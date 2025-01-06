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

### Prototype Pattern

**Purpose:**
Enables cloning of existing objects (pieces) instead of creating them from scratch.

**Implementation:**

- *BaseChessPiece Class:* Implements Cloneable and overrides the clone method.
    ```java
    public abstract class BaseChessPiece implements Cloneable {
        @Override
        public BaseChessPiece clone() {
            try {
                return (BaseChessPiece) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
    ```
    
- *PiecePrototypeFactory Class:* Maintains prototypes in a Map and returns cloned pieces upon request.
    ```java
    public class PiecePrototypeFactory {
        private static final Map<String, BaseChessPiece> prototypes = new HashMap<>();

        static {
            prototypes.put("pawn-white", new PawnPiece("White Pawn", PieceColor.WHITE));
            prototypes.put("pawn-black", new PawnPiece("Black Pawn", PieceColor.BLACK));
        }

        public static BaseChessPiece getPiece(String key) {
            BaseChessPiece prototype = prototypes.get(key);
            return (prototype != null) ? prototype.clone() : null;
        }
    }
    ```

**Benefits:**

1. *Performance Optimization:*  
   Efficiently creates new objects by cloning existing prototypes, reducing the overhead of instantiation.

2. *Dynamic Object Creation:*  
   Easily adds new types of chess pieces without altering the factory's core logic.

3. *Consistency:*  
   Ensures that all cloned objects maintain the same initial state as their prototypes.

---

### Singleton Pattern

**Purpose:**
Ensures a single instance of the Game class exists, providing a global access point to manage the game state.

**Implementation:** 

- *Game Class:* Implements the Singleton Pattern by maintaining a private static instance and providing a public static method to access it.
    ```java
    public class Game {
        private static Game instance;

        private Game() {
        }

        public static Game getInstance() {
            if (instance == null) {
                instance = new Game();
            }
            return instance;
        }
    }
    ```

**Benefits:**

1. *Controlled Access to a Single Instance:*  
   Guarantees only one instance of the game exists, avoiding duplication.

2. *Global Access Point:*  
   Provides a centralized mechanism to access the game’s state and functionality.

3. *Lazy Initialization:*  
   The instance is created only when needed, optimizing resource usage.

---

### Facade Pattern

**Purpose:**  
Simplifies the interface for managing the chessboard and interactions with chess pieces, hiding the complexity of individual components (e.g., piece movement, validation, and board state).

**Implementation:**  

- *BoardFacade Class:* Acts as a central access point for the Board and BaseChessPiece classes, providing methods to place pieces, move them, and query the state of the board.
    ```java
    public class BoardFacade {
        private final Board board;

        public BoardFacade() {
            this.board = new Board();
        }

        public void placePiece(BaseChessPiece piece, Position position) {
            board.placePiece(piece, position);
        }

        public void movePiece(Position from, Position to) {
            board.movePiece(from, to);
        }

        public void displayBoard() {
            board.display();
        }
    }
    ```

**Benefits:**

1. *Simplified Interface:*  
   Centralized access to chessboard operations reduces the need to interact directly with complex underlying systems.

2. *Encapsulation of Complexity:*  
   Hides the details of movement validation, piece placement, and board state management.

3. *Improved Maintainability:*  
   Changes in the internal logic (e.g., piece movement rules) don’t affect client code using the BoardFacade.

---

### Decorator Pattern

**Purpose:**  
Adds additional responsibilities to an object dynamically without altering its core behavior.

**Implementation:**  

- *ChessPieceDecorator Abstract Class:* Serves as the base decorator, maintaining a reference to a BaseChessPiece object.
    ```java
    public abstract class ChessPieceDecorator extends BaseChessPiece {
        protected final BaseChessPiece decoratedPiece;

        public ChessPieceDecorator(BaseChessPiece decoratedPiece) {
            super(decoratedPiece.getName(), decoratedPiece.getColor());
            this.decoratedPiece = decoratedPiece;
        }

        @Override
        public boolean canMove(Board board, Position from, Position to) {
            return decoratedPiece.canMove(board, from, to);
        }
    }
    ```

- *LoggingDecorator Concrete Class:* Adds logging functionality by overriding the canMove method to log move attempts.
    ```java
    public class LoggingDecorator extends ChessPieceDecorator {
        public LoggingDecorator(BaseChessPiece decoratedPiece) {
            super(decoratedPiece);
        }

        @Override
        public boolean canMove(Board board, Position from, Position to) {
            boolean result = super.canMove(board, from, to);
            if (result) {
                System.out.println("[LOG] " + getName() + " performed a valid move: " + from + " -> " + to + ".");
            } else {
                System.out.println("[LOG] Invalid move for " + getName() + ".");
            }
            return result;
        }
    }
    ```

**Benefits:**

1. *Open/Closed Principle:*  
   Extends functionalities without modifying existing code.

2. *Dynamic Behavior Addition:*  
   Adds or removes responsibilities at runtime.

3. *Enhanced Flexibility:*  
   Allows multiple decorators to be applied to a single object for complex behavior enhancements.

---

### Strategy Pattern

**Purpose:**  
Incorporates different movement behaviors for chess pieces without altering the logic of Board or BaseChessPiece.

Implementation:  

- *MoveStrategy Interface:* Declares the canMove method.
    ```java
    public interface MoveStrategy {
        boolean canMove(BaseChessPiece piece, Board board, Position from, Position to);
    }
    ```
    
- *Concrete Strategies:* Implement specific movement rules.
    ```java
    public class PawnMoveStrategy implements MoveStrategy {
        @Override
        public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
            return true; 
        }
    }
    ```

**Benefits:**

1. *Flexibility:*  
   Easily change or extend movement behaviors without modifying the chess piece classes or the board logic.

2. *Maintainability:*  
   Encapsulates movement logic within separate strategy classes, making the codebase easier to manage and understand.

3. *Reusability:*  
   Movement strategies can be reused across different pieces or even different projects, promoting code reuse.

4. *Adherence to SOLID Principles:*  
   Particularly the Open/Closed Principle, allowing the system to be open for extension but closed for modification.

---

### State Pattern

**Purpose:**  
Manages the behavior of the chess game as it progresses through different phases (e.g., setup, play).

Implementation:  

- *GameState Interface:* Defines the structure for game phases.
    ```java
    public interface GameState {
        void execute();
    }
    ```
    
- *Concrete States:* Implement specific behaviors for each game phase.
    ```java
    public class SetupState implements GameState {
        private final Game game;

        public SetupState(Game game) {
            this.game = game;
        }

        @Override
        public void execute() {
        }
    }

    public class PlayState implements GameState {
        private final Game game;

        public PlayState(Game game) {
            this.game = game;
        }

        @Override
        public void execute() {
        }
    }
    ```
    
- *Game Class:* Manages state transitions.
    ```java
    public class Game {
        private static Game instance;
        private GameState state;
        private final BoardFacade boardFacade;

        private Game() {
            this.boardFacade = new BoardFacade();
            this.state = new SetupState(this);
        }

        public static Game getInstance() {
            if (instance == null) {
                instance = new Game();
            }
            return instance;
        }

        public void setState(GameState state) {
            this.state = state;
        }

        public GameState getState() {
            return state;
        }

        public BoardFacade getBoardFacade() {
            return boardFacade;
        }

        public void run() {
            state.execute();
        }
    }
    ```

**Benefits:**

1. *Simplified State Management:*  
   Encapsulates game phase logic within separate classes, improving code organization.

2. *Dynamic Behavior Changes:*  
   Allows the game to switch behaviors at runtime based on the current state.

3. *Extensibility:*  
   New states can be added without altering the existing states or the Game class.

---