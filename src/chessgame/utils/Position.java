package chessgame.utils;

/**
 * Represents a position on the chessboard with row (x) and column (y).
 *
 * @param x Row (0-7, where 0 is the top)
 * @param y Column (0-7, where 0 is 'a')
 */
public record Position(int x, int y) {
    /**
     * Constructs a Position with specified row and column.
     *
     * @param x Row index (0-7)
     * @param y Column index (0-7)
     */
    public Position {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Position out of bounds");
        }
    }

    /**
     * Gets the row index.
     *
     * @return Row index (0-7)
     */
    @Override
    public int x() {
        return x;
    }

    /**
     * Gets the column index.
     *
     * @return Column index (0-7)
     */
    @Override
    public int y() {
        return y;
    }

    @Override
    public String toString() {
        char col = (char) ('a' + y);
        int row = 8 - x;
        return "" + col + row;
    }
}
