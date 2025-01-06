package chessgame;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

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

        System.out.println("\n=== Game Start ===");
        while (!exit) {
            Map<Position, BaseChessPiece> allPieces = boardFacade.getAllPieces();
            if (allPieces.isEmpty()) {
                System.out.println("No pieces left on the board!");
                break;
            }

            // Display pieces
            List<Position> posList = new ArrayList<>(allPieces.keySet());
            System.out.println("Pieces on the board:");
            for (int i = 0; i < posList.size(); i++) {
                Position p = posList.get(i);
                BaseChessPiece piece = allPieces.get(p);
                System.out.println(i + ") " + piece.getName() +
                        " (" + piece.getColor() + ") at " + p);
            }

            System.out.print("\nChoose the index of the piece to move (or type 'exit'): ");
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

            // Get possible moves
            List<Position> possibleMoves = boardFacade.getPossibleMoves(fromPos);
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
            boolean successful = boardFacade.movePiece(fromPos, toPos);
            if (successful) {
                System.out.println("Move executed successfully!");
                boardFacade.displayBoard();
            } else {
                System.out.println("Invalid move.");
            }
        }

        scanner.close();
        System.out.println("=== Game Ended ===");
    }
}
