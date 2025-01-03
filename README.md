# SD_ChessGame

**SD_ChessGame** is a **console-based chess application** developed in Java. The project utilizes **three design patterns** to ensure flexible and maintainable code:

1. **Strategy Pattern** (for piece movement)
2. **Decorator Pattern** (to add behaviors such as logging)
3. **Prototype Pattern** (to create/clone pieces efficiently)

This README provides an overview of the project structure, instructions for compiling and running the application, and detailed explanations of the integrated design patterns.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Installation & Execution](#installation--execution)
   - [Clone or Download](#clone-or-download)
   - [Compilation with `javac` and `java`](#compilation-with-javac-and-java)
   - [Compile and Run in IntelliJ IDEA](#compile-and-run-in-intellij-idea)
3. [Design Patterns](#design-patterns)
   - [Strategy Pattern](#strategy-pattern)
   - [Decorator Pattern](#decorator-pattern)
   - [Prototype Pattern](#prototype-pattern)
4. [Code Structure](#code-structure)
   - [Component Descriptions](#component-descriptions)
5. [Contributors](#contributors)
6. [License](#license)
7. [Additional Notes](#additional-notes)

---

## Project Overview

**SD_ChessGame** is a minimalist chess application that currently supports:

- Placing pieces on an 8×8 chessboard
- Pawn movement with single or double steps on the first move, as well as diagonal captures
- An interactive console menu to select which piece to move and where
- Logging functionality using the **Decorator Pattern** for debugging or demonstration purposes

The project is designed to be **extensible**, allowing the addition of more chess rules (such as check, checkmate, promotions, en passant) and additional pieces (Knights, Bishops, Rooks, Queens, Kings) with their respective movement strategies.

---

## Installation & Execution

### Clone or Download

1. **Clone** the repository using Git:
    ```bash
    git clone https://github.com/SamuelePoma/SD_ChessGame.git
    cd SD_ChessGame
    ```

   **OR**

   Download the ZIP file from the repository and extract it:
   - Visit the repository link: [SD_ChessGame](https://github.com/SamuelePoma/SD_ChessGame)
   - Click on the "Code" button and select "Download ZIP"
   - Extract the downloaded ZIP file and navigate to the extracted folder:
    ```bash
    cd SD_ChessGame
    ```

### Compilation with `javac` and `java`

1. Navigate to the project directory where the `src/` folder is located.
2. Compile the `.java` files:
    ```bash
    javac src/chessgame/*.java
    ```
3. Run the `Main` class:
    ```bash
    java chessgame.Main
    ```

### Compile and Run in IntelliJ IDEA

1. Open IntelliJ IDEA and select **File → Open**.
2. Navigate to the folder containing the **SD_ChessGame** project and open it.
3. Locate `Main.java` in the `chessgame` package.
4. Right-click on `Main.java` and select **Run 'Main.main()'**.
5. The console window will display an interactive menu to select pieces and execute moves.

---

## Design Patterns

### Strategy Pattern

**Purpose:**  
Incorporates different movement behaviors for chess pieces without altering the logic of `Board` or `BaseChessPiece`.

**Implementation:**

- **`MoveStrategy` Interface:** Declares the `canMove` method.
    ```java
    public interface MoveStrategy {
        boolean canMove(BaseChessPiece piece, Board board, Position from, Position to);
    }
    ```

- **Concrete Strategies:** Implement specific movement rules.
    ```java
    public class PawnMoveStrategy implements MoveStrategy {
        @Override
        public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
            // Implement pawn movement logic
            return true; // or false based on logic
        }
    }
    ```

**Association:**  
Each piece (e.g., `PawnPiece`, `RookPiece`) is linked to a specific `MoveStrategy`.

**Usage Example:**
```java
BaseChessPiece pawn = new PawnPiece("White Pawn", PieceColor.WHITE);
