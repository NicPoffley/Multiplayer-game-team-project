package org.nightshade.game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelGen {

    private final int width;
    Image grass = new Image("view/Grass.png");
    private ArrayList<ArrayList<Entity>> entities;
    private ArrayList<Sprite> sprites;

    public LevelGen(int width) {
        this.width = width;
        this.entities = new ArrayList<>();
        this.sprites = new ArrayList<>();
    }

    public void createLevel() {
        for (int i = 0; i < 12; i++) {
            entities.add(new ArrayList<>());
            for (int j = 0; j < width; j ++) {
                entities.get(i).add(getRandomEntity(i, j));
            }
        }
    }

    public ArrayList<Sprite> createPlatformSprites(Renderer renderer, Game game) {
        // 12 is the amount of blocks vertically (12*60=720, the canvas height) - using a variable gave an error for some reason?
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < width; j ++) {
                if (entities.get(i).get(j) == Entity.PLATFORM) {
                    sprites.add(new Sprite(grass, j * 60, i * 60));
                } else if (entities.get(i).get(j) == Entity.ENEMY) {
                    int speed = ThreadLocalRandom.current().nextInt(0, (5) + 1);
                    int direction = ThreadLocalRandom.current().nextInt(0, (1) + 1);
                    game.getEnemies().add(new Enemy(speed, direction, j * 60, i * 60));
                } else if (entities.get(i).get(j) == Entity.END) {
                    renderer.drawRectangle(j * 60, i * 60, 60, 60, Color.GREEN);
                }
            }
        }
        return sprites;
    }

    //made public for test
    public Entity getRandomEntity(int i, int j) {
        int number = ThreadLocalRandom.current().nextInt(0, 100 + 1);

        if (i == 6 && j == 5 || i == 6 && j == 6 || i == 6 && j == 7) {
            return Entity.PLATFORM;
        }

        if (i < 5 && j < 20) {
            return Entity.AIR;
        }

        if (i == 11) {
            if (j == width - 1) {
                return Entity.END;
            } else if (number < 90) {
                return Entity.PLATFORM;
            } else {
                return Entity.AIR;
            }
        } else if (i > 7) {
            if (j == width - 1) {
                return Entity.END;
            } else if (number < 20) {
                return Entity.PLATFORM;
            } else if (number < 22) {
                return Entity.ENEMY;
            } else {
                return Entity.AIR;
            }
        } else {
            if (j == width - 1) {
                return Entity.END;
            } else if (number == 1) {
                return Entity.PLATFORM;
            } else if (number == 22) {
                return Entity.ENEMY;
            } else {
                return Entity.AIR;
            }
        }

    }
}
