package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class ParallaxTest {

    Renderer renderer;
    Stage tempStage;
    Parallax parallax;

    @Start
    public void start(Stage stage) {
        tempStage = stage;
        renderer = new Renderer(1280,720);
        parallax = new Parallax();
        System.nanoTime();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void showParallaxScroll(){
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                parallax.moveParallax();
                parallax.drawParallax(renderer,0);
            }
        }.start();
    }

}
