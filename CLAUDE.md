# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Purpose

This is a Low-Level Design (LLD) practice repository. Each subdirectory contains an independent design problem implemented in Java. The goal is learning — understanding design patterns, SOLID principles, and object-oriented modeling through hands-on implementation.

## Structure

Each problem lives in its own directory (e.g., `ConnectFour/`) and contains:
- A `.md` file with the problem statement, requirements, entity identification, and class design notes
- `.java` implementation files

## Build & Run

Plain Java files with no build tool (no Maven/Gradle). Compile and run directly:

```bash
# Compile all files in a problem directory
javac ConnectFour/*.java

# Run the main class (adjust class name per problem)
java ConnectFour.MainClassName
```

## Workflow for Each Problem

The `.md` files follow a consistent design process:
1. **Requirements** — extract from the problem statement
2. **Entities** — identify nouns from requirements
3. **Class Design** — define classes with fields and methods (SRP focus)
4. **Implementation** — core logic first, then edge cases

When working on a new problem, follow this same structure. Walk through the design before writing code.

## Session Log

### 2026-04-01
**Completed:** Dynamic Array (`DynamicArray/DynamicArray.java` + `Main.java`)
- Full implementation: constructor, `addElement` (with doubling resize), `removeElement`, `getElementAtIndex`, `arrayLength`
- All fields made `private` (encapsulation)
- Proper exception types: `IllegalArgumentException` (bad input), `IllegalStateException` (empty array), `IndexOutOfBoundsException` (bad index)
- Compiled and tested successfully

**Decisions:**
- Remove from end only (O(1)) — no index-based removal
- Double capacity on resize (amortized O(1) add)

**Repo setup:** Initialized git, pushed to `https://github.com/vivek4879/LLD`, added README with full problem list

**Next session:** Start Singly Linked List (`LinkedList/` directory)

### 2026-04-03
**Completed:** Singly Linked List (`LinkedList/LinkedList.java` + `Main.java`)
- Full implementation: `insertHead`, `insertTail`, `insertAtIndex`, `deleteHead`, `deleteTail`, `deleteAtIndex`, `deleteByValue`, `getValueByIndex`, `contains`, `getSize`, `displayList`
- Node as `private static` inner class (encapsulation, no unnecessary outer reference)
- 0-indexed API throughout
- Proper exceptions: `IllegalStateException` (empty list ops), `IndexOutOfBoundsException` (bad index reads)
- Compiled and tested manually via `Main.java`

**Decisions:**
- `private static` inner class for Node — implementation detail, not public API
- `size` field stored (O(1) getSize) rather than computed on traversal
- Insert methods return `void` (always succeed); delete methods return the removed value; `deleteByValue` returns `boolean`
- `displayList` is `void`, prints to stdout

**Added:** `.gitignore` with `.vscode/`

**Next session:** Start Double-ended Queue (`Deque/` directory). Use JUnit for testing this time.
