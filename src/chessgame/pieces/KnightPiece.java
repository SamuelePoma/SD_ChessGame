package chessgame.pieces;

import chessgame.strategies.KnightMoveStrategy;
import chessgame.utils.PieceColor;

/**
 * Represents a Knight chess piece.
 */
public class KnightPiece extends BaseChessPiece {
    public KnightPiece(String name, PieceColor color) {
        super(name, color);
        this.moveStrategy = new KnightMoveStrategy();
    }

    @Override
    public String toString() {
        return (getColor() == PieceColor.WHITE) ? "♞" : "♘";
    }
}
