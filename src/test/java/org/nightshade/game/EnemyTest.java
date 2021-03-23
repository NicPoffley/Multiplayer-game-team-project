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

@ExtendWith(ApplicationExtension.class)
public class EnemyTest {
    Renderer renderer;
    Enemy enemy;
    int speed;

    @Start
    public void start(Stage stage) {
        speed=5;
        enemy = new Enemy(speed,50, 50);
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testGetEnemySprite(){
        Assertions.assertNotNull(enemy.getSprite());
    }

    @Test
    public void testMoveEnemy(){
        int oldPosition = enemy.getSprite().getX();
        enemy.moveEnemy();
        int newPosition = enemy.getSprite().getX();
        Assertions.assertEquals(speed,newPosition-oldPosition);
    }
}
