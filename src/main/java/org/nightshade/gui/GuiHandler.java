package org.nightshade.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GuiHandler {

    public static Stage stage;

    public static Scene settings;
    public static Scene singlePlayer;
    public static Scene titleScreen;
    public static Scene multiPlayer;
    public static Scene menu;

    public Scene loadGui(Stage window) throws IOException {

        stage = window;

        Parent settingsRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/guiComponents/Settings.fxml")));
        Parent singlePlayerRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/guiComponents/SinglePlayer.fxml")));
        Parent titleScreenRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/guiComponents/TitleScreen.fxml")));
        Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/guiComponents/Menu.fxml")));
        Parent multiPlayerRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/guiComponents/MultiPlayer.fxml")));

        menu = new Scene(menuRoot, 1280, 720);
        singlePlayer = new Scene(singlePlayerRoot, 1280, 720);
        titleScreen = new Scene(titleScreenRoot, 1280, 720);
        settings = new Scene(settingsRoot, 1280, 720);
        multiPlayer = new Scene(multiPlayerRoot, 1280, 720);

        menu.getStylesheets().add("view/guiComponents/Buttons.css");
        singlePlayer.getStylesheets().add("view/guiComponents/Buttons.css");
        titleScreen.getStylesheets().add("view/guiComponents/Buttons.css");
        settings.getStylesheets().add("view/guiComponents/Buttons.css");
        multiPlayer.getStylesheets().add("view/guiComponents/Buttons.css");

        Image img = new Image("view/guiComponents/backbutton.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(80);
        view.setPreserveRatio(true);

        return (titleScreen);

    }
}
