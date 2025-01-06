package chessgame.state;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

import chessgame.decorators.LoggingDecorator;
import chessgame.facade.BoardFacade;
import chessgame.factory.PiecePrototypeFactory;
import chessgame.singleton.Game;
import chessgame.pieces.BaseChessPiece;
import chessgame.pieces.PawnPiece;
import chessgame.utils.PieceColor;
import chessgame.utils.Position;

public class PlayState implements GameState {
    private Game game;

    public PlayState(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        BoardFacade boardFacade = game.getBoardFacade();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        PieceColor currentTurn = PieceColor.WHITE; // White starts first
        List<String> moveHistory = new ArrayList<>();

        System.out.println("\n=== Simple Chess Demo ===");
        System.out.println("Type 'exit' to quit at any time.\n");

        while (true) {
            // Display the board
            boardFacade.displayBoard();

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
            Map<Position, BaseChessPiece> allPieces = boardFacade.getAllPieces();
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
            List<Position> possibleMoves = boardFacade.getPossibleMoves(fromPos);
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
            boolean successful = boardFacade.movePiece(fromPos, toPos);
            if (successful) {
                // Record the move in history
                String moveRecord = currentTurn + ": " + chosenPiece.getName() + " " + fromPos + " -> " + toPos;
                moveHistory.add(moveRecord);

                // Check for pawn promotion
                BaseChessPiece movedPiece = boardFacade.getPiece(toPos);
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
                            boardFacade.placePiece(promotedPiece, toPos);
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
}
