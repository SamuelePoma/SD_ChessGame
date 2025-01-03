package chessgame.strategies;

import chessgame.board.Board;
import chessgame.utils.PieceColor;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Strategy for pawn movement, including special moves like en passant and promotion.
 */
public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        // Calculate direction based on piece color
        int direction = (piece.getColor() == PieceColor.WHITE) ? -1 : 1;
        int startRow = (piece.getColor() == PieceColor.WHITE) ? 6 : 1;

        int fromX = from.x();
        int fromY = from.y();
        int toX = to.x();
        int toY = to.y();

        // Check if the target position is within bounds
        if (toX < 0 || toX >= 8 || toY < 0 || toY >= 8) {
            return false;
        }

        // Forward move
        if (fromY == toY) {
            // Single step forward
            if (toX - fromX == direction && board.getPiece(to) == null) {
                return true;
            }
            // Double step forward from starting position
            if (fromX == startRow && toX - fromX == 2 * direction) {
                Position intermediate = new Position(fromX + direction, fromY);
                if (board.getPiece(intermediate) == null && board.getPiece(to) == null) {
                    return true;
                }
            }
        }

        // Diagonal capture
        if (Math.abs(toY - fromY) == 1 && toX - fromX == direction) {
            BaseChessPiece targetPiece = board.getPiece(to);
            return targetPiece != null && targetPiece.getColor() != piece.getColor();
        }

        return false;
    }
}
