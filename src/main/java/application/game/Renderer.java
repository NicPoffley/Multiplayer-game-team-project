package application.game;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Renderer {

    private final Group group;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    public Renderer() {
        group = new Group();
        canvas = new Canvas(500, 500);
        group.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Renderer(int w, int h) {
        group = new Group();
        canvas = new Canvas(w, h);
        group.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Group getGroup() {
        return group;
    }

    public double getTransLateX(){
        return canvas.getTranslateX();
    }

    public void setTransLateX(int x){
        canvas.setTranslateX(x);
    }

    public void setWidth(int x) {
        canvas.setWidth(x);
    }

    public void setHeight(int x) {
        canvas.setHeight(x);
    }

    public void drawImage(Image image, int x, int y) {
        graphicsContext.drawImage(image, x, y);
    }

    public void drawImage(Image image, int x, int y, int w, int h) {
        graphicsContext.drawImage(image, x, y, w, h);
    }

    public void drawRectangle(int x, int y, int w, int h, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, w, h);
    }

    public void drawCircle(int x, int y, int c, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x, y, c, c);
    }

    public void drawTriangle(double[] xs, double[] ys, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(xs, ys, 3);
    }

}
