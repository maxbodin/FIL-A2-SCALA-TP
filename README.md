# IMT-FIL-A2-SCALA-TP

Scala practical exercises completed at IMT Atlantique to learn Scala 3, functional programming patterns, and object-oriented design. The final assessment is the **AmazingMaze** project: a comprehensive implementation of a binary-tree maze with multiple exploration strategies, including a concurrent traversal algorithm.

## Overview

This repository contains six practical assignments (TP0–TP4 plus AmazingMaze) that progressively cover:
- Scala 3 language fundamentals
- Immutable data structures and case classes
- Object-oriented design patterns
- Functional programming and higher-order functions
- Concurrent exploration using explicit work stacks

## Repository structure

### TP0: Getting started
- Entry points: `@main` annotation, `App` trait, and command-line arguments
- Simple "Hello World" variants to learn Scala 3 syntax

### TP1: Rational numbers
- Immutable `Rational` case class with automatic simplification via GCD
- Operations: addition, multiplication, mixed arithmetic with integers
- Companion object with factory methods and constants (`ZERO`, `ONE`)
- Test suite (`Test.scala`) with assertion-based validation

### TP2: Object-oriented design
- Inheritance: `Person` → `Human` and `Robot`
- Protected mutable state (age management)
- Trait `Social` for composition
- `SocialRobot` combining inheritance and traits
- `toString` override demonstrating pattern matching on `Option`

### TP3: Functional programming
- Tail-recursive factorial (`Fact.scala`, `FunctionalFactorial`)
- Higher-order functions: `values`, `largest`, `largestAt`, `adjustToPair`
- Closures and lazy-evaluated functions
- Custom control structure: `unless` with call-by-name parameters
- Range operations and functional reduction

### TP4: Enumerations
- Two parallel implementations of a `Direction` enum:
  - **OO style** (`oo.sc`): Scala 3 native `enum` with methods
  - **FP style** (`fp.sc`): sealed class with object companions (pre-Scala 3 pattern)
- Both support inversion and pattern matching

### AmazingMaze: Final assessment
A complete maze explorer demonstrating three exploration strategies:

**Data model:**
- `Maze` sealed enum with `Leaf` and `Branch` cases
- `Branch` nodes have a label, two children (left/right), and an `Exploration` status
- `Exploration` enum: `Explored`, `PartiallyExplored`, `UnExplored`

**Exploration algorithms:**
1. **Simple recursion** (`explore(): List[String]`)
   - Returns a trace (list of labels) as a functional result
   
2. **Mutable accumulation** (`explore(trace: ListBuffer[String]): Unit`)
   - Builds trace imperatively into a `ListBuffer`
   - More efficient for large trees
   
3. **Concurrent exploration** (`concurrentExplore(work: Stack[Maze], trace: ListBuffer[String]): Unit`)
   - Uses an explicit work stack (`Stack.scala`) for depth-first traversal
   - Demonstrates three-phase state machine:
     - **UnExplored** → mark `PartiallyExplored`, push node and left child
     - **PartiallyExplored** → mark `Explored`, push right child
     - **Explored** → add label to trace (revisit for post-order)
   - Simulates concurrent workers without actual threads

**Stack helper:**
- Generic `Stack[A]` implementation backed by `ListBuffer` with `push`, `pop`, `isEmpty`

## Key learning outcomes

- **Immutability & simplification:** `Rational` demonstrates persistent data structures.
- **Inheritance & composition:** `SocialRobot` shows multiple dispatch patterns.
- **Functional paradigms:** `TP3` covers closures, map/reduce, and call-by-name.
- **State machines:** `AmazingMaze` illustrates non-blocking traversal with explicit stacks.
- **Concurrent thinking:** The work-stack exploration is a foundation for work-stealing schedulers.