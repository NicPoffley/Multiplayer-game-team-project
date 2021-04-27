package org.nightshade.gui;

public class GameOverController {

    public void restartButton() {
        GuiHandler.stage.setScene(GuiHandler.singlePlayer);
    }

    public void mainMenuButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

}
