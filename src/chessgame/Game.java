package chessgame;

public class Game {
    private static Game instance;
    private BoardFacade boardFacade;
    private GameState state;

    // Private constructor for Singleton
    private Game() {
        this.boardFacade = new BoardFacade();
    }

    // Singleton accessor
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Accessor for the facade
    public BoardFacade getBoardFacade() {
        return boardFacade;
    }

    // Getter and setter for state
    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
