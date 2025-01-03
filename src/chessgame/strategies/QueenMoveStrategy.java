package chessgame.strategies;

import chessgame.board.Board;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Move strategy for Queen pieces.
 */
public class QueenMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        int fromX = from.x();
        int fromY = from.y();
        int toX = to.x();
        int toY = to.y();

        int deltaX = toX - fromX;
        int deltaY = toY - fromY;

        // Check for horizontal, vertical, or diagonal movement
        if (deltaX == 0 && deltaY == 0) {
            return false; // No movement
        }

        if (deltaX == 0) {
            // Horizontal movement
            int stepY = Integer.compare(toY, fromY);
            int currentY = fromY + stepY;
            while (currentY != toY) {
                Position currentPos = new Position(fromX, currentY);
                if (board.getPiece(currentPos) != null) {
                    return false; // Path is blocked
                }
                currentY += stepY;
            }
        } else if (deltaY == 0) {
            // Vertical movement
            int stepX = Integer.compare(toX, fromX);
            int currentX = fromX + stepX;
            while (currentX != toX) {
                Position currentPos = new Position(currentX, fromY);
                if (board.getPiece(currentPos) != null) {
                    return false; // Path is blocked
                }
                currentX += stepX;
            }
        } else if (Math.abs(deltaX) == Math.abs(deltaY)) {
            // Diagonal movement
            int stepX = Integer.compare(toX, fromX);
            int stepY = Integer.compare(toY, fromY);
            int currentX = fromX + stepX;
            int currentY = fromY + stepY;
            while (currentX != toX && currentY != toY) {
                Position currentPos = new Position(currentX, currentY);
                if (board.getPiece(currentPos) != null) {
                    return false; // Path is blocked
                }
                currentX += stepX;
                currentY += stepY;
            }
        } else {
            return false; // Invalid movement
        }

        // Check if destination has a friendly piece
        BaseChessPiece destinationPiece = board.getPiece(to);
        return destinationPiece == null || destinationPiece.getColor() != piece.getColor();
    }
}
