package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
import org.nightshade.renderer.Renderer;

import java.sql.Time;
import java.util.ArrayList;

public class Game {

    private final int levelWidth = 120;
    private final int blockWidth = 60;
    public static int xViewCoordinate = 0;

    private final ArrayList<String> input = new ArrayList<>();
    private ArrayList<Sprite> platformSprites;
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    private Renderer renderer;
    private Client client;
    private AI ai;
    private org.nightshade.ai.AILogic AILogic;
    private Sprite cloud;
    private Parallax background;

    private final Image cloudImage = new Image("view/dark.png");

    public void initGame(Stage stage){
        cloud = new Sprite(cloudImage,-300,50);
        background = new Parallax();
        renderer = new Renderer();
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane,1280,720);
        LevelGen levelGen = new LevelGen(levelWidth);
        AILogic = new AILogic();
        client = new Client();
        ai = new AI(3);

        cloud.setPositionX(-400);
        renderer.setHeight(720);
        renderer.setWidth(levelWidth*blockWidth);
        platformSprites = levelGen.createPlatformSprites(renderer);

        stage.setScene(scene);
        stage.show();

        Image grass = new Image("view/Grass.png");
        Image clientImg = new Image("view/Body.png");
        Image enemy = new Image("view/enemy.png");

        checkForInput(scene);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.017), // 60FPS
                actionEvent -> gameLoop(platformSprites, grass, enemy, clientImg)
        );

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void checkForInput(Scene scene){

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });
    }

    private void moveClient(ArrayList<Sprite> platformSprites){
        if(client.isLive()) {
            if (input.contains("UP") && client.getClientSprite().getPositionY() >= 5) {
                client.jump();
            }

            if (input.contains("LEFT") && client.getClientSprite().getPositionX() >= 5) {
                client.moveX(-5, platformSprites,enemies);
            }

            if (input.contains("RIGHT") && client.getClientSprite().getPositionX() <= (levelWidth*blockWidth) - 5) {
                client.moveX(5,platformSprites,enemies);
            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0,1));
            }

            client.moveY((int)client.getVelocity().getY(),platformSprites,enemies);

        }
    }

    public void gameLoop(ArrayList<Sprite> platformSprites, Image grass,Image enemy, Image clientImg){

        background.moveParallax();
        background.drawParallax(renderer);

        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(grass, platformSprite.getPositionX(), platformSprite.getPositionY());
        }

        moveCloud();
        renderer.drawImage(cloudImage,cloud.getPositionX(),0);

        if(client.isLive()) {
            moveClient(platformSprites);
            client.displaySprite(renderer, clientImg, client.getClientSprite());
            if (client.getClientSprite().intersects(cloud.getPositionX()-200,cloud.getPositionY(),(int)cloud.getWidth(),(int)cloud.getHeight())){
                client.kill();
            }
        }

        AILogic.moveChar(ai, platformSprites);
        ai.displaySprite(renderer,clientImg,ai.getAISprite());

        for (Enemy thisEnemy : enemies) {
            thisEnemy.moveEnemy();
            thisEnemy.displaySprite(renderer,enemy, thisEnemy.getEnemySprite());
        }

        //Move camera
        if ((-1*renderer.getTransLateX())+700 < client.getClientSprite().getPositionX()){
            renderer.setTransLateX((int) (renderer.getTransLateX()+((-1*renderer.getTransLateX())+700-client.getClientSprite().getPositionX())));
        } else{
            renderer.setTransLateX((int) (renderer.getTransLateX()));
        }

        xViewCoordinate = (int) (-1*renderer.getTransLateX());
    }

    private void moveCloud(){
        int cloudXPosNew=cloud.getPositionX()+2;

        if (client.getClientSprite().getPositionX()-cloud.getPositionX() > 1000){
            cloudXPosNew = client.getClientSprite().getPositionX() - 1000;
        }
        cloud.setPositionX(cloudXPosNew);
    }






}
