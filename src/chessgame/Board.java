package chessgame;

import java.util.*;

public class Board {
    private Map<Position, BaseChessPiece> pieces = new HashMap<>();

    public void placePiece(BaseChessPiece piece, Position pos) {
        pieces.put(pos, piece);
    }

    public BaseChessPiece getPiece(Position pos) {
        return pieces.get(pos);
    }

    public boolean movePiece(Position from, Position to) {
        BaseChessPiece piece = pieces.get(from);
        if (piece == null) {
            System.out.println("No piece at " + from);
            return false;
        }

        // Calls piece.canMove(...) -> Decorator for logs
        if (piece.canMove(this, from, to)) {
            pieces.remove(from);
            pieces.put(to, piece);
            System.out.println(piece.getName() + " moved from " + from + " to " + to);
            return true;
        } else {
            System.out.println("Invalid move for " + piece.getName());
            return false;
        }
    }

    public Map<Position, BaseChessPiece> getAllPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public List<Position> getPossibleMoves(Position from) {
        BaseChessPiece piece = pieces.get(from);
        if (piece == null) {
            return Collections.emptyList();
        }

        MoveStrategy strategy = piece.getStrategy(); // we need getStrategy() in BaseChessPiece
        List<Position> validMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position to = new Position(row, col);
                if (!to.equals(from)) {
                    boolean isValid = false;

                    // For pawns, we call dryRunCanMove
                    if (strategy instanceof PawnMoveStrategy) {
                        isValid = ((PawnMoveStrategy) strategy).dryRunCanMove(piece, this, from, to);
                    }
                    // Add other piece types (Rook, Knight, etc.) here as needed

                    if (isValid) {
                        validMoves.add(to);
                    }
                }
            }
        }
        return validMoves;
    }
}
