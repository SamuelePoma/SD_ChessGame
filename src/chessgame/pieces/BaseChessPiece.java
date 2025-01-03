package chessgame.pieces;

import chessgame.board.Board;
import chessgame.strategies.MoveStrategy;
import chessgame.utils.PieceColor;
import chessgame.utils.Position;

/**
 * Abstract base class for all chess pieces.
 */
public abstract class BaseChessPiece implements Cloneable {
    private final String name;
    private final PieceColor color;
    protected MoveStrategy moveStrategy;

    /**
     * Constructs a chess piece with a name and color.
     *
     * @param name  The name of the piece.
     * @param color The color of the piece.
     */
    public BaseChessPiece(String name, PieceColor color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Gets the name of the piece.
     *
     * @return The name of the piece.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the color of the piece.
     *
     * @return The color of the piece.
     */
    public PieceColor getColor() {
        return color;
    }

    /**
     * Gets the move strategy of the piece.
     *
     * @return The move strategy.
     */
    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    /**
     * Determines if the piece can move from one position to another.
     *
     * @param board The current state of the chessboard.
     * @param from  The starting position.
     * @param to    The target position.
     * @return True if the move is valid, false otherwise.
     */
    public boolean canMove(Board board, Position from, Position to) {
        return moveStrategy.canMove(this, board, from, to);
    }

    @Override
    public BaseChessPiece clone() {
        try {
            return (BaseChessPiece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public abstract String toString();
}
