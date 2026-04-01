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
