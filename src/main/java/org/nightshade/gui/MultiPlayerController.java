package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class MultiPlayerController {

    @FXML private TextField serverIpBox;
    @FXML private TextField portNumBox;

    public void playButton(){
        String serverIp = serverIpBox.getText();
        String portNum = portNumBox.getText();

        System.out.println(serverIp);
        System.out.println(portNum);
    }
    public void backButton(){
        GuiHandler.stage.setScene(GuiHandler.menu);
    }
}
