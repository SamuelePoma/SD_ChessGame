package chessgame.decorators;

import chessgame.board.Board;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Concrete decorator that logs movements of chess pieces.
 */
public class LoggingDecorator extends ChessPieceDecorator {
    public LoggingDecorator(BaseChessPiece decoratedPiece) {
        super(decoratedPiece);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        boolean result = super.canMove(board, from, to);
        if (result) {
            System.out.println("[LOG] " + getName() + " performed a valid move: " + from + " -> " + to);
        } else {
            System.out.println("[LOG] Invalid move for " + getName() + ".");
        }
        return result;
    }
}
