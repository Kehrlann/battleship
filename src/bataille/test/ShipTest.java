package bataille.test;

import bataille.Ship;
import bataille.ShipOrientation;
import bataille.ShipState;
import bataille.ShotResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShipTest {

    @Test
    public void shoot() {
        Ship vertical = new Ship(0, 0, 2, ShipOrientation.VERTICAL);
        Ship horizontal = new Ship(0, 0, 2, ShipOrientation.HORIZONTAL);

        assertEquals(ShotResult.MISSED, vertical.shoot(42, 42));

        assertEquals(ShotResult.MISSED, vertical.shoot(0, 42));
        assertEquals(ShotResult.HIT, vertical.shoot(0, 0));
        assertEquals(ShotResult.HIT, vertical.shoot(0, 0));
        assertEquals(ShotResult.SUNK, vertical.shoot(0, 1));

        assertEquals(ShotResult.MISSED, horizontal.shoot(42, 0));
        assertEquals(ShotResult.HIT, horizontal.shoot(0, 0));
        assertEquals(ShotResult.SUNK, horizontal.shoot(1, 0));
    }

    @Test
    public void shipState() {
        Ship undamaged = new Ship(0, 0, 1, ShipOrientation.HORIZONTAL);
        Ship damaged = new Ship(0, 0, 2, ShipOrientation.HORIZONTAL);
        Ship sunk = new Ship(0, 0, 1, ShipOrientation.HORIZONTAL);

        damaged.shoot(0, 0);
        sunk.shoot(0, 0);

        assertEquals(ShipState.UNKNOWN, undamaged.state(0, 0));
        assertEquals(ShipState.UNKNOWN, undamaged.state(10, 10));

        assertEquals(ShipState.DAMAGED, damaged.state(0, 0));
        assertEquals(ShipState.UNKNOWN, damaged.state(0, 1));
        assertEquals(ShipState.UNKNOWN, damaged.state(10, 10));

        assertEquals(ShipState.SUNK, sunk.state(0, 0));
        assertEquals(ShipState.UNKNOWN, sunk.state(10, 10));
    }


}