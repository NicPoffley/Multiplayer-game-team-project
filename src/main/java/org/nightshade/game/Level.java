package org.nightshade.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Level {
    private final ArrayList<Sprite> platformSprites;
    private final ArrayList<Sprite> lavaSprites;
    private final ArrayList<Sprite> groundSprites;
    private final ArrayList<Sprite> endSprites;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<MovingPlatform> movingPlatforms;
    private final ArrayList<PowerUp> powerUps;
    public int width;

    Image grass = new Image("img/game/dark-grass.png");
    Image lava = new Image("img/game/lava/lava-1.png");
    Image ground = new Image("img/game/dirt.png");
    Image end = new Image("img/game/end.png");
    Image powerUp = new Image("img/game/powerup.png");

    /**
     * Level method is a method creating the level the player chose to play
     * @param width of the track
     */
    public Level(int width) {
        this.width = width;
        int blockHeight = 12;

        ArrayList<ArrayList<Node>> nodeArrayLists = new ArrayList<>();
        for (int i = 0; i < blockHeight; i ++) {
            ArrayList<Node> nodes = new ArrayList<>();
            int count = 0;
            while (count < width) {
                Node node = Node.getRandomNode(i, count, width);
                nodes.add(node);
                switch (node) {
                    case PLATFORM:
                        nodes.add(Node.PLATFORM);
                        count += 2;
                        break;
                    case MOVING_PLATFORM:
                        nodes.add(Node.MOVING_PLATFORM);
                        count += 2;
                        break;
                    case LAVA:
                        if(nodes.size()>3) {
                            if (nodes.get(nodes.size()-3) == Node.LAVA) {
                                nodes.add(Node.GROUND);
                            }
                        }
                        int length = ThreadLocalRandom.current().nextInt(0, 2 + 1);
                        for (int j = 0; j < length; j ++) {
                            nodes.add(Node.LAVA);
                        }
                        count += length;
                        break;
                    default:
                        count++;
                        break;
                }
            }
            nodeArrayLists.add(nodes);
        }

        this.platformSprites = new ArrayList<>();
        this.lavaSprites = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.movingPlatforms = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        for (int i = 0; i < blockHeight; i++) {
            ArrayList<Node> nodes = nodeArrayLists.get(i);
            for (int j = 0; j < width; j++) {
                int x = j * 60;
                int y = i * 60;
                Node node = nodes.get(j);
                switch (node) {
                    case PLATFORM: {
                        Sprite sprite = new Sprite(grass, x, y);
                        platformSprites.add(sprite);
                        break;
                    }
                    case LAVA: {
                        Sprite sprite = new Sprite(lava, x, y);
                        lavaSprites.add(sprite);
                        break;
                    }
                    case ENEMY: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Enemy enemy = new Enemy(speed, x, y);
                        enemies.add(enemy);
                        break;
                    }
                    case POWERUP: {
                        powerUps.add(new PowerUp(powerUp, x, y));
                        break;
                    }
                    case MOVING_PLATFORM: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Direction direction = Direction.getRandomDirection();
                        if (j>1){
                            Node previousNode = nodes.get(j - 1);
                            if (previousNode == Node.MOVING_PLATFORM) {
                                speed = movingPlatforms.get(movingPlatforms.size() - 1).getSpeed();
                                MovingPlatform lastMovingPlatform = movingPlatforms.get(movingPlatforms.size() - 1);
                                direction = lastMovingPlatform.getDirection();
                            }
                        }
                        MovingPlatform newMovingPlatform = new MovingPlatform(x, y, speed, direction);
                        movingPlatforms.add(newMovingPlatform);
                        break;
                    }
                }
            }
        }

        this.groundSprites = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ArrayList<Node> nodes = nodeArrayLists.get(11);
            Node node = nodes.get(i);
            if (node == Node.GROUND) {
                int x = i * 60;
                int y = 11 * 60;
                Sprite sprite = new Sprite(ground, x, y);
                groundSprites.add(sprite);
            }
        }

        this.endSprites = new ArrayList<>();
        for (int i = 0; i < blockHeight; i++) {
            int x = (width - 1) * 60;
            int y = i * 60;
            Sprite sprite = new Sprite(end, x, y);
            endSprites.add(sprite);
        }
    }

    /**
     * getLavaSprites getter method returning lava sprites
     * @return array list of lava sprites
     */
    public ArrayList<Sprite> getLavaSprites() {
        return this.lavaSprites;
    }

    /**
     * getEndSprites getter method returning end sprites
     * @return array list of end sprites
     */
    public ArrayList<Sprite> getEndSprites() {
        return this.endSprites;
    }

    /**
     * getPlatformSprites getter method returning platform sprites
     * @return array list of platform sprites
     */
    public ArrayList<Sprite> getPlatformSprites() {
        return this.platformSprites;
    }

    /**
     * getEnemies getter method returning enemies
     * @return array list of enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * getMovingPlatforms getter method returning moving platforms
     * @return array list of moving platforms
     */
    public ArrayList<MovingPlatform> getMovingPlatforms() {
        return this.movingPlatforms;
    }

    /**
     * getPowerUps getter method returning random power ups the character can obtain in the track
     * @return array list of power ups
     */
    public ArrayList<PowerUp> getPowerUps() {
        return this.powerUps;
    }

    /**
     * getGroundSprites getter method returning ground spites
     * @return array list of ground sprites
     */
    public ArrayList<Sprite> getGroundSprites() {
        return this.groundSprites;
    }
    /**
     * getWidth getter method returning the level width
     * @return int holding value of the level width
     */
    public int getWidth() {
        return this.width;
    }
}
