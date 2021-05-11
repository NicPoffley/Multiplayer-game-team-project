package org.nightshade.game;
import java.util.concurrent.ThreadLocalRandom;

/**
 * defines the set of nodes available for our game to display
 */
public enum Node {
    AIR,
    PLATFORM,
    MOVING_PLATFORM,
    GROUND,
    LAVA,
    ENEMY,
    POWERUP,
    END;

    /**
     * getRandomNode is tge method generating the track, obstacles, moving platforms,
     * water, enemies, ground, power ups and lava in the level
     * @param i
     * @param j
     * @param width
     * @return air, platform, moving platform, ground, lava, enemy power or end
     */
    public static Node getRandomNode(int i, int j, int width) {
        //random number between 1 and 100, used for randomly choosing nodes.
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        //This is the starting platform, so the characters land on it when spawning in
        if (i == 6 && j == 5 || i == 6 && j == 6 || i == 6 && j == 7) {
            return Node.PLATFORM;
        }
        //Last column of blocks should be end nodes (the end of the level)
        if (j == width - 1) {
            return Node.END;
        }
        //this condition keeps the start of the level clear of enemies where the players will spawn
        if (i < 5 && j < 30) {
            return Node.AIR;
        }
        if (i == 11) {
            //will spawn ground 90% of the time, and lava the other 10%
            if (randomNumber < 90) {
                return Node.GROUND;
            } else {
                return Node.LAVA;
            }
        }
        if (i == 10) {
            if (randomNumber == 1) {
                return Node.ENEMY;
            } else if(randomNumber == 2){
                return Node.POWERUP;
            } else {
                return Node.AIR;
            }
        }
        if (i == 9){
            if (randomNumber < 12) {
                return Node.PLATFORM;
            } else if (randomNumber < 16){
                return Node.MOVING_PLATFORM;
            } else if (randomNumber < 17){
                return Node.POWERUP;
            } else {
                return Node.AIR;
            }
        }
        if (i == 8) {
            if (randomNumber < 4) {
                return Node.PLATFORM;
            } else if (randomNumber < 5){
                return Node.POWERUP;
            } else {
                return Node.AIR;
            }
        }
        if (i == 7) {
            if (randomNumber < 12 && j > 20) {
                return Node.PLATFORM;
            } else if (randomNumber < 16){
                return Node.MOVING_PLATFORM;
            } else if (randomNumber < 17){
                return Node.POWERUP;
            } else {
                return Node.AIR;
            }
        }
        if (randomNumber == 1 && j > 20) {
            return Node.ENEMY;
        } else {
            return Node.AIR;
        }
    }
}