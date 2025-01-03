package chessgame.pieces;

import chessgame.strategies.KingMoveStrategy;
import chessgame.utils.PieceColor;

/**
 * Represents a King chess piece.
 */
public class KingPiece extends BaseChessPiece {
    public KingPiece(String name, PieceColor color) {
        super(name, color);
        this.moveStrategy = new KingMoveStrategy();
    }

    @Override
    public String toString() {
        return (getColor() == PieceColor.WHITE) ? "♚" : "♔";
    }
}
