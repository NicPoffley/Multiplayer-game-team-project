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
    ArrayList<Sprite> movingPlatformSprites;
    Level level;


    @Start
    public void start(Stage stage) {
        game = new Game(stage);
        level = new Level(120);
        renderer = new Renderer(1280,720);
    }

    @Test
    public void testFullGame() {

        Image enemy = new Image("img/game/enemy.png");
        Image clientImg = new Image("img/game/player.png");
        Image grass = new Image("Grass.png");
        Image ground = new Image("Dirt.png");
        Image end = new Image("EndNode.png");
        Image aiImg = new Image("AIBody.png");
      //  platformSprites = level.createPlatformSprites();


        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
             //   game.loop();
            }
        }.start();
    }

}
