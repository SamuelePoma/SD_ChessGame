package chessgame.pieces;

import chessgame.utils.PieceColor;
import chessgame.strategies.QueenMoveStrategy;

/**
 * Represents a Queen chess piece.
 */
public class QueenPiece extends BaseChessPiece {
    public QueenPiece(String name, PieceColor color) {
        super(name, color);
        this.moveStrategy = new QueenMoveStrategy();
    }

    @Override
    public String toString() {
        return (getColor() == PieceColor.WHITE) ? "♛" : "♕";
    }
}
