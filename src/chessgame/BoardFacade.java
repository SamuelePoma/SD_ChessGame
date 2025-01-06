package chessgame;

import java.util.List;
import java.util.Map;

public class BoardFacade {
    private Board board;

    // Constructor to initialize the board
    public BoardFacade() {
        this.board = new Board();
    }

    // Place a piece on the board
    public void placePiece(BaseChessPiece piece, Position pos) {
        board.placePiece(piece, pos);
    }

    // Move a piece on the board
    public boolean movePiece(Position from, Position to) {
        return board.movePiece(from, to);
    }

    // Get all pieces on the board
    public Map<Position, BaseChessPiece> getAllPieces() {
        return board.getAllPieces();
    }

    // Get possible moves for a piece
    public List<Position> getPossibleMoves(Position from) {
        return board.getPossibleMoves(from);
    }

    // Display the board
    public void displayBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                BaseChessPiece piece = board.getPiece(pos);
                System.out.print((piece != null ? piece.getName().charAt(0) : ".") + " ");
            }
            System.out.println();
        }
    }
}
