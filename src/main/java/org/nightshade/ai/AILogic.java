package org.nightshade.ai;

import org.nightshade.game.Sprite;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AILogic {

    public void moveSprite(AI ai, ArrayList<Sprite> sprites) {

        if (ai.getVelocity().getY() < 10) {
            ai.setVelocity(ai.getVelocity().add(0, 1));
        }

        ai.moveX(sprites);

        for (Sprite sprite : sprites) {
            int closeness = ThreadLocalRandom.current().nextInt(2, (30) + 1);
            if (ai.getSprite().getX() + ai.getSprite().getWidth() + closeness > sprite.getX() &&
                    ai.getSprite().getX() + 10 < sprite.getX() &&
                    ai.getSprite().getY() > sprite.getY()) {
                ai.jump();
            }
        }
        ai.moveY(sprites);
    }
}