package chessgame;

public class SetupState implements GameState {
    private Game game;

    public SetupState(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        BoardFacade boardFacade = game.getBoardFacade();

        // Place white pawns
        for (int col = 0; col < 8; col++) {
            BaseChessPiece whitePawn = new PawnPiece("White Pawn", PieceColor.WHITE);
            if (col == 0) {
                whitePawn = new LoggingDecorator(whitePawn);
            }
            boardFacade.placePiece(whitePawn, new Position(6, col));
        }

        // Place black pawns
        for (int col = 0; col < 8; col++) {
            BaseChessPiece blackPawn = new PawnPiece("Black Pawn", PieceColor.BLACK);
            if (col == 0) {
                blackPawn = new LoggingDecorator(blackPawn);
            }
            boardFacade.placePiece(blackPawn, new Position(1, col));
        }

        System.out.println("\n=== Board Setup Complete ===");
        boardFacade.displayBoard();
    }
}
