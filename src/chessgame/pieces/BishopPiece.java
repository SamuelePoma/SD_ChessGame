package chessgame.pieces;

import chessgame.strategies.BishopMoveStrategy;
import chessgame.utils.PieceColor;

/**
 * Represents a Bishop chess piece.
 */
public class BishopPiece extends BaseChessPiece {
    public BishopPiece(String name, PieceColor color) {
        super(name, color);
        this.moveStrategy = new BishopMoveStrategy();
    }

    @Override
    public String toString() {
        return (getColor() == PieceColor.WHITE) ? "♝" : "♗";
    }
}
