package org.nightshade.multiplayer;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class MovingPlatform {
    private final Sprite sprite;
    private int speed;
    private Direction direction;
    private int offset;
    public MovingPlatform(int x, int y, int speed, Direction direction){
        this.sprite = new Sprite(new Image("img/game/dark-grass.png"),x,y);
        this.speed = speed;
        this.direction = direction;
        this.offset = 0;
    }
    public int getSpeed() {
        return speed;
    }
    public Direction getDirection() {
        return direction;
    }
    public Sprite getSprite(){
        return this.sprite;
    }
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }
    public void movePlatform(){
        if (this.direction == Direction.FORWARD) {
            if (offset > 180) {
                direction = Direction.BACKWARD;
            }
            else{
                offset = offset + speed;
                sprite.setX(sprite.getX() + speed);
            }
        } else {
            if (offset < -180) {
                direction = Direction.FORWARD;
            } else {
                offset = offset - speed;
                sprite.setX(sprite.getX() - speed);
            }
        }
    }
}