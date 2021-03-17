package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private final int levelWidth = 120;
    private final int blockWidth = 60;
    private int xViewCoordinate = 0;
    private int animationIndex = 0;
    private int gameTickCounter = 0;

    private ArrayList<AI> aiList;
    private ArrayList<Image> lavaImageList;


    private final ArrayList<String> input = new ArrayList<>();
    private ArrayList<Sprite> platformSprites;
    private ArrayList<Sprite> lavaSprites;
    private ArrayList<Sprite> groundSprites;
    private ArrayList<Sprite> endSprites;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Renderer renderer;
    private Client client;
    private AILogic aiLogic;
    private Sprite cloud;
    private Parallax background;
    private final Image cloudImage = new Image("view/GameComponents/dark.png");

    public void initGame(Stage stage, int aiCount){
        cloud = new Sprite(cloudImage,-2300,50);
        background = new Parallax();
        renderer = new Renderer();
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane,1280,720);
        LevelGen levelGen = new LevelGen(levelWidth);
        aiLogic = new AILogic();
        client = new Client();

        aiList = new ArrayList<>();
        for (int i = 0; i < aiCount; i++){
            int randomSpeed = ThreadLocalRandom.current().nextInt(2, 4 + 1);
            aiList.add(new AI (randomSpeed));
        }

        cloud.setPositionX(-1300);
        renderer.setHeight(720);
        renderer.setWidth(levelWidth*blockWidth);
        platformSprites = levelGen.createPlatformSprites();
        lavaSprites = levelGen.createLavaSprites();
        groundSprites = levelGen.createGroundSprites();
        enemies = levelGen.createEnemies();
        endSprites = levelGen.createEndSprites();

        stage.setScene(scene);
        stage.show();

        Image grass = new Image("view/GameComponents/DarkGrass.png");
        Image ground = new Image("view/GameComponents/Dirt.png");
        Image clientImg = new Image("view/GameComponents/Body.png");
        Image aiImg = new Image("view/GameComponents/AIBody.png");
        Image enemy = new Image("view/GameComponents/enemy.png");
        Image end = new Image("view/GameComponents/EndNode.png");

        lavaImageList = new ArrayList<>();

        for (int i = 0 ; i < 17 ; i++){
            lavaImageList.add(new Image("view/GameComponents/Water/image "+(i+1)+".png"));
        }

        checkForInput(scene);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

      /*  KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.017), // 60FPS
                actionEvent -> gameLoop(platformSprites, gameTickCounter++, grass, ground, enemy, end, clientImg, aiImg)
        );

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();*/
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gameLoop(platformSprites, gameTickCounter++, grass, ground, enemy, end, clientImg, aiImg);
            }
        }.start();
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
                client.moveX(-5, platformSprites,enemies,groundSprites);
            }

            if (input.contains("RIGHT") && client.getClientSprite().getPositionX() <= (levelWidth*blockWidth) - 5) {
                client.moveX(5,platformSprites,enemies,groundSprites);
            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0,1));
            }

            client.moveY((int)client.getVelocity().getY(),platformSprites, lavaSprites,enemies,groundSprites);

        }
    }

    public void gameLoop(ArrayList<Sprite> platformSprites, int gameTickCounter, Image grass, Image ground, Image enemy, Image end, Image clientImg, Image aiImg){

        background.moveParallax();
        background.drawParallax(renderer,xViewCoordinate);

        drawPlatformsAndLavaAndGroundAndEnd(grass, ground, end,setAnimationIndex(gameTickCounter));

        moveCloud();
        renderer.drawImage(cloudImage,cloud.getPositionX(),50);

        if(client.isLive()) {
            moveClient(platformSprites);
            client.displaySprite(renderer, clientImg, client.getClientSprite());
            if (client.getClientSprite().intersects(cloud.getPositionX()-90,cloud.getPositionY(),(int)cloud.getWidth(),(int)cloud.getHeight())){
                client.kill();
            }
        }

        for (AI ai : aiList) {
            ArrayList<Sprite> sprites = new ArrayList<>();
            sprites.addAll(platformSprites);
            sprites.addAll(groundSprites);
            sprites.addAll(lavaSprites);
            aiLogic.moveSprite(ai, sprites);
            ai.displaySprite(renderer, aiImg, ai.getSprite());
        }

        for (Enemy thisEnemy : enemies) {
            thisEnemy.moveEnemy();
            thisEnemy.displaySprite(renderer,enemy, thisEnemy.getEnemySprite());
        }

        //Move camera
        if ((-1*renderer.getTransLateX())+700 < client.getClientSprite().getPositionX() && (-1*renderer.getTransLateX()) < (levelWidth * 60 - 1280)){
            renderer.setTransLateX((int) (renderer.getTransLateX()+((-1*renderer.getTransLateX())+700-client.getClientSprite().getPositionX())));
        } else{
            renderer.setTransLateX((int) (renderer.getTransLateX()));
        }

        xViewCoordinate = (int) (-1*renderer.getTransLateX());
    }

    private void moveCloud(){
        int cloudXPosNew=cloud.getPositionX()+2;

        if (client.getClientSprite().getPositionX()-cloud.getPositionX() > 2000){
            cloudXPosNew = client.getClientSprite().getPositionX() - 2000;
        }
        cloud.setPositionX(cloudXPosNew);
    }

    private int setAnimationIndex(int counter){
        if (counter % 3 == 0) {
            animationIndex++;
            if (animationIndex == 17) {
                animationIndex = 0;
            }
        }
        return animationIndex;
    }


    private void drawPlatformsAndLavaAndGroundAndEnd(Image grass, Image ground, Image end, int animationIndex){
        for (Sprite platformSprite : platformSprites){
            renderer.drawImage(grass, platformSprite.getPositionX(), platformSprite.getPositionY());
        }
        for (Sprite lavaSprite : lavaSprites) {
            renderer.drawImage(lavaImageList.get(animationIndex), lavaSprite.getPositionX(), lavaSprite.getPositionY());
        }
        for (Sprite groundSprite : groundSprites) {
            renderer.drawImage(ground, groundSprite.getPositionX(), groundSprite.getPositionY());
        }
        for (Sprite endSprite : endSprites) {
            renderer.drawImage(end, endSprite.getPositionX(), endSprite.getPositionY());
        }
    }

}
