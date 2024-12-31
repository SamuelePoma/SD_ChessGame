package chessgame;

public class LoggingDecorator extends ChessPieceDecorator {
    public LoggingDecorator(BaseChessPiece piece) {
        super(piece);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        boolean result = super.canMove(board, from, to);
        if (result) {
            System.out.println("[LOG] " + getName() + " performed a valid move: " + from + " -> " + to);
        } else {
            System.out.println("[LOG] Invalid move for " + getName());
        }
        return result;
    }
}
