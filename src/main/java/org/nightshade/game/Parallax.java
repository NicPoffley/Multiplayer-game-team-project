package org.nightshade.game;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Parallax {
    public Parallax() {
        initArrays();
    }

    private final Image parallaxOne = new Image ("view/parallaxScroll/1.png");
    private final Image parallaxTwo = new Image ("view/parallaxScroll/2.png");
    private final Image parallaxThree = new Image ("view/parallaxScroll/3.png");
    private final Image parallaxFour = new Image ("view/parallaxScroll/4.png");
    private final Image parallaxFive = new Image ("view/parallaxScroll/5.png");
    private final Image parallaxSix = new Image ("view/parallaxScroll/6.png");
    private final Image parallaxSeven = new Image ("view/parallaxScroll/7.png");

    private final ArrayList<Integer> parallax2X = new ArrayList<>();
    private final ArrayList<Integer> parallax3X = new ArrayList<>();
    private final ArrayList<Integer> parallax4X = new ArrayList<>();
    private final ArrayList<Integer> parallax5X = new ArrayList<>();
    private final ArrayList<Integer> parallax6X = new ArrayList<>();
    private final ArrayList<Integer> parallax7X = new ArrayList<>();

    public void initArrays(){
        for(int i = 0 ; i < 2 ; i++){
            parallax2X.add((int) (i*parallaxTwo.getWidth()*2));
            parallax3X.add((int) (i*parallaxTwo.getWidth()*2));
            parallax4X.add((int) (i*parallaxTwo.getWidth()*2));
            parallax5X.add((int) (i*parallaxTwo.getWidth()*2));
            parallax6X.add((int) (i*parallaxTwo.getWidth()*2));
            parallax7X.add((int) (i*parallaxTwo.getWidth()*2));
        }
    }


    public void drawParallax(Renderer renderer,int xViewCoordinate){
        renderer.drawImage(parallaxOne, xViewCoordinate ,0,1280,720);
        renderer.drawImage(parallaxTwo,parallax2X.get(0),0,7920,720);
        renderer.drawImage(parallaxTwo,parallax2X.get(1),0,7920,720);
        renderer.drawImage(parallaxThree,parallax3X.get(0),0,7920,720);
        renderer.drawImage(parallaxThree,parallax3X.get(1),0,7920,720);
        renderer.drawImage(parallaxFour,parallax4X.get(0),0,7920,720);
        renderer.drawImage(parallaxFour,parallax4X.get(1),0,7920,720);
        renderer.drawImage(parallaxFive,parallax5X.get(0),0,7920,720);
        renderer.drawImage(parallaxFive,parallax5X.get(1),0,7920,720);
        renderer.drawImage(parallaxSix,parallax6X.get(0),0,7920,720);
        renderer.drawImage(parallaxSix,parallax6X.get(1),0,7920,720);
        renderer.drawImage(parallaxSeven,parallax7X.get(0),0,7920,720);
        renderer.drawImage(parallaxSeven,parallax7X.get(1),0,7920,720);
    }




    public void moveParallax() {
        for (int i = 0 ; i < parallax2X.size() ; i++){
            if (parallax2X.get(i)>-7920){
                parallax2X.set(i,parallax2X.get(i)-1);
            } else{
                parallax2X.set(i, (int) (parallaxTwo.getWidth()*2)-5);
            } if (parallax3X.get(i)>-7920){
                parallax3X.set(i,parallax3X.get(i)-2);
            } else{
                parallax3X.set(i, (int) (parallaxThree.getWidth()*2)-5);
            } if (parallax4X.get(i)>-7920){
                parallax4X.set(i,parallax4X.get(i)-3);
            } else{
                parallax4X.set(i, (int) (parallaxFour.getWidth()*2)-5);
            } if (parallax5X.get(i)>-7920){
                parallax5X.set(i,parallax5X.get(i)-4);
            } else{
                parallax5X.set(i, (int) (parallaxFive.getWidth()*2)-5);
            } if (parallax6X.get(i)>-7920){
                parallax6X.set(i,parallax6X.get(i)-5);
            } else{
                parallax6X.set(i, (int) (parallaxTwo.getWidth()*2)-5);
            } if (parallax7X.get(i)>-7920){
                parallax7X.set(i,parallax7X.get(i)-5);
            } else{
                parallax7X.set(i, (int) (parallaxSeven.getWidth()*2)-5);
            }
        }
    }
}
