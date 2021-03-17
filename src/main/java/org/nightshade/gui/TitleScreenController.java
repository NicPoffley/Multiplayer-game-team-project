package org.nightshade.gui;

import javafx.fxml.FXML;

public class TitleScreenController {
    @FXML
    public void titleButton(){
        GuiHandler.stage.setScene(GuiHandler.menu);
    }
}
