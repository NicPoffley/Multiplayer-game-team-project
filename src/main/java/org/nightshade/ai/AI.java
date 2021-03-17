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
        this.velocity = new Point2D(0, 0);
        int randomXStart = ThreadLocalRandom.current().nextInt(270, 330 + 1);
        int randomYStart = ThreadLocalRandom.current().nextInt(20, 60 + 1);

        this.sprite = new Sprite(new Image("view/GameComponents/AIBody.png"), randomXStart, randomYStart);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite) {
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

    public void moveX(ArrayList<Sprite> sprites) {
        int absoluteSpeed = Math.abs(speed);

        for (int i = 0; i < absoluteSpeed; i++) {
            for (Sprite sprite : sprites) {
                if (sprite.intersects(this.sprite)) {
                    if (speed > 0) {
                        // moving right
                        this.sprite.moveRight();
                    } else {
                        this.sprite.moveLeft();
                    }
                }
            }

            if (speed > 0) {
                // moving right
                sprite.moveLeft();
            } else {
                sprite.moveRight();
            }
        }
    }

    public void moveY(ArrayList<Sprite> sprites) {

        int speedY = (int) velocity.getY();
        int absoluteSpeed = Math.abs(speedY);

        for (int i = 0; i < absoluteSpeed; i++) {
            for (Sprite platform : sprites) {
                if (platform.intersects(sprite) && speedY > 0) {
                    sprite.setPositionY(sprite.getPositionY() - 1);
                    setCanJump(true);
                    return;
                }
            }

            if (speedY > 0) {
                sprite.setPositionY(sprite.getPositionY() + 1);
            } else {
                sprite.setPositionY(sprite.getPositionY() - 1);
            }
        }
    }
}