package org.nightshade.multiplayer;
import javafx.scene.image.Image;

public class Enemy {

    private final Sprite sprite;
    private final int speed;
    private Direction direction;
    private int offset;

    public Enemy(int speed, int x, int y) {
        Image spriteImage = new Image("img/game/enemy.png");
        this.sprite = new Sprite(spriteImage, x, y);
        this.speed = speed;
        this.offset = 0;
        this.direction = Direction.getRandomDirection();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void moveEnemy() {
        if (this.direction.equals(Direction.FORWARD)) {
            if (offset > 180) {
                direction = Direction.BACKWARD;
            } else {
                offset += speed;
                sprite.setX(sprite.getX() + speed);
            }
        } else {
            if (offset < -180) {
                direction = Direction.FORWARD;
            } else {
                offset -= speed;
                sprite.setX(sprite.getX() - speed);
            }
        }
    }
}