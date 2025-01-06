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

### Facade Pattern

**Purpose:**  
Simplifies the interface for managing the chessboard and interactions with chess pieces, hiding the complexity of individual components (e.g., piece movement, validation, and board state).

---

**Implementation:**  
The `BoardFacade` class acts as a central access point for the `Board` and `BaseChessPiece` classes, providing methods to place pieces, move them, and query the state of the board.

Example:
```java
BoardFacade boardFacade = new BoardFacade();
BaseChessPiece pawn = new PawnPiece("White Pawn", PieceColor.WHITE);
boardFacade.placePiece(pawn, new Position(6, 0));
boardFacade.displayBoard();
```

---

**Benefits of Using the Facade Pattern:**

1. **Simplified Interface**:  
   - Centralized access to chessboard operations reduces the need to interact directly with complex underlying systems.

2. **Encapsulation of Complexity**:  
   - Hides the details of movement validation, piece placement, and board state management.

3. **Improved Maintainability**:  
   - Changes in the internal logic (e.g., piece movement rules) don’t affect client code using the `BoardFacade`.

---

### State Pattern

**Purpose:**  
Manages the behavior of the chess game as it progresses through different phases (e.g., setup, play).

---

**Implementation:**  
The `GameState` interface defines the structure for game phases. Concrete implementations like `SetupState` and `PlayState` encapsulate the specific logic for each phase. The `Game` class uses these implementations to manage state transitions dynamically.

Example:
```java
Game game = Game.getInstance();
GameState setupState = new SetupState(game);
game.setState(setupState);
game.getState().execute();
```

---

**Benefits of Using the State Pattern:**

1. **Simplified State Management**:  
   - Encapsulates game phase logic within separate classes, improving code organization.

2. **Dynamic Behavior Changes**:  
   - Allows the game to switch behaviors at runtime based on the current state.

3. **Extensibility**:  
   - New states can be added without altering the existing states or the `Game` class.

---

### Singleton Pattern

**Purpose:**  
Ensures a single instance of the `Game` class exists, providing a global access point to manage the game state.

---

**Implementation:**  
The `Game` class maintains a private static instance of itself and provides a `getInstance` method for lazy initialization and global access.

Example:
```java
Game game = Game.getInstance();
BoardFacade boardFacade = game.getBoardFacade();
boardFacade.displayBoard();
```

---

**Benefits of Using the Singleton Pattern:**

1. **Controlled Access to a Single Instance**:  
   - Guarantees only one instance of the game exists, avoiding duplication.

2. **Global Access Point**:  
   - Provides a centralized mechanism to access the game’s state and functionality.

3. **Lazy Initialization**:  
   - The instance is created only when needed, optimizing resource usage.
