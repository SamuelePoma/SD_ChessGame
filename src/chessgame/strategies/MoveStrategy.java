package chessgame.strategies;

import chessgame.board.Board;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;

/**
 * Interface defining the movement strategy for chess pieces.
 */
public interface MoveStrategy {
    /**
     * Determines if a piece can move from one position to another on the board.
     *
     * @param piece The chess piece attempting to move.
     * @param board The current state of the chessboard.
     * @param from  The starting position.
     * @param to    The target position.
     * @return True if the move is valid, false otherwise.
     */
    boolean canMove(BaseChessPiece piece, Board board, Position from, Position to);
}
