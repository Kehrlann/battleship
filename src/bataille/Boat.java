package bataille;

public class Boat {
    private final int startingX;
    private final int startingY;
    private final int length;
    private final Orientation orientation;
    private boolean[] segmentsHit;

    public Boat(int startingX, int startingY, int length, Orientation orientation) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.length = length;
        this.orientation = orientation;
        this.segmentsHit = new boolean[length];
    }

    public boolean contains(int x, int y) {
        int horizontalLength = orientation == Orientation.HORIZONTAL ? length : 1;
        int verticalLength = orientation == Orientation.VERTICAL ? length : 1;

        boolean isWithinHorizontalBounds = x >= startingX && x < startingX + horizontalLength;
        boolean isWithinVerticalBounds = y >= startingY && y < startingY + verticalLength;
        return isWithinVerticalBounds && isWithinHorizontalBounds;
    }

    public ShotResult shoot(int x, int y) {
        if (!contains(x, y)) return ShotResult.MISSED;

        int hitIndex;
        if(orientation == Orientation.VERTICAL) {
            hitIndex = y - startingY;
        } else {
            hitIndex = x - startingX;
        }
        segmentsHit[hitIndex] = true;

        for (boolean isHit : segmentsHit) {
            if (!isHit) return ShotResult.HIT;
        }
        return ShotResult.SUNK;
    }
}
