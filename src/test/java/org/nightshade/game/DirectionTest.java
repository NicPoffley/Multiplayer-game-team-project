package org.nightshade.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testGetRandomDirection() {
        Direction direction = Direction.getRandomDirection();
        Assertions.assertNotNull(direction);

        boolean hasBeenForward = false;
        boolean hasBeenBackward = false;

        for (int i = 0; i < 100; i ++) {
            direction = Direction.getRandomDirection();
            if (direction.equals(Direction.FORWARD)) {
                hasBeenForward = true;
            } else if (direction.equals(Direction.BACKWARD)) {
                hasBeenBackward = true;
            }
        }

        Assertions.assertTrue(hasBeenForward);
        Assertions.assertTrue(hasBeenBackward);
    }

}
