package bataille;


import org.junit.Test;

import static bataille.BattleshipGame.BOARD_SIZE;
import static org.junit.Assert.assertEquals;

public class BattleshipGameTest {

    @Test
    public void state() {
        Ship undamagedShip = new Ship(0, 0, 3, ShipOrientation.VERTICAL);
        BattleshipGame noShotsFired = new BattleshipGame(undamagedShip);

        char[][] state = noShotsFired.boardState();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                assertEquals('~', state[row][column]);
            }
        }
    }

    @Test
    public void shoot() {
        Ship damaged = new Ship(0, 0, 3, ShipOrientation.VERTICAL);
        Ship sunk = new Ship(4, 4, 2, ShipOrientation.VERTICAL);
        BattleshipGame board = new BattleshipGame(damaged, sunk);

        assertEquals(ShotResult.HIT, board.shoot(0, 0));
        assertEquals(ShotResult.MISSED, board.shoot(9, 9));
        assertEquals(ShotResult.HIT, board.shoot(4, 4));
        assertEquals(ShotResult.SUNK, board.shoot(4, 5));

        assertEquals('@', board.boardState()[0][0]);
        assertEquals('o', board.boardState()[9][9]);
        assertEquals('x', board.boardState()[4][4]);
        assertEquals('x', board.boardState()[4][5]);
    }

    @Test
    public void endGame() {
        Ship one = new Ship(0, 0, 1, ShipOrientation.VERTICAL);
        Ship two = new Ship(1, 0, 2, ShipOrientation.VERTICAL);
        BattleshipGame board = new BattleshipGame(one, two);

        assertEquals(false, board.gameOver());
        board.shoot(1, 0);
        assertEquals(false, board.gameOver());
        board.shoot(0, 0);
        assertEquals(false, board.gameOver());
        board.shoot(1, 0);
        assertEquals(false, board.gameOver());
        board.shoot(1, 1);
        assertEquals(true, board.gameOver());
    }


}