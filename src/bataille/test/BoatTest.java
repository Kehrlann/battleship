package bataille.test;

import bataille.Boat;
import bataille.BoatState;
import bataille.Orientation;
import bataille.ShotResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoatTest {

    @Test
    public void contains() {
        Boat horizontal = new Boat(0, 0, 2, Orientation.HORIZONTAL);
        Boat vertical = new Boat(0, 0, 2, Orientation.VERTICAL);

        assertTrue(horizontal.contains(0, 0));
        assertTrue(horizontal.contains(1, 0));
        assertFalse(horizontal.contains(2, 0));
        assertFalse(horizontal.contains(0, 1));

        assertTrue(vertical.contains(0, 0));
        assertTrue(vertical.contains(0, 1));
        assertFalse(vertical.contains(0, 2));
        assertFalse(vertical.contains(1, 0));
    }

    @Test
    public void shoot() {
        Boat vertical = new Boat(0, 0, 2, Orientation.VERTICAL);
        Boat horizontal = new Boat(0, 0, 2, Orientation.HORIZONTAL);

        assertEquals(ShotResult.MISSED, vertical.shoot(42, 42));

        assertEquals(ShotResult.MISSED, vertical.shoot(0, 42));
        assertEquals(ShotResult.HIT, vertical.shoot(0, 0));
        assertEquals(ShotResult.HIT, vertical.shoot(0, 0));
        assertEquals(ShotResult.SUNK, vertical.shoot(0, 1));

        assertEquals(ShotResult.MISSED, horizontal.shoot(42, 0));
        assertEquals(ShotResult.HIT, horizontal.shoot(0, 0));
        assertEquals(ShotResult.SUNK, horizontal.shoot(1, 0));
    }
}