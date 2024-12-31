package chessgame;

public interface MoveStrategy {
    /**
     * @param piece The piece you want to move
     * @param board The board
     * @param from  Start position
     * @param to    End position
     * @return true if the movement is possible, otherwise false
     */
    boolean canMove(BaseChessPiece piece, Board board, Position from, Position to);
}
