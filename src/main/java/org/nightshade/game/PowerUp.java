package org.nightshade.game;

import javafx.scene.image.Image;

public class PowerUp extends Sprite {

    private final Image image;
    private double x;
    private double y;
    private final double width;
    private final double height;
    private boolean collected;

    private String ability;

    public PowerUp(Image image, int x, int y) {
        super(image, x, y);
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
        this.collected = false;
        this.ability = assignRandomAbility();
    }

    public String getAbility(){
        return this.ability;
    }

    public void setCollected(boolean collected){
        this.collected = collected;
    }
    public boolean getCollected(){
        return this.collected;
    }
    public void collect(){
        this.collected = true;
    }


    private String assignRandomAbility (){
        double randomNum = Math.random();
        if (randomNum<0.25){
            return "PROTECTIVE SHIELD";
        } else if (randomNum<0.5){
            return "SPEED BOOST";
        }else if (randomNum<0.75){
            return "SLOW CLOUD";
        }else{
            return "JUMP BOOST";
        }
    }



}
