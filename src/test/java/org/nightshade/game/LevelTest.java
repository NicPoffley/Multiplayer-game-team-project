package org.nightshade.game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class LevelTest {

    Renderer renderer;
    Level level;
    Game game;
    Stage tempStage;
    Sprite sprite;
    ArrayList<Sprite> platformSprites;
    ArrayList<MovingPlatform> movingPlatforms;


    @Start
    public void start(Stage stage) {
        game = new Game(stage);
        sprite = new Sprite(new Image("Grass.png"),20,20);
        level = new Level(120);
        tempStage = stage;
        renderer = new Renderer(1280,720);
        level = new Level(120);

        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }
/*
    @Test
    public void createPlatformSprites(){
        platformSprites = level.createPlatformSprites();
        Assertions.assertNotNull(platformSprites);  //length is random so cannot use assertEquals
    }

    @Test
    public void testCreateMovingPlatforms(){
        movingPlatforms = level.createMovingPlatforms();
        Assertions.assertNotNull(movingPlatforms);
    }


    @Test
    public void testGetRandomNode(){
        Node newNode = level.getRandomNode(0,0);
        Assertions.assertTrue(newNode == Node.AIR || newNode == Node.END || newNode == Node.ENEMY || newNode == Node.PLATFORM);
    }

    @Test
    public void testDrawPlatforms(){
        platformSprites = level.createPlatformSprites();
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(new Image("Grass.png"), platformSprite.getX(), platformSprite.getY());
        }
    }
*/
}
