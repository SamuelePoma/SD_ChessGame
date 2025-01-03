package chessgame.strategies;

import chessgame.board.Board;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Move strategy for Knight pieces.
 */
public class KnightMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        int fromX = from.x();
        int fromY = from.y();
        int toX = to.x();
        int toY = to.y();

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        // Check for 'L' shaped movement
        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            return false;
        }

        // Check if destination has a friendly piece
        BaseChessPiece destinationPiece = board.getPiece(to);
        return destinationPiece == null || destinationPiece.getColor() != piece.getColor();
    }
}
