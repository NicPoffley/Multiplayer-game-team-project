package org.nightshade.ai;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.game.Game;
import org.nightshade.game.Level;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class AITest {
    Renderer renderer;
    Level level;
    Game game;
    AI ai;
    int speed;
    ArrayList<Sprite> platformSprites;
    ArrayList<Sprite> waterSprites;

    @Start
    public void start(Stage stage) {
        speed=5;
        ai = new AI(Difficulty.EASY);
        game = new Game(stage);
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        level = new Level(120);
     //   platformSprites = level.createPlatformSprites();
     //   waterSprites = level.createLavaSprites();
    }

    @Test
    public void displaySprite(){
        Image image = new Image("Grass.png");
        ai.displaySprite(renderer,image,new Sprite(image,0,0));
    }

    @Test
    public void testGetWidth(){
        Assertions.assertEquals(40,ai.getSprite().getWidth());
    }

    @Test
    public void testGetVelocity(){
        Assertions.assertNotNull(ai.getVelocity());
    }

    @Test
    public void testSetVelocity(){
        ai.setVelocity(new Point2D(10,20));
        Assertions.assertNotNull(ai.getVelocity());
    }

    @Test
    public void testGetAISprite(){
        Assertions.assertNotNull(ai.getSprite());
    }
    @Test
    public void testJump(){
        AI aiNoJump = new AI(Difficulty.EASY);
        AI aiJump = new AI(Difficulty.EASY);
        aiJump.jump();
        Assertions.assertNotEquals(aiJump.getVelocity(),aiNoJump.getVelocity());
    }

    @Test
    public void testIsLive(){
        Assertions.assertTrue(ai.isAlive());
    }

    @Test
    public void testSetCanJump(){
        ai.setCanJump(false);
        ai.setCanJump(true);
    }

    @Test
    public void testMoveX(){
        Image image = new Image("Grass.png");
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                ai.displaySprite(renderer,image,ai.getSprite());
           //     ArrayList<Sprite> levelSprites = level.createGroundSprites();
                ArrayList<Sprite> sprites = new ArrayList<>();
                sprites.addAll(platformSprites);
            //    sprites.addAll(levelSprites);
                ai.moveX(sprites);
            }
        }.start();
    }

    @Test
    public void testMoveY(){
        Image image = new Image("Grass.png");
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                ai.displaySprite(renderer,image,ai.getSprite());
             //   ArrayList<Sprite> groundSprites = level.createGroundSprites();
                ArrayList<Sprite> sprites = new ArrayList<>();
                sprites.addAll(platformSprites);
            //    sprites.addAll(groundSprites);
                ai.moveY(sprites);
            }
        }.start();
    }

}
