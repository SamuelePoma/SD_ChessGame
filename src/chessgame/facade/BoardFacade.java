package chessgame.facade;

import java.util.List;
import java.util.Map;
import chessgame.board.Board;
import chessgame.pieces.BaseChessPiece;
import chessgame.utils.Position;

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

    public BaseChessPiece getPiece(Position pos){
        return board.getPiece(pos);
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
        board.displayBoard();
    }
}
