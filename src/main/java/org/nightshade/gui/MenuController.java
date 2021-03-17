package org.nightshade.gui;


public class MenuController {

    public void singlePLayerButton (){
        GuiHandler.stage.setScene(GuiHandler.singlePlayer);
    }

    public void settingsButton (){
        GuiHandler.stage.setScene(GuiHandler.settings);
    }

    public void exitButton (){
        GuiHandler.stage.setScene(GuiHandler.titleScreen);
    }

    public void multiPlayerButton (){GuiHandler.stage.setScene(GuiHandler.multiPlayer);}
}
