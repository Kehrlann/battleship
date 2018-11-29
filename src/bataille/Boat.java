package bataille;

public class Boat {
    private final int startingX;
    private final int startingY;
    private final int length;
    private final Orientation orientation;

    public Boat(int startingX, int startingY, int length, Orientation orientation) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.length = length;
        this.orientation = orientation;
    }

    public boolean contains(int x, int y) {
        int horizontalLength = orientation == Orientation.HORIZONTAL ? length : 1;
        int verticalLength = orientation == Orientation.VERTICAL ? length : 1;

        boolean isWithinHorizontalBounds = x >= startingX && x < startingX + horizontalLength;
        boolean isWithinVerticalBounds = y >= startingY && y < startingY + verticalLength;
        return isWithinVerticalBounds && isWithinHorizontalBounds;
    }

    public boolean shoot(int x, int y) {
        return contains(x, y);
    }
}
