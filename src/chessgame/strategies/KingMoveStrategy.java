package chessgame.strategies;

import chessgame.board.Board;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Move strategy for King pieces.
 */
public class KingMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        int fromX = from.x();
        int fromY = from.y();
        int toX = to.x();
        int toY = to.y();

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        // King moves one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        if (deltaX == 0 && deltaY == 0) {
            return false; // No movement
        }

        // Check if destination has a friendly piece
        BaseChessPiece destinationPiece = board.getPiece(to);
        return destinationPiece == null || destinationPiece.getColor() != piece.getColor();
    }
}
