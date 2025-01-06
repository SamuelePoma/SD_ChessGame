package chessgame;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        
        // Initialize the game in SetupState
        game.setState(new SetupState(game));
        game.getState().execute();

        // Transition to PlayState
        game.setState(new PlayState(game));
        game.getState().execute();
    }
}
