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
public class SpriteTest {

    Renderer renderer;
    LevelGen level;
    Game game;

    @Start
    public void start(Stage stage) {
        game = new Game();
        renderer = new Renderer();
        level = new LevelGen(120);
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void testDrawSprite(){
        Image image = new Image("Grass.png");
        Sprite sprite = new Sprite(image,40,40);
        sprite.setPosition(100,100);
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }

    @Test
    public void testIntersects(){
        Image image = new Image("Grass.png");
        Sprite spriteOne = new Sprite(image,40,40);
        Sprite spriteTwo = new Sprite(image,70,40);
        renderer.drawImage(image,0,0);
        Assertions.assertTrue(spriteOne.intersects(spriteTwo));
        spriteOne.setPositionX(800);
        Assertions.assertFalse(spriteOne.intersects(spriteTwo));
    }

    @Test
    public void testGetHeight(){
        Image image = new Image("Grass.png");
        //image is of height 60
        Sprite sprite = new Sprite(image,0,0);
        Assertions.assertEquals(60,sprite.getHeight());
    }

    @Test
    public void testGetWidth(){
        Image image = new Image("Grass.png");
        //image is of width 60
        Sprite sprite = new Sprite(image,0,0);
        Assertions.assertEquals(60,sprite.getWidth());
    }

    @Test
    public void testGetPositionX(){
        Image image = new Image("Grass.png");
        Sprite sprite = new Sprite(image,0,0);
        Assertions.assertEquals(0,sprite.getPositionX());
        sprite.setPositionX(50);
        Assertions.assertEquals(50,sprite.getPositionX());
    }

    @Test
    public void testGetPositionY(){
        Image image = new Image("Grass.png");
        Sprite sprite = new Sprite(image,0,0);
        Assertions.assertEquals(0,sprite.getPositionY());
        sprite.setPositionY(50);
        Assertions.assertEquals(50,sprite.getPositionY());
    }
}
