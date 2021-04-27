package org.nightshade.game;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
import org.nightshade.renderer.Renderer;
import java.util.ArrayList;

public class Game {

    private Scene scene;
    private final int LEVEL_WIDTH = 120;
    private final int BLOCK_WIDTH = 60;

    private int xViewCoordinate = 0;
    private final int animationIndex = 0;
    private final ArrayList<AI> aiPlayers;
    private final ArrayList<Image> lavaImages;
    private ArrayList<KeyCode> keyCodes;
    private final Renderer renderer;
    private final Client client;
    private final AILogic aiLogic;
    private final Sprite cloudSprite;
    private final Parallax parallax;
    private final Level level;

    public Game(Stage stage) {
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(LEVEL_WIDTH * BLOCK_WIDTH);
        Pane pane = new Pane(renderer.getGroup());

        int SCENE_WIDTH = 1280;
        int SCENE_HEIGHT = 720;
        scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();

        Image cloudImage = new Image("img/game/cloud.png");
        cloudSprite = new Sprite(cloudImage, -2300, 50);
        cloudSprite.setX(-1300);
        parallax = new Parallax();
        level = new Level(LEVEL_WIDTH);

        aiLogic = new AILogic();
        client = new Client();
        aiPlayers = new ArrayList<>();
        lavaImages = new ArrayList<>();
        for (int i = 1; i < 18; i++) {
            Image lavaImage = new Image("img/game/lava/lava-" + i + ".png");
            lavaImages.add(lavaImage);
        }

        keyCodes = new ArrayList<>();
        listen();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                loop();
            }
        }.start();
    }

    // listens for KeyEvents and keeps track of keys pressed
    private void listen() {
        scene.setOnKeyPressed(
                keyEvent -> {
                    KeyCode keyCode = keyEvent.getCode();
                    if (!keyCodes.contains(keyCode)) {
                        keyCodes.add(keyCode);
                    }
                });
        scene.setOnKeyReleased(
                keyEvent -> {
                    KeyCode keyCode = keyEvent.getCode();
                    keyCodes.remove(keyCode);
                });
    }

    private void moveClient() {
        // TODO: these arrays can just be moved to client (just pass level)
        ArrayList<Sprite> platformSprites = level.getPlatformSprites();
        ArrayList<Sprite> lavaSprites = level.getLavaSprites();
        ArrayList<Sprite> groundSprites = level.getGroundSprites();
        ArrayList<Enemy> enemies = level.getEnemies();
        ArrayList<MovingPlatform> movingPlatforms = level.getMovingPlatforms();
        ArrayList<PowerUp>powerUps = level.getPowerUps();

        if (client.isAlive()) {
            if (keyCodes.contains(KeyCode.UP) && client.getSprite().getY() >= 5) {
                client.jump();
            }

            if (keyCodes.contains(KeyCode.LEFT) && client.getSprite().getX() >= 5) {
                client.moveX(-5, level);
            }

            if (keyCodes.contains(KeyCode.RIGHT) && client.getSprite().getX() <= (LEVEL_WIDTH * BLOCK_WIDTH) - 5) {
                client.moveX(5, level);

            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0, 1));
            }

            client.moveY((int) client.getVelocity().getY(), platformSprites, lavaSprites, enemies, groundSprites, movingPlatforms, powerUps);
        }
    }

    public void addAiPlayer(AI ai) {
        aiPlayers.add(ai);
    }

    public void loop() {
        parallax.move();
        parallax.render(renderer, xViewCoordinate);

        renderSprites(level.getPlatformSprites());
        renderSprites(level.getGroundSprites());
        renderSprites(level.getEndSprites());
        for (Sprite lavaSprite : level.getLavaSprites()) {
            renderer.drawImage(lavaImages.get(animationIndex), lavaSprite.getX(), lavaSprite.getY());
        }
        // move cloud
        if (client.getSprite().getX() - cloudSprite.getX() > 2000) {
            cloudSprite.setX(client.getSprite().getX() - 2000);
        } else {
            cloudSprite.setX(cloudSprite.getX() + 2);
        }
        renderer.drawImage(cloudSprite.getImage(), cloudSprite.getX(), 50);
        if (client.isAlive()) {
            moveClient();
            Sprite clientSprite = client.getSprite();
            renderer.drawImage(clientSprite.getImage(), clientSprite.getX(), clientSprite.getY());
            boolean intersectsCloud = clientSprite.intersects(cloudSprite.getX() - 90, cloudSprite.getY(), (int) cloudSprite.getWidth(), (int) cloudSprite.getHeight());
            if (intersectsCloud) {
                client.kill();
            }
        }
        for (AI ai : aiPlayers) {
            ArrayList<Sprite> sprites = new ArrayList<>();
            sprites.addAll(level.getPlatformSprites());
            sprites.addAll(level.getGroundSprites());
            sprites.addAll(level.getLavaSprites());
            aiLogic.moveSprite(ai, sprites);
            Sprite aiSprite = ai.getSprite();
            renderer.drawImage(aiSprite.getImage(), aiSprite.getX(), aiSprite.getY());
        }
        for (Enemy enemy : level.getEnemies()) {
            enemy.moveEnemy();
            Sprite enemySprite = enemy.getSprite();
            renderer.drawImage(enemySprite.getImage(), enemySprite.getX(), enemySprite.getY());
        }
        for (MovingPlatform movingPlatform : level.getMovingPlatforms()) {
            movingPlatform.movePlatform();
            movingPlatform.displaySprite(renderer, movingPlatform.getSprite().getImage(), movingPlatform.getSprite());
        }
        for (PowerUp powerUp : level.getPowerUps()) {
            if (powerUp.getCollected()){
                //System.out.println(powerUp.getAbility());
            }else {
                renderer.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY());
            }
        }
        if (client.powerUpTimer > 0){
            client.reducePowerUpTimer();
        }else {
            client.removeAbility();
        }


        //Move camera
        double translateX = renderer.getCanvas().getTranslateX();
        if ((-1 *translateX) + 700 < client.getSprite().getX() && (-1 * translateX) < (LEVEL_WIDTH * 60 - 1280)) {
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
}