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
    private int verticalBlocksCount;
    private int xViewCoordinate = 0;
    private int animationIndex = 0;
    private final ArrayList<AI> aiPlayers;
    private final ArrayList<Image> lavaImages;
    private final ArrayList<String> input = new ArrayList<>();
    private final Renderer renderer;
    private final Client client;
    private final AILogic aiLogic;
    private final Sprite cloud;
    private final Parallax parallax;
    private Level level;

    public Game(Stage stage) {
        this.level = new Level(levelWidth);
        cloud = new Sprite(new Image("img/game/cloud.png"), -2300, 50);
        parallax = new Parallax();
        renderer = new Renderer();
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane, 1280, 720);
        aiLogic = new AILogic();
        client = new Client();
        aiPlayers = new ArrayList<>();
        cloud.setX(-1300);
        renderer.setHeight(720);
        renderer.setWidth(levelWidth * blockWidth);
        stage.setScene(scene);
        stage.show();
        lavaImages = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            lavaImages.add(new Image("img/game/lava/image " + (i + 1) + ".png"));
        }
        checkForInput(scene);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                loop();
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
    private void moveClient() {
        // TODO: these arrays can just be moved to client (just pass level)
        ArrayList<Sprite> platformSprites = level.getPlatformSprites();
        ArrayList<Sprite> lavaSprites = level.getLavaSprites();
        ArrayList<Sprite> groundSprites = level.getGroundSprites();
        ArrayList<Enemy> enemies = level.getEnemies();
        ArrayList<MovingPlatform> movingPlatforms = level.getMovingPlatforms();
        ArrayList<PowerUp>powerUps = level.getPowerUps();

        if (client.isAlive()) {
            if (input.contains("UP") && client.getSprite().getY() >= 5) {
                client.jump();
            }
            if (input.contains("LEFT") && client.getSprite().getX() >= 5) {
                client.moveX(-5, platformSprites, enemies, groundSprites, movingPlatforms,powerUps);
            }
            if (input.contains("RIGHT") && client.getSprite().getX() <= (levelWidth * blockWidth) - 5) {
                client.moveX(5, platformSprites, enemies, groundSprites, movingPlatforms,powerUps);
            }
            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0, 1));
            }
            client.moveY((int) client.getVelocity().getY(), platformSprites, lavaSprites, enemies, groundSprites, movingPlatforms,powerUps);
        }
    }
    public void addAiPlayer(AI ai) {
        aiPlayers.add(ai);
    }
    public ArrayList<AI> getAiPlayers() {
        return this.aiPlayers;
    }
    public void loop() {
/*
        parallax.move();
        parallax.render(renderer, xViewCoordinate);
*/

        renderSprites(level.getPlatformSprites());
        renderSprites(level.getGroundSprites());
        renderSprites(level.getEndSprites());
        for (Sprite lavaSprite : level.getLavaSprites()) {
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
            moveClient();
            Sprite clientSprite = client.getSprite();
            renderer.drawImage(clientSprite.getImage(), clientSprite.getX(), clientSprite.getY());
            boolean intersectsCloud = clientSprite.intersects(cloud.getX() - 90, cloud.getY(), (int) cloud.getWidth(), (int) cloud.getHeight());
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
}