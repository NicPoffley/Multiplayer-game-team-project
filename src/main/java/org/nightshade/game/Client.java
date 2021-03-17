package org.nightshade.game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.audio.BackgroundMusic;
import org.nightshade.audio.SpotEffects;
import org.nightshade.renderer.Renderer;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Client {

    private boolean isLive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite clientSprite;
    private SpotEffects spotEffects;
    private Random random;

    public Client() {
        this.isLive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.spotEffects = new SpotEffects();
        this.random = new Random();
        clientSprite = createSprite();
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public Point2D getVelocity() {
        return velocity;
    }


    public Sprite getClientSprite() {
        return clientSprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }


    public void jump() {
        if (canJump) {
            File soundFile = new File("src/main/resources/audio/jump_0" + random.nextInt(6) + ".mp3");
            spotEffects.playSound(soundFile, true);
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }


    public void kill() {
        File soundFile = new File("src/main/resources/audio/die.mp3");
        spotEffects.playSoundUntilEnd(soundFile, true);
        isLive=false;
    }

    public Sprite createSprite() {
        return new Sprite(new Image("view/GameComponents/Body.png"),300,50);
    }



    public void moveX(int value,ArrayList<Sprite> platformSprites,ArrayList<Enemy> enemies,ArrayList<Sprite> groundSprites){
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(clientSprite)){
                    if(movingRight){
                        getClientSprite().setPositionX(getClientSprite().getPositionX() - 1);
                    } else {
                        getClientSprite().setPositionX(getClientSprite().getPositionX() + 1);
                    }
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(clientSprite)){
                    if(movingRight){
                        getClientSprite().setPositionX(getClientSprite().getPositionX() - 1);
                    } else {
                        getClientSprite().setPositionX(getClientSprite().getPositionX() + 1);
                    }
                    return;
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.getEnemySprite().intersects(clientSprite)){
                    kill();
                    return;
                }
            }
            getClientSprite().setPositionX(getClientSprite().getPositionX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int value,ArrayList<Sprite> platformSprites,ArrayList<Sprite> waterSprites,ArrayList<Enemy> enemies,ArrayList<Sprite> groundSprites){
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(clientSprite) && movingDown) {
                    getClientSprite().setPositionY(getClientSprite().getPositionY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(clientSprite) && movingDown) {
                    getClientSprite().setPositionY(getClientSprite().getPositionY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite water : waterSprites) {
                if (water.intersects(clientSprite)){
                    getClientSprite().setPositionY(getClientSprite().getPositionY() + 1);
                    return;
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.getEnemySprite().intersects(clientSprite)) {
                    kill();
                    return;
                }
            }
            getClientSprite().setPositionY(getClientSprite().getPositionY() + (movingDown ? 1 : -1));
        }

    }


}
