package chessgame;

public class PawnMoveStrategy implements MoveStrategy {

    private PieceColor color;

    public PawnMoveStrategy(PieceColor color) {
        this.color = color;
    }

    /**
     * The core logic of pawn movement:
     *  - Move 1 step forward.
     *  - Move 2 steps forward if still on the starting row.
     *  - Diagonal capture if an enemy piece is there.
     */
    private boolean canMoveLogic(BaseChessPiece piece, Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // White pawns move upward (row decreases)
        if (color == PieceColor.WHITE) {
            // 1) Single step
            if (rowDiff == -1 && colDiff == 0) {
                return (board.getPiece(to) == null);
            }
            // 2) Diagonal capture
            if (rowDiff == -1 && Math.abs(colDiff) == 1) {
                BaseChessPiece target = board.getPiece(to);
                return (target != null && target.getColor() == PieceColor.BLACK);
            }
            // 3) Double step if still on row 6
            if (from.getRow() == 6 && rowDiff == -2 && colDiff == 0) {
                int fromRow = from.getRow();
                int col = from.getCol();
                Position step1 = new Position(fromRow - 1, col); // row 5
                Position step2 = new Position(fromRow - 2, col); // row 4
                return (board.getPiece(step1) == null && board.getPiece(step2) == null);
            }
        }

        // Black pawns move downward (row increases)
        if (color == PieceColor.BLACK) {
            // 1) Single step
            if (rowDiff == 1 && colDiff == 0) {
                return (board.getPiece(to) == null);
            }
            // 2) Diagonal capture
            if (rowDiff == 1 && Math.abs(colDiff) == 1) {
                BaseChessPiece target = board.getPiece(to);
                return (target != null && target.getColor() == PieceColor.WHITE);
            }
            // 3) Double step if still on row 1
            if (from.getRow() == 1 && rowDiff == 2 && colDiff == 0) {
                int fromRow = from.getRow();
                int col = from.getCol();
                Position step1 = new Position(fromRow + 1, col); // row 2
                Position step2 = new Position(fromRow + 2, col); // row 3
                return (board.getPiece(step1) == null && board.getPiece(step2) == null);
            }
        }

        return false;
    }

    @Override
    public boolean canMove(BaseChessPiece piece, Board board, Position from, Position to) {
        return canMoveLogic(piece, board, from, to);
    }

    /**
     * This "silent" version is used when calculating possible moves,
     * so we don't spam logs from a decorator.
     */
    public boolean dryRunCanMove(BaseChessPiece piece, Board board, Position from, Position to) {
        return canMoveLogic(piece, board, from, to);
    }
}
