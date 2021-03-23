package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.nightshade.Main;
import org.nightshade.game.Game;
import org.nightshade.networking.Client;
import org.nightshade.networking.ClientLogic;

import java.io.IOException;
import java.util.ArrayList;


public class MultiPlayerController {

    @FXML
    private TextField serverIpBox;

    @FXML
    private TextField portNumBox;

    public void playButton() throws IOException {

        ClientLogic clientLogic;
        String serverIp = serverIpBox.getText();
        int portValue = Integer.parseInt(portNumBox.getText());
        System.out.println(serverIp);
        System.out.println(portValue);

        Client client = new Client(serverIp, portValue);
        //clientLogic = new ClientLogic(serverIp, portValue, client);


        Game game = new Game(Main.stage);

    }

    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

}

