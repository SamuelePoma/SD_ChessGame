package chessgame.factory;

import chessgame.utils.PieceColor;
import chessgame.pieces.*;

import java.util.Map;

/**
 * Factory class for creating chess pieces using the Prototype pattern.
 */
public class PiecePrototypeFactory {
    private static final Map<String, BaseChessPiece> prototypes;

    static {
        prototypes = Map.ofEntries(Map.entry("pawn-white", new PawnPiece("White Pawn", PieceColor.WHITE)),
                Map.entry("pawn-black", new PawnPiece("Black Pawn", PieceColor.BLACK)),
                Map.entry("rook-white", new RookPiece("White Rook", PieceColor.WHITE)),
                Map.entry("rook-black", new RookPiece("Black Rook", PieceColor.BLACK)),
                Map.entry("knight-white", new KnightPiece("White Knight", PieceColor.WHITE)),
                Map.entry("knight-black", new KnightPiece("Black Knight", PieceColor.BLACK)),
                Map.entry("bishop-white", new BishopPiece("White Bishop", PieceColor.WHITE)),
                Map.entry("bishop-black", new BishopPiece("Black Bishop", PieceColor.BLACK)),
                Map.entry("queen-white", new QueenPiece("White Queen", PieceColor.WHITE)),
                Map.entry("queen-black", new QueenPiece("Black Queen", PieceColor.BLACK)),
                Map.entry("king-white", new KingPiece("White King", PieceColor.WHITE)),
                Map.entry("king-black", new KingPiece("Black King", PieceColor.BLACK)));
    }

    /**
     * Retrieves a cloned instance of the specified piece.
     *
     * @param pieceKey The key identifying the piece prototype.
     * @return A new instance of the requested chess piece.
     */
    public static BaseChessPiece getPiece(String pieceKey) {
        BaseChessPiece prototype = prototypes.get(pieceKey);
        if (prototype != null) {
            return prototype.clone();
        }
        throw new IllegalArgumentException("Invalid piece key: " + pieceKey);
    }
}
