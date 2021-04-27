package org.nightshade.multiplayer;

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

    Image grass = new Image("img/game/dark-grass.png");
    Image lava = new Image("img/game/lava/lava-1.png");
    Image ground = new Image("img/game/dirt.png");
    Image end = new Image("img/game/end.png");

    public Level(int width) {
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
                    case MOVING_PLATFORM: {
                        int speed = ThreadLocalRandom.current().nextInt(0, 5 + 1);
                        Direction direction = Direction.getRandomDirection();
                        Node prevNode = nodes.get(j - 1);
                        if (prevNode == Node.MOVING_PLATFORM) {
                            speed = movingPlatforms.get(movingPlatforms.size() - 1).getSpeed();
                            MovingPlatform lastMovingPlatform = movingPlatforms.get(movingPlatforms.size() - 1);
                            direction = lastMovingPlatform.getDirection();
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

    public ArrayList<Sprite> getLavaSprites() {
        return this.lavaSprites;
    }

    public ArrayList<Sprite> getEndSprites() {
        return this.endSprites;
    }

    public ArrayList<Sprite> getPlatformSprites() {
        return this.platformSprites;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public ArrayList<MovingPlatform> getMovingPlatforms() {
        return this.movingPlatforms;
    }

    public ArrayList<Sprite> getGroundSprites() {
        return this.groundSprites;
    }
}