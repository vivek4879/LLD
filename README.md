# LLD — Low-Level Design Practice

A hands-on repository for learning low-level design, data structures, and object-oriented principles in Java. Each problem lives in its own directory and is worked from scratch, with a focus on tradeoffs, edge cases, and clean design (SOLID, design patterns, SRP).

> **Note:** This is a personal practice repo. I'm using it to learn and build intuition for low-level design, so the code and designs are works in progress. Expect incomplete implementations, design notes that evolve as I refine my thinking, and tradeoffs I'm still working through. It is not production-ready code or a reference solution.

## Problems

### Data Structures
| # | Problem | Directory | Status |
|---|---------|-----------|--------|
| 1 | Dynamic Array | `DynamicArray/` | Done |
| 2 | Singly Linked List | `LinkedList/` | Done |
| 3 | Double-ended Queue (circular array) | `Deque/` | In Progress |
| 4 | Binary Search Tree | — | Pending |
| 5 | Hash Table | — | Pending |
| 6 | Heap | — | Pending |
| 7 | Graph | — | Pending |
| 8 | Disjoint Set (Union-Find) | — | Pending |
| 9 | Segment Tree | — | Pending |

### Object-Oriented Design
| # | Problem | Directory | Status |
|---|---------|-----------|--------|
| 1 | Connect Four | `ConnectFour/` | Design only |
| 2 | Amazon Locker | `AmazonLocker/` | Done |
| 3 | Elevator System | `Elevator/` | In Progress |
| 4 | File System | `FIleSystem/` | In Progress |
| 5 | Parking Lot | `ParkingLot/` | In Progress |
| 6 | Movie Ticket Booking | `movieTicketBooking/` | In Progress |
| 7 | Logging Service | `loggingService/` | Design / pseudocode |
| 8 | Warehouse Manager | `WarehouseManager/` | In Progress |

## Repo Layout

Each problem directory typically contains:
- A markdown file (`README.md`, `requirements.md`, or `<problem>.md`) with the requirements, entities, class design, and design tradeoffs
- One or more `.java` files with the implementation

## Design Workflow

1. **Requirements** — extract from the problem statement, note what's out of scope
2. **Entities** — identify the nouns
3. **Class design** — fields and methods, with SRP in mind
4. **Implementation** — core logic first, then edge cases

## How to Compile & Run

No build tools, just plain Java. From the repo root:

```bash
# Compile all files in a problem directory
javac <ProblemDir>/*.java

# Run the main class
java <ProblemDir>.Main
```

Example:

```bash
javac DynamicArray/*.java
java DynamicArray.Main
```

Note: some problems (e.g. `loggingService/`, `ParkingLot/`) are still design sketches and may not compile yet.
