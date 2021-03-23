package org.nightshade.ai;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.game.LevelGen;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class AILogicTest {
    Renderer renderer;
    LevelGen levelGen;
    AI ai;
    AILogic aiLogic;
    int speed = 5;
    ArrayList<Sprite> platformSprites;
    ArrayList<Sprite> groundSprites;
    ArrayList<Sprite> sprites;


    @Start
    public void start(Stage stage) {
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        aiLogic = new AILogic();
        ai = new AI(Difficulty.EASY);
        levelGen = new LevelGen(120);
        platformSprites = levelGen.createPlatformSprites();
        groundSprites = levelGen.createGroundSprites();
        sprites = new ArrayList<>();
        sprites.addAll(platformSprites);
        sprites.addAll(groundSprites);
    }

    @Test
    public void testMoveChar(){
       aiLogic.moveSprite(ai, sprites);
    }
}
