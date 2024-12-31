package chessgame;

public abstract class ChessPieceDecorator extends BaseChessPiece {
    protected BaseChessPiece decoratedPiece;

    public ChessPieceDecorator(BaseChessPiece piece) {
        super(piece.getName(), piece.getColor(), piece.getStrategy());
        this.decoratedPiece = piece;
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        return decoratedPiece.canMove(board, from, to);
    }

    @Override
    public BaseChessPiece clone() {
        ChessPieceDecorator cloned = (ChessPieceDecorator) super.clone();
        cloned.decoratedPiece = decoratedPiece.clone();
        return cloned;
    }
}
