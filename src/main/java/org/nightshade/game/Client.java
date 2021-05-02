package org.nightshade.game;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.audio.SpotEffects;
import org.nightshade.gui.GuiHandler;
import org.nightshade.gui.SettingsController;
import org.nightshade.renderer.Renderer;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite sprite;
    public AnimatedSprite animatedSprite;
    private SpotEffects spotEffects;

    public double volume;

    private AnimatedImage animatedImage;

    private Random random;
    //public float volume1;
    public Ability ability;
    public int powerUpTimer;

    public Client() {
        this.isAlive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.animatedImage = new AnimatedImage();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("img/game/player_idle_0.png");
        imageArray[1] = new Image("img/game/player_idle_1.png");
        animatedImage.setFrames(imageArray);
        animatedImage.setDuration(0.150);
        this.animatedSprite = new AnimatedSprite(animatedImage, 300,50);
        this.sprite = new Sprite(new Image("img/game/player.png"),300,50);
        this.spotEffects = new SpotEffects();
        this.random = new Random();
        this.ability = null;
        this.powerUpTimer = 0;
        this.volume = SettingsController.mSliderVal / 100;
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
    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }


    public void displayAnimatedSprite(Renderer renderer, AnimatedImage animatedImage, Sprite sprite, double t){
        renderer.drawImage(animatedImage.getFrame(t), sprite.getX(), sprite.getY());

    }
    public void reducePowerUpTimer(){
        this.powerUpTimer = powerUpTimer-1;
    }
    private void setPowerUpTimer(){
        this.powerUpTimer = 99;
    }
    public void removeAbility(){
        this.ability = null;

    }
    public void jump() {
        if (canJump) {
            File soundFile = new File("src/main/resources/audio/jump_0" + random.nextInt(6) + ".mp3");
            spotEffects.playSound(soundFile, true, volume);
            if (this.ability == Ability.JUMPBOOST){
                velocity = velocity.add(0, -40);
            }else {
                velocity = velocity.add(0, -30);
            }

            canJump = false;
        }
    }
    public void kill() {
        File soundFile = new File("src/main/resources/audio/die.mp3");
        spotEffects.playSoundUntilEnd(soundFile, true, volume);
        isAlive =false;
        GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
    }

    public void moveX(int value, Level level){
        boolean isMovingRight = value > 0;
        if (isMovingRight) {
            animatedSprite.setAnimatedImage(AnimationType.RUNNING, Direction.FORWARD);
        } else {
            animatedSprite.setAnimatedImage(AnimationType.RUNNING, Direction.BACKWARD);
//            System.out.println(animatedSprite.getImage().getFrame(0).getUrl());
        }

        int speed =1;
        if (this.ability == Ability.SPEEDBOOST){
            speed = 2;
        }
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : level.getPlatformSprites()) {
                if (platform.intersects(animatedSprite)){
                    if(isMovingRight){
                        animatedSprite.setX(animatedSprite.getX() - 1);
                    } else {
                        animatedSprite.setX(animatedSprite.getX() + 1);
                    }
                    return;
                }
            }
            for (Sprite ground : level.getGroundSprites()) {
                if (ground.intersects(animatedSprite)){
                    File soundFile = new File("src/main/resources/audio/step.mp3");
                    spotEffects.playSoundUntilEnd(soundFile, true, volume);
                    if(isMovingRight){
                        animatedSprite.setX(animatedSprite.getX() - 1);
                    } else {
                        animatedSprite.setX(animatedSprite.getX() + 1);
                    }
                    return;
                }
            }
            for (PowerUp powerUp : level.getPowerUps()) {
                if (powerUp.intersects(animatedSprite)) {
                    System.out.println(1);
                    if (powerUp.getCollected() == false) {
                        System.out.println(2);
                        this.ability = powerUp.getAbility();
                        this.setPowerUpTimer();
                        powerUp.collect();
                    }
                }
            }

            for (MovingPlatform movingPlatform : level.getMovingPlatforms()){
                if (movingPlatform.getSprite().intersects(animatedSprite)){
                    if(isMovingRight){
                        animatedSprite.setX(animatedSprite.getX() - 1);
                    } else {
                        animatedSprite.setX(animatedSprite.getX() + 1);
                    }
                    return;
                }
            }

            for (Enemy enemy : level.getEnemies()) {
                if (enemy.getSprite().intersects(animatedSprite)){
                    if (this.ability == Ability.SHIELD){
                        return;
                    }else {
                        kill();
                    }
                    return;
                }
            }

            double newX;
            if (isMovingRight) {
                newX = animatedSprite.getX() + speed;
            } else {
                newX = animatedSprite.getX() - speed;
            }

            animatedSprite.setX(newX);

        }
    }

    public void moveY(int value,ArrayList<Sprite> platformSprites,ArrayList<Sprite> lavaSprites,ArrayList<Enemy> enemies,ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms, ArrayList<PowerUp> powerUps){
        boolean movingDown = value > 0;
//        animatedSprite.setAnimatedImage(AnimationType.IDLE, Direction.FORWARD);
        // TODO: above line stops the animation from working, figure a way to integrate this properly
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(animatedSprite) && movingDown) {
                    animatedSprite.setY(animatedSprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(animatedSprite) && movingDown) {
                    animatedSprite.setY(animatedSprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite lava : lavaSprites) {
                if (lava.intersects(animatedSprite)){
                    animatedSprite.setY(animatedSprite.getY() + 1);
                    if(lava.intersects(animatedSprite.getX(), animatedSprite.getY()-60, (int) Math. round(animatedSprite.getWidth()), (int) Math. round(animatedSprite.getHeight()))){
                        kill();
                    }
                    return;
                }
            }

            for (PowerUp powerUp : powerUps) {
                if (powerUp.intersects(animatedSprite)) {
                    System.out.println(1);
                    if (powerUp.getCollected() == false) {
                        System.out.println(2);
                        this.ability = powerUp.getAbility();
                        this.setPowerUpTimer();
                        powerUp.collect();
                    }
                }
            }

            for (MovingPlatform mPlatform : movingPlatforms) {
                if (mPlatform.getSprite().intersects(animatedSprite) && movingDown){
                    animatedSprite.setY(animatedSprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }

            for (Enemy enemy : enemies) {
                if (enemy.getSprite().intersects(animatedSprite)) {
                    if (this.ability != Ability.SHIELD){
                        kill();
                        return;
                    }else if (movingDown) {
                        getSprite().setY(getSprite().getY() - 1);
                        setCanJump(true);
                    }
                }
            }

            double newY;
            if (movingDown) {
                newY = animatedSprite.getY() + 1;
            } else {
                newY = animatedSprite.getY() - 1;
            }

            animatedSprite.setY(newY);

        }
    }
}