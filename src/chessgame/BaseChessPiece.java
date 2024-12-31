package chessgame;

public abstract class BaseChessPiece implements Cloneable {
    protected String name;
    protected PieceColor color;
    protected MoveStrategy moveStrategy;

    public BaseChessPiece(String name, PieceColor color, MoveStrategy moveStrategy) {
        this.name = name;
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    public String getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean canMove(Board board, Position from, Position to) {
        return moveStrategy.canMove(this, board, from, to);
    }

    public MoveStrategy getStrategy() {
        return this.moveStrategy;
    }

    @Override
    public BaseChessPiece clone() {
        try {
            return (BaseChessPiece) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
