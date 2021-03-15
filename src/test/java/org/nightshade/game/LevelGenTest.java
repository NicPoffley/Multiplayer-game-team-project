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
public class LevelGenTest {

    Renderer renderer;
    LevelGen level;
    Game game;
    Stage tempStage;
    Sprite sprite;
    ArrayList<Sprite> platformSprites;
    LevelGen levelGen;


    @Start
    public void start(Stage stage) {
        game = new Game();
        sprite = new Sprite(new Image("Grass.png"),20,20);
        level = new LevelGen(120);
        tempStage = stage;
        renderer = new Renderer(1280,720);
        levelGen = new LevelGen(120);

        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void createPlatformSprites(){
        platformSprites = levelGen.createPlatformSprites(renderer);
        Assertions.assertNotNull(platformSprites);  //length is random so cannot use assertEquals
    }


    @Test
    public void testGetRandomNode(){
        Entity newNode = levelGen.getRandomEntity(0,0);
        Assertions.assertTrue(newNode == Entity.AIR || newNode == Entity.END || newNode == Entity.ENEMY || newNode == Entity.PLATFORM);
    }

    @Test
    public void testDrawPlatforms(){
        platformSprites = levelGen.createPlatformSprites(renderer);
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(new Image("Grass.png"), platformSprite.getPositionX(), platformSprite.getPositionY());
        }
    }

}
