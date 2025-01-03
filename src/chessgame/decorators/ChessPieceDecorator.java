package chessgame.decorators;

import chessgame.board.Board;
import chessgame.utils.PieceColor;
import chessgame.utils.Position;
import chessgame.pieces.BaseChessPiece;
import chessgame.strategies.MoveStrategy;

/**
 * Abstract decorator class for chess pieces.
 */
public abstract class ChessPieceDecorator extends BaseChessPiece {
    protected final BaseChessPiece decoratedPiece;

    public ChessPieceDecorator(BaseChessPiece decoratedPiece) {
        super(decoratedPiece.getName(), decoratedPiece.getColor());
        this.decoratedPiece = decoratedPiece;
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        return decoratedPiece.canMove(board, from, to);
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return decoratedPiece.getMoveStrategy();
    }

    @Override
    public String getName() {
        return decoratedPiece.getName();
    }

    @Override
    public PieceColor getColor() {
        return decoratedPiece.getColor();
    }

    @Override
    public BaseChessPiece clone() {
        return decoratedPiece.clone();
    }

    @Override
    public String toString() {
        return decoratedPiece.toString();
    }
}
