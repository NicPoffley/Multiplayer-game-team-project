package application.game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Client {
    //add:
    private int ID;

    private boolean isLive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite clientSprite;

    public Client(String pic) {
        //add:
        this.ID = (int)Math.random();

        this.isLive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        clientSprite = createSprite(pic);
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public Sprite getClientSprite() {
        return clientSprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }
    //add:
    public int getID(){
        return ID;
    }
    //
    public void jump() {
        if (canJump) {
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    public Sprite createSprite(String pic) {
        if (pic.equals("Body.png"))
            return new Sprite(new Image(pic),300,50);
        return new Sprite(new Image(pic),600,150);
    }

    public void moveX(int value){
        //boolean movingRight = value > 0;
        //getClientSprite().setPositionX(getClientSprite().getPositionX() + (movingRight ? 1 : -1));
        getClientSprite().setPositionX(getClientSprite().getPositionX() + value);
    }

    public void moveY(int value){
        //boolean movingDown = value > 0;
        //getClientSprite().setPositionY(getClientSprite().getPositionY() + (movingDown ? 1 : -1));
        getClientSprite().setPositionY(getClientSprite().getPositionY() + value);
    }




}
