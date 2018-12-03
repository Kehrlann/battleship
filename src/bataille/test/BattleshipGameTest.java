package bataille.test;


import bataille.BattleshipGame;
import bataille.Ship;
import bataille.ShipOrientation;
import bataille.ShotResult;
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
        Ship undamagedShip = new Ship(0, 0, 3, ShipOrientation.VERTICAL);
        BattleshipGame board = new BattleshipGame(undamagedShip);

        assertEquals(ShotResult.HIT, board.shoot(0, 0));
        assertEquals(ShotResult.MISSED, board.shoot(9, 9));
    }


}