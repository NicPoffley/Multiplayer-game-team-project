package org.nightshade;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nightshade.game.Game;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game();
        game.initGame(stage);

    }
}
