# SD_ChessGame

**SD_ChessGame** is a **console-based chess application** developed in Java. The project utilizes **six design patterns** to ensure flexible and maintainable code:

1. **Prototype Pattern** 
2. **Singleton Pattern** 
3. **Facade Pattern**
4. **Decorator Pattern**
5. **Strategy Pattern** 
6. **State Pattern**

This README provides an overview of the project structure, instructions for compiling and running the application, and detailed explanations of the integrated design patterns.

This game is done for the Software Design Exam. The program was made by Andrea Bezzolato and Samuele Poma, students of the HZ University of Applied Sciences.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Installation & Execution](#installation--execution)
   - [Clone or Download](#clone-or-download)
   - [Compilation with `javac` and `java`](#compilation-with-javac-and-java)
   - [Compile and Run in IntelliJ IDEA](#compile-and-run-in-intellij-idea)
3. [Design Patterns](#design-patterns)
   - [Prototype Pattern](#prototype-pattern)
   - [Singleton Pattern](#singleton-pattern)
   - [Facade Pattern](#facade-pattern)
   - [Decorator Pattern](#decorator-pattern)
   - [Strategy Pattern](#strategy-pattern)
   - [State Pattern](#state-pattern)
   
   
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
```

### Singleton Pattern:
In this game, the Singleton Design Pattern was implemented using the Game class.
This class, in fact allows the program to have only one instance of the game, while
providing a global access point to this instance. It solves 2 main problems: ensures
that a class has just a single instance and provides a global access point to that instance.
This is achieved by having a static instance of the class inside of it, making the
constructor private and having a getInstance method that returns the class and
creates it if it doesn't exist yet.

### Facade Pattern
In this game, the Facade Design Pattern was implemented using the BoardFacade class.
This class, provides a simple interface so that the application doesn't need to
operate with complex functionalities but can have a layer in between. This is achived
by having the Board class functionalities being called from the Facade and not from
the Main. A facade might provide limited functionality in comparison to working with
the subsystem directly. However, it includes only those features that clients really
care about. This is useful when the clients need only some operations and not all of
them.

### State Pattern
In this game, the State Design Pattern was implemented using the GameState, SetupState
and PlayState classes. This classes allow the game class to act differently based on
the state of the application. At the beginning in fact, the game will be in the setup
state, where the pieces are put inside of the game. Next, the game will go in the play
state, where the actual game will happen. Instead of implementing all behaviors on its
own, the original object, called context, stores a reference to one of the state objects
that represents its current state, and delegates all the state-related work to that object.
