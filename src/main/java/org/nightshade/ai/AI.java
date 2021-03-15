package org.nightshade.ai;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AI {

    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private Sprite sprite;
    private int speed;

    public AI(int speed) {
        this.isAlive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.sprite = new Sprite(new Image("view/Body.png"),300,50);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void jump() {
        if (canJump) {
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    public void moveX(ArrayList<Sprite> platformSprites) {
        int absoluteSpeed = Math.abs(speed);

        for (int i = 0; i < absoluteSpeed; i ++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(sprite)) {
                    if (speed > 0) {
                        // moving right
                        sprite.moveRight();
                    } else {
                        sprite.moveLeft();
                    }
                }
                return;
            }

            if (speed > 0) {
                // moving right
                sprite.moveLeft();
            } else {
                sprite.moveRight();
            }
        }
    }

    public void moveY(ArrayList<Sprite> platformSprites) {
        int speedY = (int)velocity.getY();

        int absoluteSpeed = Math.abs(speedY);

        // check if sprite intersects platform sprite
        for (int i = 0; i < absoluteSpeed; i ++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(sprite) && speedY > 0) {
                    // moving down and hit platform
                    sprite.moveUp();
                    canJump = true;
                    return;
                }
            }

            if (speedY > 0) {
                // moving down
                sprite.moveDown();
            } else {
                sprite.moveUp();
            }
        }
    }
}