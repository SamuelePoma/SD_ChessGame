package chessgame;

import chessgame.board.Board;
import chessgame.decorators.LoggingDecorator;
import chessgame.factory.PiecePrototypeFactory;
import chessgame.pieces.BaseChessPiece;
import chessgame.pieces.PawnPiece;
import chessgame.utils.PieceColor;
import chessgame.utils.Position;

import java.util.*;

/**
 * Main class to run the chess game.
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        // ------------------------------------------------
        // 1) Create and place white pawns (row 6)
        // ------------------------------------------------
        initializePawns(board, "pawn-white", 6);

        // ------------------------------------------------
        // 2) Create and place black pawns (row 1)
        // ------------------------------------------------
        initializePawns(board, "pawn-black", 1);

        // ------------------------------------------------
        // 3) Create and place Rooks
        // ------------------------------------------------
        initializeRooks(board);

        // ------------------------------------------------
        // 4) Create and place Bishops
        // ------------------------------------------------
        initializeBishops(board);

        // ------------------------------------------------
        // 5) Create and place Knights
        // ------------------------------------------------
        initializeKnights(board);

        // ------------------------------------------------
        // 6) Create and place Queens and Kings
        // ------------------------------------------------
        initializeQueensAndKings(board);

        // ------------------------------------------------
        // 8) Interactive CLI with Turn Management and Enhancements
        // ------------------------------------------------
        runGameLoop(board);
    }

    /**
     * Initializes and places pawns on the board.
     *
     * @param board The chess board.
     * @param pieceKey The key for the piece type and color.
     * @param row The row where pawns should be placed.
     */
    private static void initializePawns(Board board, String pieceKey, int row) {
        for (int col = 0; col < 8; col++) {
            BaseChessPiece pawn = PiecePrototypeFactory.getPiece(pieceKey);
            pawn = new LoggingDecorator(pawn); // Optional decoration
            board.placePiece(pawn, new Position(row, col));
        }
    }

    /**
     * Initializes and places rooks on the board.
     *
     * @param board The chess board.
     */
    private static void initializeRooks(Board board) {
        // White Rooks at (7,0) and (7,7) -> a1 and h1
        placeMultiplePieces(board, "rook-white", new Position[]{new Position(7, 0), new Position(7, 7)});

        // Black Rooks at (0,0) and (0,7) -> a8 and h8
        placeMultiplePieces(board, "rook-black", new Position[]{new Position(0, 0), new Position(0, 7)});
    }

    /**
     * Initializes and places bishops on the board.
     *
     * @param board The chess board.
     */
    private static void initializeBishops(Board board) {
        // White Bishops at (7,2) and (7,5) -> c1 and f1
        placeMultiplePieces(board, "bishop-white", new Position[]{new Position(7, 2), new Position(7, 5)});

        // Black Bishops at (0,2) and (0,5) -> c8 and f8
        placeMultiplePieces(board, "bishop-black", new Position[]{new Position(0, 2), new Position(0, 5)});
    }

    /**
     * Initializes and places knights on the board.
     *
     * @param board The chess board.
     */
    private static void initializeKnights(Board board) {
        // White Knights at (7,1) and (7,6) -> b1 and g1
        placeMultiplePieces(board, "knight-white", new Position[]{new Position(7, 1), new Position(7, 6)});

        // Black Knights at (0,1) and (0,6) -> b8 and g8
        placeMultiplePieces(board, "knight-black", new Position[]{new Position(0, 1), new Position(0, 6)});
    }

    /**
     * Initializes and places queens and kings on the board.
     *
     * @param board The chess board.
     */
    private static void initializeQueensAndKings(Board board) {
        // White Queen at (7,3) and White King at (7,4) -> d1 and e1
        placeMultiplePieces(board, "queen-white", new Position[]{new Position(7, 3)});
        placeMultiplePieces(board, "king-white", new Position[]{new Position(7, 4)});

        // Black Queen at (0,3) and Black King at (0,4) -> d8 and e8
        placeMultiplePieces(board, "queen-black", new Position[]{new Position(0, 3)});
        placeMultiplePieces(board, "king-black", new Position[]{new Position(0, 4)});
    }

    /**
     * Runs the main game loop, handling user input and game state.
     *
     * @param board The chess board.
     */
    private static void runGameLoop(Board board) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        PieceColor currentTurn = PieceColor.WHITE; // White starts first
        List<String> moveHistory = new ArrayList<>();

        System.out.println("\n=== Simple Chess Demo ===");
        System.out.println("Type 'exit' to quit at any time.\n");

        while (true) {
            // Display the board
            board.displayBoard();

            // Display move history
            if (!moveHistory.isEmpty()) {
                System.out.println("Move History:");
                for (int i = 0; i < moveHistory.size(); i++) {
                    System.out.println((i + 1) + ". " + moveHistory.get(i));
                }
            }

            // Display current turn
            System.out.println("\nCurrent Turn: " + currentTurn);
            System.out.println("Pieces available to move:");

            // Get all pieces of the current player
            Map<Position, BaseChessPiece> allPieces = board.getAllPieces();
            List<Position> posList = new ArrayList<>(allPieces.keySet());
            List<Integer> validPieceIndices = new ArrayList<>();
            for (int i = 0; i < posList.size(); i++) {
                Position p = posList.get(i);
                BaseChessPiece piece = allPieces.get(p);
                if (piece.getColor() == currentTurn) {
                    System.out.println(validPieceIndices.size() + ") " + piece.getName() + " at " + p);
                    validPieceIndices.add(i);
                }
            }

            if (validPieceIndices.isEmpty()) {
                System.out.println("No available pieces to move for " + currentTurn);
                break;
            }

            System.out.print("\nChoose the index of the piece to move: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }

            int pieceIdx;
            try {
                pieceIdx = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (pieceIdx < 0 || pieceIdx >= validPieceIndices.size()) {
                System.out.println("Index out of range. Please try again.");
                continue;
            }

            Position fromPos = posList.get(validPieceIndices.get(pieceIdx));
            BaseChessPiece chosenPiece = allPieces.get(fromPos);

            // Calculate possible moves
            List<Position> possibleMoves = board.getPossibleMoves(fromPos);
            if (possibleMoves.isEmpty()) {
                System.out.println("No valid moves for " + chosenPiece.getName());
                continue;
            }

            // Display possible moves
            System.out.println("\nPossible moves for " + chosenPiece.getName() + " at " + fromPos + ":");
            for (int i = 0; i < possibleMoves.size(); i++) {
                System.out.println(i + ") " + possibleMoves.get(i));
            }

            System.out.print("Choose the index of the move: ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            int moveIdx;
            try {
                moveIdx = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (moveIdx < 0 || moveIdx >= possibleMoves.size()) {
                System.out.println("Index out of range. Please try again.");
                continue;
            }

            Position toPos = possibleMoves.get(moveIdx);
            boolean successful = board.movePiece(fromPos, toPos);
            if (successful) {
                // Record the move in history
                String moveRecord = currentTurn + ": " + chosenPiece.getName() + " " + fromPos + " -> " + toPos;
                moveHistory.add(moveRecord);

                // Check for pawn promotion
                BaseChessPiece movedPiece = board.getPiece(toPos);
                if (movedPiece instanceof PawnPiece) {
                    if ((movedPiece.getColor() == PieceColor.WHITE && toPos.x() == 0) ||
                            (movedPiece.getColor() == PieceColor.BLACK && toPos.x() == 7)) {
                        System.out.println("Pawn promotion! Choose a piece to promote to:");
                        System.out.println("1) Queen");
                        System.out.println("2) Rook");
                        System.out.println("3) Bishop");
                        System.out.println("4) Knight");
                        System.out.print("Enter the number of your choice: ");
                        String choice = scanner.nextLine().trim();
                        BaseChessPiece promotedPiece = null;
                        switch (choice) {
                            case "1":
                                promotedPiece = PiecePrototypeFactory.getPiece(
                                        movedPiece.getColor() == PieceColor.WHITE ? "queen-white" : "queen-black");
                                break;
                            case "2":
                                promotedPiece = PiecePrototypeFactory.getPiece(
                                        movedPiece.getColor() == PieceColor.WHITE ? "rook-white" : "rook-black");
                                break;
                            case "3":
                                promotedPiece = PiecePrototypeFactory.getPiece(
                                        movedPiece.getColor() == PieceColor.WHITE ? "bishop-white" : "bishop-black");
                                break;
                            case "4":
                                promotedPiece = PiecePrototypeFactory.getPiece(
                                        movedPiece.getColor() == PieceColor.WHITE ? "knight-white" : "knight-black");
                                break;
                            default:
                                System.out.println("Invalid choice. Pawn remains as a Pawn.");
                                break;
                        }
                        if (promotedPiece != null) {
                            promotedPiece = new LoggingDecorator(promotedPiece);
                            board.placePiece(promotedPiece, toPos);
                            System.out.println("Pawn promoted to " + promotedPiece.getName() + "!");
                            moveRecord += " (Promoted to " + promotedPiece.getName() + ")";
                            moveHistory.set(moveHistory.size() - 1, moveRecord);
                        }
                    }
                }

                System.out.println("Move executed successfully!\n");
                // Toggle the turn
                currentTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
            } else {
                System.out.println("Move was not successful. Please try again.\n");
            }
        }

        scanner.close();
        System.out.println("=== Game Ended ===");
    }

    /**
     * Places multiple pieces on the board.
     *
     * @param board The chess board.
     * @param pieceKey The key for the piece type and color.
     * @param positions The positions where pieces should be placed.
     */
    private static void placeMultiplePieces(Board board, String pieceKey, Position[] positions) {
        for (Position pos : positions) {
            BaseChessPiece piece = PiecePrototypeFactory.getPiece(pieceKey);
            piece = new LoggingDecorator(piece); // Optional decoration
            board.placePiece(piece, pos);
        }
    }
}
