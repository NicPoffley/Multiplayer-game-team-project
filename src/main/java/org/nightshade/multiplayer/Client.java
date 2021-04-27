package org.nightshade.multiplayer;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.audio.SpotEffects;
import org.nightshade.gui.GuiHandler;
import org.nightshade.renderer.Renderer;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite sprite;
    private SpotEffects spotEffects;
    private Random random;
    public Client() {
        this.isAlive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.sprite = new Sprite(new Image("img/game/player.png"),300,50);
        this.spotEffects = new SpotEffects();
        this.random = new Random();
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
        return sprite;
    }
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
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
        isAlive =false;
        GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
    }
    public void moveX(int value, ArrayList<Sprite> platformSprites, ArrayList<Enemy> enemies, ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms){
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(sprite)){
                    if(movingRight){
                        getSprite().setX(getSprite().getX() - 1);
                    } else {
                        getSprite().setX(getSprite().getX() + 1);
                    }
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(sprite)){
                    if(movingRight){
                        getSprite().setX(getSprite().getX() - 1);
                    } else {
                        getSprite().setX(getSprite().getX() + 1);
                    }
                    return;
                }
            }
            for (MovingPlatform movingPlatform : movingPlatforms){
                if (movingPlatform.getSprite().intersects(sprite)){
                    if(movingRight){
                        getSprite().setX(getSprite().getX() - 1);
                    } else {
                        getSprite().setX(getSprite().getX() + 1);
                    }
                    return;
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.getSprite().intersects(sprite)){
                    kill();
                    return;
                }
            }
            getSprite().setX(getSprite().getX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int value, ArrayList<Sprite> platformSprites, ArrayList<Sprite> waterSprites, ArrayList<Enemy> enemies, ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms){
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(sprite) && movingDown) {
                    getSprite().setY(getSprite().getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(sprite) && movingDown) {
                    getSprite().setY(getSprite().getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite water : waterSprites) {
                if (water.intersects(sprite)){
                    getSprite().setY(getSprite().getY() + 1);
                    return;
                }
            }
            for (MovingPlatform mPlatform : movingPlatforms) {
                if (mPlatform.getSprite().intersects(sprite) && movingDown){
                    getSprite().setY(getSprite().getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.getSprite().intersects(sprite)) {
                    kill();
                    return;
                }
            }
            getSprite().setY(getSprite().getY() + (movingDown ? 1 : -1));
        }
    }
}