package model.wartool;

public class FixedCatapult {
    private final int x;
    private  final int y;
    private final int efficiently=200;
    private final int board=200;

    public FixedCatapult(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getEfficiently() {
        return efficiently;
    }

    public int getBoard() {
        return board;
    }
}
