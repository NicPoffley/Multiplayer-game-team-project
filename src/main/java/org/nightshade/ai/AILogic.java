package org.nightshade.ai;

import org.nightshade.game.Sprite;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AILogic {

    public void moveChar(AI ai, ArrayList<Sprite> platformSprites) {

        if (ai.getVelocity().getY() < 10) {
            ai.setVelocity(ai.getVelocity().add(0,1));
        }

        ai.moveX(platformSprites);

        for (Sprite platform : platformSprites) {

            int closeness = ThreadLocalRandom.current().nextInt(2, (30) + 1);

            if (ai.getSprite().getPositionX() + ai.getSprite().getWidth() + closeness > platform.getPositionX() &&
                    ai.getSprite().getPositionX() + 10 < platform.getPositionX() &&
                    ai.getSprite().getPositionY() > platform.getPositionY()) {
                ai.jump();
            }
        }

        ai.moveY(platformSprites);
    }
}
