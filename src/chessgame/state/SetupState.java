package chessgame.state;

import chessgame.facade.BoardFacade;
import chessgame.factory.PiecePrototypeFactory;
import chessgame.singleton.Game;
import chessgame.utils.Position;
import chessgame.pieces.*;
import chessgame.decorators.LoggingDecorator;

public class SetupState implements GameState {
    private Game game;

    public SetupState(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        BoardFacade boardFacade = game.getBoardFacade();

        // ------------------------------------------------
        // 1) Create and place white pawns (row 6)
        // ------------------------------------------------
        initializePawns(boardFacade, "pawn-white", 6);

        // ------------------------------------------------
        // 2) Create and place black pawns (row 1)
        // ------------------------------------------------
        initializePawns(boardFacade, "pawn-black", 1);

        // ------------------------------------------------
        // 3) Create and place Rooks
        // ------------------------------------------------
        initializeRooks(boardFacade);

        // ------------------------------------------------
        // 4) Create and place Bishops
        // ------------------------------------------------
        initializeBishops(boardFacade);

        // ------------------------------------------------
        // 5) Create and place Knights
        // ------------------------------------------------
        initializeKnights(boardFacade);

        // ------------------------------------------------
        // 6) Create and place Queens and Kings
        // ------------------------------------------------
        initializeQueensAndKings(boardFacade);

        System.out.println("\n=== Board Setup Complete ===");
    }

    /**
     * Initializes and places pawns on the board.
     *
     * @param board The chess board.
     * @param pieceKey The key for the piece type and color.
     * @param row The row where pawns should be placed.
     */
    private static void initializePawns(BoardFacade board, String pieceKey, int row) {
        for (int col = 0; col < 8; col++) {
            BaseChessPiece pawn = PiecePrototypeFactory.getPiece(pieceKey);
            pawn = new LoggingDecorator(pawn); // Optional decoration
            board.placePiece(pawn, new Position(row, col));
        }
    }

    /**
     * Initializes and places rooks on the board.
     *
     * @param board The chess board.
     */
    private static void initializeRooks(BoardFacade board) {
        // White Rooks at (7,0) and (7,7) -> a1 and h1
        placeMultiplePieces(board, "rook-white", new Position[]{new Position(7, 0), new Position(7, 7)});

        // Black Rooks at (0,0) and (0,7) -> a8 and h8
        placeMultiplePieces(board, "rook-black", new Position[]{new Position(0, 0), new Position(0, 7)});
    }

    /**
     * Initializes and places bishops on the board.
     *
     * @param board The chess board.
     */
    private static void initializeBishops(BoardFacade board) {
        // White Bishops at (7,2) and (7,5) -> c1 and f1
        placeMultiplePieces(board, "bishop-white", new Position[]{new Position(7, 2), new Position(7, 5)});

        // Black Bishops at (0,2) and (0,5) -> c8 and f8
        placeMultiplePieces(board, "bishop-black", new Position[]{new Position(0, 2), new Position(0, 5)});
    }

    /**
     * Initializes and places knights on the board.
     *
     * @param board The chess board.
     */
    private static void initializeKnights(BoardFacade board) {
        // White Knights at (7,1) and (7,6) -> b1 and g1
        placeMultiplePieces(board, "knight-white", new Position[]{new Position(7, 1), new Position(7, 6)});

        // Black Knights at (0,1) and (0,6) -> b8 and g8
        placeMultiplePieces(board, "knight-black", new Position[]{new Position(0, 1), new Position(0, 6)});
    }

    /**
     * Initializes and places queens and kings on the board.
     *
     * @param board The chess board.
     */
    private static void initializeQueensAndKings(BoardFacade board) {
        // White Queen at (7,3) and White King at (7,4) -> d1 and e1
        placeMultiplePieces(board, "queen-white", new Position[]{new Position(7, 3)});
        placeMultiplePieces(board, "king-white", new Position[]{new Position(7, 4)});

        // Black Queen at (0,3) and Black King at (0,4) -> d8 and e8
        placeMultiplePieces(board, "queen-black", new Position[]{new Position(0, 3)});
        placeMultiplePieces(board, "king-black", new Position[]{new Position(0, 4)});
    }

    /**
     * Places multiple pieces on the board.
     *
     * @param board The chess board.
     * @param pieceKey The key for the piece type and color.
     * @param positions The positions where pieces should be placed.
     */
    private static void placeMultiplePieces(BoardFacade board, String pieceKey, Position[] positions) {
        for (Position pos : positions) {
            BaseChessPiece piece = PiecePrototypeFactory.getPiece(pieceKey);
            piece = new LoggingDecorator(piece); // Optional decoration
            board.placePiece(piece, pos);
        }
    }
}
