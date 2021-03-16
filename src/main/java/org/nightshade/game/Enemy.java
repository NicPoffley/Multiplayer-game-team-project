package org.nightshade.game;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Enemy {

    private final Sprite enemySprite;
    private final int speed;
    private boolean direction;
    private int offset;

    public Enemy(int speed, int direction, int x, int y) {
        this.enemySprite = new Sprite(new Image("view/enemy.png"),x,y);
        this.speed = speed;
        this.offset = 0;
        this.direction = direction == 1;
    }

    public Sprite getEnemySprite() {
        return enemySprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }

    public void moveEnemy(){
        if (this.direction){
            if (offset > 180) {
                direction = false;
            }
            else {
                offset = offset+speed;
                enemySprite.setPositionX(enemySprite.getPositionX()+speed);
            }
        } else{
            if (offset < -180) {
                direction = true;
            }
            else {
                offset = offset-speed;
                enemySprite.setPositionX(enemySprite.getPositionX()-speed);
            }
        }
    }



}
