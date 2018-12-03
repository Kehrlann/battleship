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

    public ShotResult shoot(int x, int y) {
        if (!contains(x, y)) return ShotResult.MISSED;

        int hitIndex = getHitIndex(x, y);
        segmentsHit[hitIndex] = true;

        return isSunk() ? ShotResult.SUNK : ShotResult.HIT;
    }

    public ShipState state(int x, int y) {
        if (!contains(x, y)) return ShipState.UNKNOWN;
        if(isSunk()) return ShipState.SUNK;

        int hitIndex = getHitIndex(x, y);
        return segmentsHit[hitIndex] ? ShipState.DAMAGED : ShipState.UNKNOWN;
    }

    private boolean contains(int x, int y) {
        int horizontalLength = orientation == ShipOrientation.HORIZONTAL ? length : 1;
        int verticalLength = orientation == ShipOrientation.VERTICAL ? length : 1;

        boolean isWithinHorizontalBounds = x >= startingX && x < startingX + horizontalLength;
        boolean isWithinVerticalBounds = y >= startingY && y < startingY + verticalLength;
        return isWithinVerticalBounds && isWithinHorizontalBounds;
    }

    private int getHitIndex(int x, int y) {
        int hitIndex;
        if(orientation == ShipOrientation.VERTICAL) {
            hitIndex = y - startingY;
        } else {
            hitIndex = x - startingX;
        }
        return hitIndex;
    }

    private boolean isSunk() {
        for (boolean isHit : segmentsHit) {
            if (!isHit) return false;
        }
        return true;
    }
}
