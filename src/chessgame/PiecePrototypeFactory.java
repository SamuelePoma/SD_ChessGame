package chessgame;

import java.util.HashMap;
import java.util.Map;

/**
 * Mappa di "prototipi" di pezzi,
 * da cui cloneremo quando abbiamo bisogno di un nuovo pezzo
 */
public class PiecePrototypeFactory {
    private static Map<String, BaseChessPiece> prototypes = new HashMap<>();

    static {
        // Pedoni Bianchi e Neri
        prototypes.put("pawn-white", new PawnPiece("White Pawn", PieceColor.WHITE));
        prototypes.put("pawn-black", new PawnPiece("Black Pawn", PieceColor.BLACK));

        // Torri Bianche e Nere
        prototypes.put("rook-white", new RookPiece("White Rook", PieceColor.WHITE));
        prototypes.put("rook-black", new RookPiece("Black Rook", PieceColor.BLACK));

        // ... Aggiungi gli altri pezzi a tuo piacere
    }

    public static BaseChessPiece getPiece(String key) {
        BaseChessPiece prototype = prototypes.get(key);
        if (prototype != null) {
            // Ritorna un clone del prototipo
            return prototype.clone();
        }
        return null;
    }
}
