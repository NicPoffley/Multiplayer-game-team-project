package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Game {

    private final int levelWidth = 120;
    private final int blockWidth = 60;
    private int xViewCoordinate = 0;
    private int animationIndex = 0;
    private int gameTickCounter = 0;

    private ArrayList<AI> aiPlayers;
    private ArrayList<Image> lavaImages;

    private final ArrayList<String> input = new ArrayList<>();
    private ArrayList<Sprite> platformSprites;
    private ArrayList<Sprite> lavaSprites;
    private ArrayList<Sprite> groundSprites;
    private ArrayList<Sprite> endSprites;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private Renderer renderer;
    private Client client;
    private AILogic aiLogic;
    private Sprite cloud;
    private Parallax parallax;
    private final Image cloudImage = new Image("img/game/cloud.png");

    public Game(Stage stage) {
        cloud = new Sprite(cloudImage, -2300, 50);
        parallax = new Parallax();
        renderer = new Renderer();
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane, 1280, 720);
        LevelGen levelGen = new LevelGen(levelWidth);
        aiLogic = new AILogic();
        client = new Client();

        aiPlayers = new ArrayList<>();

        cloud.setX(-1300);
        renderer.setHeight(720);
        renderer.setWidth(levelWidth * blockWidth);
        platformSprites = levelGen.createPlatformSprites();
        lavaSprites = levelGen.createLavaSprites();
        groundSprites = levelGen.createGroundSprites();
        enemies = levelGen.createEnemies();
        endSprites = levelGen.createEndSprites();
        powerUps = levelGen.createPowerUps();

        stage.setScene(scene);
        stage.show();

        lavaImages = new ArrayList<>();

        for (int i = 0; i < 17; i++) {
            lavaImages.add(new Image("img/game/lava/image " + (i + 1) + ".png"));
        }

        checkForInput(scene);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                loop(gameTickCounter);
            }
        }.start();
    }

    private void checkForInput(Scene scene) {
        scene.setOnKeyPressed(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                });

        scene.setOnKeyReleased(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    input.remove(code);
                });
    }

    private void moveClient(ArrayList<Sprite> platformSprites) {
        if (client.isAlive()) {
            if (input.contains("UP") && client.getSprite().getY() >= 5) {
                client.jump();
            }

            if (input.contains("LEFT") && client.getSprite().getX() >= 5) {
                client.moveX(-5, platformSprites, enemies, groundSprites,powerUps);
            }

            if (input.contains("RIGHT") && client.getSprite().getX() <= (levelWidth * blockWidth) - 5) {
                client.moveX(5, platformSprites, enemies, groundSprites,powerUps);
            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0, 1));
            }

            client.moveY((int) client.getVelocity().getY(), platformSprites, lavaSprites, enemies, groundSprites,powerUps);

        }
    }

    public void addAiPlayer(AI ai) {
        aiPlayers.add(ai);
    }

    public ArrayList<AI> getAiPlayers() {
        return this.aiPlayers;
    }

    public void loop(int gameTickCounter) {

        parallax.moveParallax();
        parallax.drawParallax(renderer, xViewCoordinate);

        renderSprites(platformSprites);
        renderSprites(groundSprites);
        renderSprites(endSprites);

        for (Sprite lavaSprite : lavaSprites) {
            renderer.drawImage(lavaImages.get(animationIndex), lavaSprite.getX(), lavaSprite.getY());
        }

        // move cloud
        if (client.getSprite().getX() - cloud.getX() > 2000) {
            cloud.setX(client.getSprite().getX() - 2000);
        } else {
            cloud.setX(cloud.getX() + 2);
        }
        renderer.drawImage(cloud.getImage(), cloud.getX(), 50);

        if (client.isAlive()) {
            moveClient(platformSprites);
            Sprite clientSprite = client.getSprite();
            renderer.drawImage(clientSprite.getImage(), clientSprite.getX(), clientSprite.getY());

            boolean intersectsCloud = clientSprite.intersects(cloud.getX() - 90, cloud.getY(), (int) cloud.getWidth(), (int) cloud.getHeight());

            if (intersectsCloud) {
                client.kill();
            }
        }

        for (AI ai : aiPlayers) {
            ArrayList<Sprite> sprites = new ArrayList<>();
            sprites.addAll(platformSprites);
            sprites.addAll(groundSprites);
            sprites.addAll(lavaSprites);
            sprites.addAll(powerUps);
            aiLogic.moveSprite(ai, sprites);
            Sprite aiSprite = ai.getSprite();
            renderer.drawImage(aiSprite.getImage(), aiSprite.getX(), aiSprite.getY());
        }

        for (Enemy enemy : enemies) {
            enemy.moveEnemy();
            Sprite enemySprite = enemy.getSprite();
            renderer.drawImage(enemySprite.getImage(), enemySprite.getX(), enemySprite.getY());
        }

        for (PowerUp powerUp : powerUps) {
            if (powerUp.getCollected()){
                //System.out.println(powerUp.getAbility());
            }else {
                renderer.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY());
            }
        }

        //Move camera
        double translateX = renderer.getCanvas().getTranslateX();
        if ((-1 *translateX) + 700 < client.getSprite().getX() && (-1 * translateX) < (levelWidth * 60 - 1280)) {
            renderer.getCanvas().setTranslateX((int) (translateX + ((-1 * translateX) + 700 - client.getSprite().getX())));
        } else {
            renderer.getCanvas().setTranslateX((int) (translateX));
        }

        xViewCoordinate = (int) (-1 * translateX);
    }

    private void renderSprites(ArrayList<Sprite> sprites) {
        for (Sprite sprite : sprites) {
            renderer.drawImage(sprite.getImage(), sprite.getX(), sprite.getY());
        }
    }


    private int setAnimationIndex(int counter) {
        if (counter % 3 == 0) {
            animationIndex ++;
            if (animationIndex == 17) {
                animationIndex = 0;
            }
        }
        return animationIndex;
    }

    private void drawPlatformsAndLavaAndGroundAndEnd(int animationIndex) {

    }
}