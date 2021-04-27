package org.nightshade.multiplayer;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Parallax {

    private final Image image1;
    private final Image image2;
    private final Image image3;
    private final Image image4;
    private final Image image5;
    private final Image image6;
    private final Image image7;

    private final int scaledWidth;

    private final ArrayList<Integer> image2XPositions;
    private final ArrayList<Integer> image3XPositions;
    private final ArrayList<Integer> image4XPositions;
    private final ArrayList<Integer> image5XPositions;
    private final ArrayList<Integer> image6XPositions;
    private final ArrayList<Integer> image7XPositions;

    public Parallax() {
        image1 = new Image("img/game/parallax/parallax-1.png");
        image2 = new Image("img/game/parallax/parallax-2.png");
        image3 = new Image("img/game/parallax/parallax-3.png");
        image4 = new Image("img/game/parallax/parallax-4.png");
        image5 = new Image("img/game/parallax/parallax-5.png");
        image6 = new Image("img/game/parallax/parallax-6.png");
        image7 = new Image("img/game/parallax/parallax-7.png");

        int imageWidth = 3960;
        scaledWidth = imageWidth * 2;
        ArrayList<Integer> initialXPositions = new ArrayList<>();
        initialXPositions.add(0);
        initialXPositions.add(scaledWidth);

        image2XPositions = new ArrayList<>();
        image3XPositions = new ArrayList<>();
        image4XPositions = new ArrayList<>();
        image5XPositions = new ArrayList<>();
        image6XPositions = new ArrayList<>();
        image7XPositions = new ArrayList<>();

        image2XPositions.addAll(initialXPositions);
        image3XPositions.addAll(initialXPositions);
        image4XPositions.addAll(initialXPositions);
        image5XPositions.addAll(initialXPositions);
        image6XPositions.addAll(initialXPositions);
        image7XPositions.addAll(initialXPositions);
    }

    public void move() {
        for (int i = 0; i < 2; i++) {
            moveHelper(image2XPositions, i, -1);
            moveHelper(image3XPositions, i, -2);
            moveHelper(image4XPositions, i, -3);
            moveHelper(image5XPositions, i, -4);
            moveHelper(image6XPositions, i, -5);
            moveHelper(image7XPositions, i, -5);
        }
    }

    private void moveHelper(ArrayList<Integer> imageXPositions, int index, int changeX) {
        int imageXPosition = imageXPositions.get(index);
        if (imageXPosition > -scaledWidth) {
            imageXPositions.set(index, imageXPosition + changeX);
        } else {
            imageXPositions.set(index, scaledWidth - 5);
        }
    }

    public void render(Renderer renderer, int xViewCoordinate) {
        int y = 0;
        int scaledHeight = 720;

        renderer.drawImage(image1, xViewCoordinate, 0, 1280, 720);

        for (int index = 0; index < 2; index ++) {
            renderer.drawImage(image2, image2XPositions.get(index), y, scaledWidth, scaledHeight);
            renderer.drawImage(image3, image3XPositions.get(index), y, scaledWidth, scaledHeight);
            renderer.drawImage(image4, image4XPositions.get(index), y, scaledWidth, scaledHeight);
            renderer.drawImage(image5, image5XPositions.get(index), y, scaledWidth, scaledHeight);
            renderer.drawImage(image6, image6XPositions.get(index), y, scaledWidth, scaledHeight);
            renderer.drawImage(image7, image7XPositions.get(index), y, scaledWidth, scaledHeight);
        }
    }
}