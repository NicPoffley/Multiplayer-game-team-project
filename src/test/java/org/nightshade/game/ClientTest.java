package org.nightshade.game;

import javafx.geometry.Point2D;
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
public class ClientTest {
    Renderer renderer;
    LevelGen levelGen;
    Game game;
    Client client;
    int speed;
    ArrayList<Sprite> platformSprites;

    @Start
    public void start(Stage stage) {
        speed=5;
        client = new Client();
        game = new Game();
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        levelGen = new LevelGen(120);
        platformSprites = levelGen.createPlatformSprites();
    }

    @Test
    public void displaySprite(){
        Image image = new Image("Grass.png");
        client.displaySprite(renderer,image,new Sprite(image,0,0));
    }

    @Test
    public void testGetVelocity(){
        Assertions.assertNotNull(client.getVelocity());
    }

    @Test
    public void testSetVelocity(){
        client.setVelocity(new Point2D(10,20));
        Assertions.assertNotNull(client.getVelocity());
    }

    @Test
    public void testGetClientSprite(){
        Assertions.assertNotNull(client.getClientSprite());
    }

    @Test
    public void testJump(){
        Client clientNoJump = new Client();
        Client clientJump = new Client();
        clientJump.jump();
        Assertions.assertNotEquals(clientJump.getVelocity(),clientNoJump.getVelocity());
    }

    @Test
    public void testIsLive(){
        Assertions.assertTrue(client.isLive());
    }

    @Test
    public void testKill(){
        Assertions.assertTrue(client.isLive());
        client.kill();
        Assertions.assertFalse(client.isLive());
    }

    @Test
    public void testSetCanJump(){
        client.setCanJump(false);
        client.setCanJump(true);
    }

}
