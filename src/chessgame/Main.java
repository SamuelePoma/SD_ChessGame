package chessgame;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        // ------------------------------------------------
        // 1) Create and place 8 white pawns (row 6)
        // ------------------------------------------------
        for (int col = 0; col < 8; col++) {
            BaseChessPiece whitePawn = new PawnPiece("White Pawn", PieceColor.WHITE);
            // For demonstration, decorate the pawn in column 0 with a logging decorator
            if (col == 0) {
                whitePawn = new LoggingDecorator(whitePawn);
            }
            board.placePiece(whitePawn, new Position(6, col));
        }

        // ------------------------------------------------
        // 2) Create and place 8 black pawns (row 1)
        // ------------------------------------------------
        for (int col = 0; col < 8; col++) {
            BaseChessPiece blackPawn = new PawnPiece("Black Pawn", PieceColor.BLACK);
            // Optionally decorate the pawn at col 0
            if (col == 0) {
                blackPawn = new LoggingDecorator(blackPawn);
            }
            board.placePiece(blackPawn, new Position(1, col));
        }

        // ------------------------------------------------
        // 3) Sample moves (optional)
        // ------------------------------------------------
        // Attempt a double step for a white pawn at (6,0) -> (4,0)
        board.movePiece(new Position(6, 0), new Position(4, 0));
        // Attempt a double step for a black pawn at (1,0) -> (3,0)
        board.movePiece(new Position(1, 0), new Position(3, 0));

        // ------------------------------------------------
        // 4) Basic interactive CLI
        // ------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("\n=== Simple Chess Pawns Demo ===");
        System.out.println("Type 'exit' to quit at any time.\n");

        while (!exit) {
            // Display all pieces
            Map<Position, BaseChessPiece> allPieces = board.getAllPieces();
            if (allPieces.isEmpty()) {
                System.out.println("No pieces left on the board!");
                break;
            }

            List<Position> posList = new ArrayList<>(allPieces.keySet());
            System.out.println("Pieces on the board:");
            for (int i = 0; i < posList.size(); i++) {
                Position p = posList.get(i);
                BaseChessPiece piece = allPieces.get(p);
                System.out.println(i + ") " + piece.getName() +
                        " (" + piece.getColor() + ") at " + p);
            }
            System.out.print("\nChoose the index of the piece to move: ");
            String inp = scanner.nextLine().trim();
            if (inp.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }

            int idx;
            try {
                idx = Integer.parseInt(inp);
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (idx < 0 || idx >= posList.size()) {
                System.out.println("Index out of range. Try again.");
                continue;
            }

            Position fromPos = posList.get(idx);
            BaseChessPiece chosenPiece = allPieces.get(fromPos);

            // Calculate possible moves (using the dry-run method in Board)
            List<Position> possibleMoves = board.getPossibleMoves(fromPos);
            if (possibleMoves.isEmpty()) {
                System.out.println("No valid moves for " + chosenPiece.getName());
                continue;
            }

            System.out.println("\nPossible moves for " + chosenPiece.getName() + ":");
            for (int i = 0; i < possibleMoves.size(); i++) {
                System.out.println(i + ") " + possibleMoves.get(i));
            }

            System.out.print("Choose the index of the move: ");
            inp = scanner.nextLine().trim();
            if (inp.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }

            int moveIdx;
            try {
                moveIdx = Integer.parseInt(inp);
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (moveIdx < 0 || moveIdx >= possibleMoves.size()) {
                System.out.println("Index out of range. Try again.");
                continue;
            }

            Position toPos = possibleMoves.get(moveIdx);
            boolean successful = board.movePiece(fromPos, toPos);
            if (successful) {
                System.out.println("Move executed successfully!\n");
            } else {
                System.out.println("Invalid move.\n");
            }
        }

        scanner.close();
        System.out.println("=== Program ended. ===");
    }
}
