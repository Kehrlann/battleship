package bataille;

public class Ship {
    private final int startingX;
    private final int startingY;
    private final int length;
    private final ShipOrientation orientation;
    private boolean[] segmentsHit;

    public Ship(int startingX, int startingY, int length, ShipOrientation orientation) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.length = length;
        this.orientation = orientation;
        this.segmentsHit = new boolean[length];
    }

    private boolean contains(int x, int y) {
        int horizontalLength = orientation == ShipOrientation.HORIZONTAL ? length : 1;
        int verticalLength = orientation == ShipOrientation.VERTICAL ? length : 1;

        boolean isWithinHorizontalBounds = x >= startingX && x < startingX + horizontalLength;
        boolean isWithinVerticalBounds = y >= startingY && y < startingY + verticalLength;
        return isWithinVerticalBounds && isWithinHorizontalBounds;
    }

    public ShotResult shoot(int x, int y) {
        if (!contains(x, y)) return ShotResult.MISSED;

        int hitIndex;
        if(orientation == ShipOrientation.VERTICAL) {
            hitIndex = y - startingY;
        } else {
            hitIndex = x - startingX;
        }
        segmentsHit[hitIndex] = true;

        return isSunk() ? ShotResult.SUNK : ShotResult.HIT;
    }

    public boolean isSunk() {
        for (boolean isHit : segmentsHit) {
            if (!isHit) return false;
        }
        return true;
    }

    public ShipState state(int x, int y) {
        if (!contains(x, y)) return ShipState.UNKNOWN;
        if(isSunk()) return ShipState.SUNK;

        int hitIndex;
        if(orientation == ShipOrientation.VERTICAL) {
            hitIndex = y - startingY;
        } else {
            hitIndex = x - startingX;
        }

        return segmentsHit[hitIndex] ? ShipState.DAMAGED : ShipState.UNKNOWN;
    }
}
