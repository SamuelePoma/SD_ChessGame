package chessgame;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        if (from.getRow() == to.getRow() || from.getCol() == to.getCol()) {
            return true;
        }
        return false;
    }
}
