package bataille;

import java.util.Arrays;

public class BattleshipGame {

    public static final int BOARD_SIZE = 10;
    private final Ship[] ships;
    private final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public BattleshipGame(Ship... ships) {
        this.ships = ships;

        initializeBoard();
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
        // TODO: make immutable
        return board;
    }

    public ShotResult shoot(int x, int y) {
        for (Ship ship : ships) {
            ShotResult shot = ship.shoot(x, y);
            if (shot != ShotResult.MISSED) {
                updateBoardState();
                return shot;
            }
        }

        recordMissed(x, y);
        return ShotResult.MISSED;
    }

    private void recordMissed(int x, int y) {
        board[x][y] = State.MISS;
    }

    private void updateBoardState() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                boolean hitOrSunk = false;
                for (Ship ship : ships) {
                    switch (ship.state(row, column)) {
                        case SUNK:
                            board[row][column] = State.SUNK;
                            hitOrSunk = true;
                            break;
                        case DAMAGED:
                            board[row][column] = State.HIT;
                            hitOrSunk = true;
                            break;
                    }
                    if (hitOrSunk) break;
                }
            }
        }
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                board[row][column] = State.UNKNOWN;
            }
        }
    }
}
