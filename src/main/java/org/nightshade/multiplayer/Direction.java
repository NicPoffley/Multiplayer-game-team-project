package org.nightshade.multiplayer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    FORWARD, BACKWARD;

    public static Direction getRandomDirection() {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(FORWARD);
        directions.add(BACKWARD);
        return directions.get(ThreadLocalRandom.current().nextInt(directions.size()));
    }
}
