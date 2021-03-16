package org.nightshade.game;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Parallax {
    private final int levelWidth = 120;
    private final int blockWidth = 60;
    public Parallax() {
        initArrays();
    }

    private final Image parallaxOne = new Image ("view/parallax/1.png");
    private final Image parallaxTwo = new Image ("view/parallax/222.png");
    private final Image parallaxThree = new Image ("view/parallax/3.png");
    private final Image parallaxFour = new Image ("view/parallax/4.png");
    private final Image parallaxFive = new Image ("view/parallax/5.png");
    private final Image parallaxSix = new Image ("view/parallax/666.png");
    private final Image parallaxSeven = new Image ("view/parallax/7.png");

    private final ArrayList<Integer> parallax2X = new ArrayList<>();
    private final ArrayList<Integer> parallax3X = new ArrayList<>();
    private final ArrayList<Integer> parallax4X = new ArrayList<>();
    private final ArrayList<Integer> parallax5X = new ArrayList<>();
    private final ArrayList<Integer> parallax6X = new ArrayList<>();
    private final ArrayList<Integer> parallax7X = new ArrayList<>();

    public void initArrays(){
        int i = 0;
        while (i*1280<levelWidth*blockWidth){
            parallax2X.add(i*1280);
            parallax3X.add(i*1280);
            parallax4X.add(i*1280);
            parallax5X.add(i*1280);
            parallax6X.add(i*1280);
            parallax7X.add(i*1280);
            i++;
        }
    }

    public void drawParallax(Renderer renderer, int xViewCoordinate){
        renderer.drawImage(parallaxOne, xViewCoordinate ,0,1280,720);
        for(int i = 0; i < parallax2X.size(); i++){
            renderer.drawImage(parallaxTwo,parallax2X.get(i),0,1280,720);
            renderer.drawImage(parallaxThree,parallax3X.get(i),0,1280,720);
            renderer.drawImage(parallaxFour,parallax4X.get(i),0,1280,720);
            renderer.drawImage(parallaxFive,parallax5X.get(i),0,1280,720);
            renderer.drawImage(parallaxSix,parallax6X.get(i),0,1280,720);
            renderer.drawImage(parallaxSeven,parallax7X.get(i),0,1280,720);
        }
    }




    public void moveParallax() {
        for (int i = 0 ; i < parallax2X.size() ; i++){
            if (parallax2X.get(i)>-1280){
                parallax2X.set(i,parallax2X.get(i)-1);
            } else{
                parallax2X.set(i,blockWidth*levelWidth);
            } if (parallax3X.get(i)>-1280){
                parallax3X.set(i,parallax3X.get(i)-2);
            } else{
                parallax3X.set(i,blockWidth*levelWidth);
            } if (parallax4X.get(i)>-1280){
                parallax4X.set(i,parallax4X.get(i)-3);
            } else{
                parallax4X.set(i,blockWidth*levelWidth);
            } if (parallax5X.get(i)>-1280){
                parallax5X.set(i,parallax5X.get(i)-4);
            } else{
                parallax5X.set(i,blockWidth*levelWidth);
            } if (parallax6X.get(i)>-1280){
                parallax6X.set(i,parallax6X.get(i)-5);
            } else{
                parallax6X.set(i,blockWidth*levelWidth);
            } if (parallax7X.get(i)>-1280){
                parallax7X.set(i,parallax7X.get(i)-5);
            } else{
                parallax7X.set(i,blockWidth*levelWidth);
            }
        }
    }
}
