# Deque (Double-Ended Queue)

A **deque** supports O(1) insertion and removal at *both* ends. Acts as a generalization of both a stack and a queue.

## Requirements

Generic, unbounded deque with the following contract:

| Operation       | Returns   | Behavior                                          |
|-----------------|-----------|---------------------------------------------------|
| `addFirst(T)`   | `void`    | Insert at front                                   |
| `addLast(T)`    | `void`    | Insert at back                                    |
| `removeFirst()` | `T`       | Remove and return front. Throws if empty.         |
| `removeLast()`  | `T`       | Remove and return back. Throws if empty.          |
| `peekFirst()`   | `T`       | Return front without removing. Throws if empty.  |
| `peekLast()`    | `T`       | Return back without removing. Throws if empty.   |
| `size()`        | `int`     | Number of elements currently in the deque         |
| `isEmpty()`     | `boolean` | `true` iff `size() == 0`                          |

**Empty-state failure mode:** `IllegalStateException` (not `IndexOutOfBoundsException` — the caller didn't pass a bad index, the object is in a state where the op is illegal).

**Design decision — dropped operations:**
- No `get(int index)` — that would make this a List, not a Deque. Minimal ADT keeps the contract honest and doesn't lock us into a specific representation.

## Entities

- **`Deque<T>`** — the public class. Generic over element type.
- **Backing array** — implementation detail. Not exposed.

## Class Design

### Backing representation: Circular Array (Ring Buffer)

**Why circular array over doubly linked list:**
- Cache locality: contiguous memory → far fewer cache misses.
- Low per-element overhead: no node objects, no `prev`/`next` pointers.
- Matches Java's `ArrayDeque`, which outperforms `LinkedList` in virtually every benchmark.
- Extends knowledge from `DynamicArray` (resize) with a new skill (wraparound indexing).

### Fields

| Field            | Purpose                                               |
|------------------|-------------------------------------------------------|
| `T[] array`      | Backing store. `array.length` is the current capacity.|
| `int head`       | Index of the first element                            |
| `int tail`       | Index of the **next empty slot** (one past the last)  |
| `int size`       | Number of elements currently stored                   |

**Tail convention — Interpretation 2:** `tail` points to the *next empty slot*, not the last element.
- `addLast(x)`: write at `array[tail]`, then advance `tail`.
- `peekLast()`: read from `array[(tail - 1 + length) % length]`.
- This matches Java's `ArrayDeque` and makes `size == (tail - head)` (mod length) hold cleanly.

### Invariants

These must hold between every operation:

1. `0 <= size <= array.length`
2. `0 <= head < array.length`
3. `0 <= tail < array.length`
4. If `size == 0`, the deque is empty. `head` and `tail` values are irrelevant (don't check them).
5. If `size == array.length`, the deque is full and the next add must resize.
6. Logical order: elements live at `array[(head + i) % array.length]` for `i` in `[0, size)`.

### Empty vs. Full disambiguation

`head == tail` is ambiguous — it's true for *both* empty and full states. We disambiguate with the explicit `size` field:

- `size == 0` → empty
- `size == array.length` → full

**Rule:** never trust `head == tail` as a state signal. Always check `size`.

### Pointer arithmetic rules

| Operation          | Formula                              |
|--------------------|--------------------------------------|
| Advance a pointer  | `ptr = (ptr + 1) % length`           |
| Retreat a pointer  | `ptr = (ptr - 1 + length) % length`  |

The `+ length` on decrement is required in Java because `%` returns the *remainder* (keeps sign of dividend), not mathematical modulo. `(-1) % 6 == -1` in Java, which would crash an array access. Adding `length` first forces non-negative input.

### Resize policy

- **Trigger:** `size == array.length`
- **New capacity:** `2 * array.length` (amortized O(1) adds, same analysis as `DynamicArray`)
- **Copy strategy:** single modulo loop (Way B):
  ```
  for i in [0, size):
      newArray[i] = oldArray[(head + i) % oldLength]
  ```
- **Post-resize state:** `head = 0`, `tail = size`, `size` unchanged. The new array holds elements in a straight, un-wrapped block starting at index 0.

**Alternative (Way A, deferred):** two `System.arraycopy` calls — faster (native memcpy), harder to read. Revisit when we do the power-of-two optimization.

## Complexity

| Operation     | Amortized | Worst Case     |
|---------------|-----------|----------------|
| `addFirst`    | O(1)      | O(n) on resize |
| `addLast`     | O(1)      | O(n) on resize |
| `removeFirst` | O(1)      | O(1)           |
| `removeLast`  | O(1)      | O(1)           |
| `peekFirst`   | O(1)      | O(1)           |
| `peekLast`    | O(1)      | O(1)           |
| `size`        | O(1)      | O(1)           |
| `isEmpty`     | O(1)      | O(1)           |

## Follow-ups (after core implementation)

1. **Power-of-two capacity optimization** — what Java's `ArrayDeque` actually does.
   - Constrain `array.length` to a power of two.
   - Replace `% length` with `& (length - 1)` — bitwise AND is dramatically faster than modulo (division).
   - Eliminates the need for an explicit `size` field; size becomes implicit via `(tail - head) & (length - 1)`.
   - Tradeoff: up to 2x memory waste in the worst case, but simpler and faster.
   - Same trick appears in hash table bucketing, lock-free ring buffers, and the LMAX Disruptor.
2. **JUnit test harness** — set up manual JUnit (no Maven/Gradle). Cover: empty-state exceptions, single-element edge cases, wraparound scenarios, resize correctness.
3. **Benchmark vs. `LinkedList`** — measure the cache-locality win empirically once both implementations exist.
