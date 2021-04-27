package org.nightshade.game;

import javafx.scene.image.Image;

public class PowerUp extends Sprite {

    private final Image image;
    private double x;
    private double y;
    private double width;
    private double height;
    private boolean collected;

    private Ability ability;

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

    public Ability getAbility(){
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
        this.width = 0;
        this.height = 0;
    }

    private Ability assignRandomAbility (){
        double randomNum = Math.random();

        if (randomNum<0.33){
            return Ability.JUMPBOOST;
        } else if (randomNum<0.67){
            return Ability.SPEEDBOOST;
        }else {
            return Ability.SHIELD;
        }

    }



}
