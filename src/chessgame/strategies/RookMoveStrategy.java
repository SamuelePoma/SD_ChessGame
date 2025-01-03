package chessgame.strategies;

import chessgame.board.Board;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Move strategy for Rook pieces.
 */
public class RookMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        int fromX = from.x();
        int fromY = from.y();
        int toX = to.x();
        int toY = to.y();

        // Rook moves horizontally or vertically
        if (fromX != toX && fromY != toY) {
            return false;
        }

        // Determine movement direction
        int stepX = Integer.compare(toX, fromX);
        int stepY = Integer.compare(toY, fromY);

        int currentX = fromX + stepX;
        int currentY = fromY + stepY;

        // Check for obstacles along the path
        while (currentX != toX || currentY != toY) {
            Position currentPos = new Position(currentX, currentY);
            if (board.getPiece(currentPos) != null) {
                return false; // Path is blocked
            }
            currentX += stepX;
            currentY += stepY;
        }

        // Check if destination has a friendly piece
        BaseChessPiece destinationPiece = board.getPiece(to);
        return destinationPiece == null || destinationPiece.getColor() != piece.getColor();
    }
}
