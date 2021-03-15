package org.nightshade.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelGen {
    private final int width;
    ArrayList<ArrayList<NodeType>> level;
    ArrayList<Sprite> platformSprites = new ArrayList<>();
    ArrayList<Sprite> waterSprites = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    Image grass = new Image("view/Grass.png");
    Image water = new Image("view/Water/image 1.png");
    Image enemy = new Image("view/enemy.png");


    public LevelGen(int width) {
        this.width = width;
        this.level = createLevel(width);
    }

    private ArrayList<ArrayList<NodeType>> createLevel(int width) {
        level=new ArrayList<>();
        for (int i=0;i<12;i++){
            level.add(new ArrayList<>());
            int j = 0;
            while(j < width){
                NodeType newNode = getRandomNode(i,j);
                level.get(i).add(newNode);
                if (newNode == NodeType.PLATFORM){
                    level.get(i).add(level.get(i).get(j));
                    j += 2;
                } else{
                    j++;
                }
                if (newNode == NodeType.WATER){
                    int randomWaterLength = ThreadLocalRandom.current().nextInt(0, 2 + 1);
                    for (int k = 0 ; k < randomWaterLength ; k++){
                        level.get(i).add(NodeType.WATER);
                    }
                    j+=randomWaterLength;
                }
            }
        }
        return level;
    }

    public ArrayList<Sprite> createPlatformSprites(){
        //12 is the amount of blocks vertically (12*60=720, the canvas height) - using a variable gave an error for some reason?
        for (int i = 0 ; i < 12 ; i++){
            for(int j = 0 ; j < width ; j++){
                if(level.get(i).get(j) == NodeType.PLATFORM) {
                    platformSprites.add(new Sprite(grass, j * 60, i * 60));
                }
            }
        }
        return platformSprites;
    }

    public ArrayList<Enemy> createEnemies(){
        for (int i = 0 ; i < 12 ; i++){
            for(int j = 0 ; j < width ; j++){
                if(level.get(i).get(j) == NodeType.ENEMY) {
                    int speed = ThreadLocalRandom.current().nextInt(0, (5) + 1);
                    int direction = ThreadLocalRandom.current().nextInt(0, (1) + 1);
                    enemies.add(new Enemy(speed,direction,j*60,i*60));
                }
            }
        }
        return enemies;
    }

    public ArrayList<Sprite> createWaterSprites(){
        for (int i = 0 ; i < 12 ; i++){
            for(int j = 0 ; j < width ; j++){
                if(level.get(i).get(j) == NodeType.WATER){
                    waterSprites.add(new Sprite(water, j * 60, i * 60));
                }
            }
        }
        return waterSprites;
    }



    //made public for test
    public NodeType getRandomNode(int i,int j){
        //random number between 1 and 100
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);

        //This is the starting platform, so the characters land on it when spawning in
        if(i==6 && j==5 || i==6 && j==6 || i==6 && j==7){
            return NodeType.PLATFORM;
        }
        //Last column of blocks should be end nodes (the end of the level)
        if(j==width-1) {
            return NodeType.END;
        }
        //this condition keeps the start of the level clear of enemies where the players will spawn
        if(i<5 && j<30){
            return NodeType.AIR;
        }
        if(i == 11){
            //will spawn a platform 90% of the time, and water the other 10%
            if(randomNumber<90) {
                return NodeType.PLATFORM;
            } else{
                return NodeType.WATER;
            }
        }
        if(i == 10){
            if(randomNumber<=4) {
                return NodeType.PLATFORM;
            } else if(randomNumber<=7){
                return NodeType.ENEMY;
            } else{
                return NodeType.AIR;
            }
        }
        if(i>7){
            if(randomNumber<15) {
                return NodeType.PLATFORM;
            } else if(randomNumber<16){
                return NodeType.ENEMY;
            } else{
                return NodeType.AIR;
            }
        } else{
            if(randomNumber==1){
                return NodeType.ENEMY;
            } else{
                return NodeType.AIR;
            }
        }
    }
}
