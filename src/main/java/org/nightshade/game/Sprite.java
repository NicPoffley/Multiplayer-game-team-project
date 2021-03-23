package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sprite {

    private final Image image;
    private double x;
    private double y;
    private final double width;
    private final double height;

    public Sprite(Image image, int x, int y) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public int getY() {
        return (int) y;
    }

    public int getX() {
        return (int) x;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    public boolean intersects(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());
    }

    public boolean intersects(int x, int y, int w, int h) {
        return this.getBoundary().intersects(x,y,w,h);
    }

    public void moveLeft() {
        this.x += 1;
    }

    public void moveRight() {
        this.x -= 1;
    }

}