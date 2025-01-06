package chessgame.board;

import chessgame.pieces.BaseChessPiece;
import chessgame.strategies.MoveStrategy;
import chessgame.utils.Position;

import java.util.*;

/**
 * Represents the chessboard and manages the placement and movement of pieces.
 */
public class Board {
    private final Map<Position, BaseChessPiece> pieces = new HashMap<>();

    /**
     * Places a piece at a specified position on the board.
     *
     * @param piece The chess piece to place.
     * @param pos   The position where the piece will be placed.
     */
    public void placePiece(BaseChessPiece piece, Position pos) {
        pieces.put(pos, piece);
    }

    /**
     * Retrieves the piece at a specified position.
     *
     * @param pos The position to query.
     * @return The chess piece at the position, or null if empty.
     */
    public BaseChessPiece getPiece(Position pos) {
        return pieces.get(pos);
    }

    /**
     * Moves a piece from one position to another if the move is valid.
     *
     * @param from The starting position.
     * @param to   The target position.
     * @return True if the move was successful, false otherwise.
     */
    public boolean movePiece(Position from, Position to) {
        BaseChessPiece piece = pieces.get(from);
        if (piece == null) {
            System.out.println("No piece at " + from + ".");
            return false;
        }

        // Check if the piece can move to the target position
        if (piece.canMove(this, from, to)) {
            pieces.remove(from);
            // Capture opponent's piece if present
            BaseChessPiece destinationPiece = pieces.get(to);
            if (destinationPiece != null && destinationPiece.getColor() != piece.getColor()) {
                System.out.println(piece.getName() + " captured " + destinationPiece.getName() + " at " + to + ".");
            }
            pieces.put(to, piece);
            System.out.println(piece.getName() + " moved from " + from + " to " + to + ".");
            return true;
        } else {
            System.out.println("Invalid move for " + piece.getName() + ".");
            return false;
        }
    }

    /**
     * Retrieves all pieces currently on the board.
     *
     * @return An unmodifiable map of positions to chess pieces.
     */
    public Map<Position, BaseChessPiece> getAllPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    /**
     * Retrieves all possible valid moves for a piece at a given position.
     *
     * @param from The position of the piece.
     * @return A list of valid target positions.
     */
    public List<Position> getPossibleMoves(Position from) {
        BaseChessPiece piece = pieces.get(from);
        if (piece == null) {
            return Collections.emptyList();
        }

        MoveStrategy strategy = piece.getMoveStrategy();
        List<Position> validMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position to = new Position(row, col);
                if (!to.equals(from) && strategy.canMove(piece, this, from, to)) {
                    validMoves.add(to);
                }
            }
        }
        return validMoves;
    }

    /**
     * Displays the current state of the chessboard in the console.
     * The board is displayed symmetrically with white pieces on the bottom and black pieces on the top.
     */
    public void displayBoard() {
        System.out.println("+------------------------+");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + "|");
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                BaseChessPiece piece = getPiece(pos);
                if (piece != null) {
                    System.out.print(piece + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println("|");
        }
        System.out.println("+------------------------+");
        System.out.println("  a b c d e f g h");
    }
}
