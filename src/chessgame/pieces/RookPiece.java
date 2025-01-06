package chessgame.pieces;

import chessgame.utils.PieceColor;
import chessgame.strategies.RookMoveStrategy;

/**
 * Represents a Rook chess piece.
 */
public class RookPiece extends BaseChessPiece {
    public RookPiece(String name, PieceColor color) {
        super(name, color);
        this.moveStrategy = new RookMoveStrategy();
    }

    @Override
    public String toString() {
        return (getColor() == PieceColor.WHITE) ? "♖" : "♜";
    }
}
