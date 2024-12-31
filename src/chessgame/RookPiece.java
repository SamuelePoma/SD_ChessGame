package chessgame;

public class RookPiece extends BaseChessPiece {

    public RookPiece(String name, PieceColor color) {
        super(name, color, new RookMoveStrategy());
    }
}
