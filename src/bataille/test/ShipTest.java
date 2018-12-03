package bataille.test;

import bataille.Ship;
import bataille.ShipOrientation;
import bataille.ShotResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void isSunk() {
        Ship undamaged = new Ship(0, 0, 1, ShipOrientation.VERTICAL);
        Ship damaged = new Ship(0, 0, 2, ShipOrientation.VERTICAL);
        Ship sunk = new Ship(0, 0, 1, ShipOrientation.VERTICAL);

        damaged.shoot(0, 0);
        sunk.shoot(0, 0);

        assertFalse(undamaged.isSunk());
        assertFalse(damaged.isSunk());
        assertTrue(sunk.isSunk());
    }
}