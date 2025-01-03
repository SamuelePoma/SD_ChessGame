package chessgame.pieces;

import chessgame.strategies.PawnMoveStrategy;
import chessgame.utils.PieceColor;

/**
 * Represents a Pawn chess piece.
 */
public class PawnPiece extends BaseChessPiece {
    public PawnPiece(String name, PieceColor color) {
        super(name, color);
        this.moveStrategy = new PawnMoveStrategy();
    }

    @Override
    public String toString() {
        return (getColor() == PieceColor.WHITE) ? "♟" : "♙";
    }
}
