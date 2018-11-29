package bataille.test;

import bataille.Boat;
import bataille.BoatState;
import bataille.Orientation;
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
        Boat boat = new Boat(0, 0, 1, Orientation.VERTICAL);
        assertTrue(boat.shoot(0, 0));
        assertFalse(boat.shoot(1, 1));
    }
}