package org.nightshade;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nightshade.game.Game;
import org.nightshade.gui.GuiHandler;

import java.util.ArrayList;

public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        stage = window;
        GuiHandler gh = new GuiHandler();

        stage.setScene(gh.loadGui(stage));
        stage.show();

    }
}

