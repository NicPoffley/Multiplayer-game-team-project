package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class GameTest {

    Renderer renderer;
    Game game;
    ArrayList<Sprite> platformSprites;
    LevelGen levelGen;


    @Start
    public void start(Stage stage) {
        game = new Game();
        game.initGame(stage);
        levelGen = new LevelGen(120);
        renderer = new Renderer(1280,720);
    }

    @Test
    public void testFullGame() {

        Image enemy = new Image("view/enemy.png");
        Image clientImg = new Image("view/Body.png");
        Image grass = new Image("Grass.png");
        Image grassLeft = new Image("GrassLeft.png");
        Image grassRight = new Image("GrassRight.png");
        Image water = new Image("Water.png");
        Image ground = new Image("Dirt.png");
        Image end = new Image("EndNode.png");
        platformSprites = levelGen.createPlatformSprites();

        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                game.gameLoop(platformSprites,grass,ground,water,enemy,end,clientImg);
            }
        }.start();
    }

}
