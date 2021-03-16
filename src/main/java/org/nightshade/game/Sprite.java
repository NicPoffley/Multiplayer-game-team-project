package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sprite {

    private double positionX;
    private double positionY;
    private final double width;
    private final double height;


    public Sprite(Image image, int x, int y) {
        width = image.getWidth();
        height = image.getHeight();
        positionX = x;
        positionY = y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }
    public void setPositionX(double x) {
        positionX = x;
    }
    public void setPositionY(double y) {
        positionY = y;
    }

    public int getPositionY() {
        return (int) positionY;
    }

    public int getPositionX() {
        return (int) positionX;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());
    }

    public boolean intersects(int x, int y, int w, int h) {
        return this.getBoundary().intersects(x,y,w,h);
    }

    public void moveLeft() {
        this.positionX += 1;
    }

    public void moveRight() {
        this.positionX -= 1;
    }

}