package chessgame;

public class PawnPiece extends BaseChessPiece {

    public PawnPiece(String name, PieceColor color) {
        super(name, color, new PawnMoveStrategy(color));
    }
}
