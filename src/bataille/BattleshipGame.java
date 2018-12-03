package bataille;

public class BattleshipGame {

    public static final int BOARD_SIZE = 10;
    private final Ship[] ships;

    public BattleshipGame(Ship ...ships) {
        this.ships = ships;
    }

    public BattleshipGame() {
        this(new Ship[0]);
    }

    public static class State {
        public static final char UNKNOWN = '~';
        public static final char MISS = 'o';
        public static final char HIT = '-';
        public static final char SUNK = 'x';
    }

    public char[][] boardState() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                board[row][column] = State.UNKNOWN;
            }
        }
        return board;
    }

    public ShotResult shoot(int x, int y) {
        for(Ship ship: ships) {
            ShotResult shot = ship.shoot(x, y);
            if(shot != ShotResult.MISSED) {
               return shot;
            }
        }

        return ShotResult.MISSED;
    }
}
