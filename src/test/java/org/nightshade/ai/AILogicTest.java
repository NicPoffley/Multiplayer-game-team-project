package org.nightshade.ai;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
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
    ArrayList<Sprite> waterSprites;
    ArrayList<Sprite> groundSprites;


    @Start
    public void start(Stage stage) {
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        aiLogic = new AILogic();
        ai = new AI(speed);
        levelGen = new LevelGen(120);
    }

    @Test
    public void testMoveChar(){
       platformSprites = levelGen.createPlatformSprites();
       waterSprites = levelGen.createWaterSprites();
       groundSprites = levelGen.createGroundSprites();
       aiLogic.moveChar(ai,platformSprites,waterSprites, groundSprites);
    }
}
