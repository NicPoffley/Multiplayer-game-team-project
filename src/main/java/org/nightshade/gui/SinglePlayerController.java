package org.nightshade.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.nightshade.Main;
import org.nightshade.game.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class SinglePlayerController {

    @FXML private CheckBox ai1Check;
    @FXML private CheckBox ai2Check;
    @FXML private CheckBox ai3Check;



    @FXML private CheckBox ai1EasyCheck;
    @FXML private CheckBox ai1MediumCheck;
    @FXML private CheckBox ai1HardCheck;


    @FXML private CheckBox ai2EasyCheck;
    @FXML private CheckBox ai2MediumCheck;
    @FXML private CheckBox ai2HardCheck;


    @FXML private CheckBox ai3EasyCheck;
    @FXML private CheckBox ai3MediumCheck;
    @FXML private CheckBox ai3HardCheck;



    @FXML private Button start;
    @FXML private Button back;

    private int count;

    private ArrayList<String> aiList = new ArrayList<>(Arrays.asList("","",""));


    @FXML
    void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @FXML
    void startGame(ActionEvent event) {
        System.out.println(aiList);
        Game game = new Game();
        game.initGame(Main.stage, count);
    }

    @FXML
    void checkBoxHandler (ActionEvent event){


        //adds up the number of AIs selected and stores it in count
        count = 0;
        if (ai1Check.isSelected() && (!(ai1EasyCheck.isSelected())) && (!(ai1MediumCheck.isSelected())) && (!(ai1HardCheck.isSelected()))){
            count ++;
            ai1Check.setText("ON");
            ai1EasyCheck.setDisable(false);
            ai1MediumCheck.setDisable(false);
            ai1HardCheck.setDisable(false);

            aiList.set(0,"EASY");
            ai1EasyCheck.setSelected(true);
        }else if (!(ai1Check.isSelected())){
            ai1Check.setText("OFF");
            ai1EasyCheck.setDisable(true);
            ai1MediumCheck.setDisable(true);
            ai1HardCheck.setDisable(true);

            ai1EasyCheck.setSelected(false);
            ai1MediumCheck.setSelected(false);
            ai1HardCheck.setSelected(false);
            aiList.set(0,"");
        }
        if (ai2Check.isSelected() && (!(ai2EasyCheck.isSelected())) && (!(ai2MediumCheck.isSelected())) && (!(ai2HardCheck.isSelected()))){
            count ++;
            ai2Check.setText("ON");
            ai2EasyCheck.setDisable(false);
            ai2MediumCheck.setDisable(false);
            ai2HardCheck.setDisable(false);

            aiList.set(1,"EASY");
            ai2EasyCheck.setSelected(true);
        }else if (!(ai2Check.isSelected())){
            ai2Check.setText("OFF");
            ai2EasyCheck.setDisable(true);
            ai2MediumCheck.setDisable(true);
            ai2HardCheck.setDisable(true);

            ai2EasyCheck.setSelected(false);
            ai2MediumCheck.setSelected(false);
            ai2HardCheck.setSelected(false);
            aiList.set(1,"");
        }
        if (ai3Check.isSelected() && (!(ai3EasyCheck.isSelected())) && (!(ai3MediumCheck.isSelected())) && (!(ai3HardCheck.isSelected()))){
            count ++;
            ai3Check.setText("ON");
            ai3EasyCheck.setDisable(false);
            ai3MediumCheck.setDisable(false);
            ai3HardCheck.setDisable(false);

            aiList.set(2,"EASY");
            ai3EasyCheck.setSelected(true);
        }else if (!(ai3Check.isSelected())){
            ai3Check.setText("OFF");
            ai3EasyCheck.setDisable(true);
            ai3MediumCheck.setDisable(true);
            ai3HardCheck.setDisable(true);

            ai3EasyCheck.setSelected(false);
            ai3MediumCheck.setSelected(false);
            ai3HardCheck.setSelected(false);
            aiList.set(2,"");
        }

        //makes sure only one difficultly is selected for Ai 1
        if (event.getSource().equals(ai1EasyCheck)){
            ai1MediumCheck.setSelected(false);
            ai1HardCheck.setSelected(false);
            aiList.set(0,"EASY");
        }
        if (event.getSource().equals(ai1MediumCheck)){
            ai1EasyCheck.setSelected(false);
            ai1HardCheck.setSelected(false);
            aiList.set(0,"MEDIUM");
        }
        if (event.getSource().equals(ai1HardCheck)){
            ai1MediumCheck.setSelected(false);
            ai1EasyCheck.setSelected(false);
            aiList.set(0,"HARD");
        }


        //makes sure only one difficultly is selected for Ai 2
        if (event.getSource().equals(ai2EasyCheck)){
            ai2MediumCheck.setSelected(false);
            ai2HardCheck.setSelected(false);
            aiList.set(1,"EASY");
        }
        if (event.getSource().equals(ai2MediumCheck)){
            ai2EasyCheck.setSelected(false);
            ai2HardCheck.setSelected(false);
            aiList.set(1,"MEDIUM");
        }
        if (event.getSource().equals(ai2HardCheck)){
            ai2MediumCheck.setSelected(false);
            ai2EasyCheck.setSelected(false);
            aiList.set(1,"HARD");
        }


        //makes sure only one difficultly is selected for Ai 3
        if (event.getSource().equals(ai3EasyCheck)){
            ai3MediumCheck.setSelected(false);
            ai3HardCheck.setSelected(false);
            aiList.set(2,"EASY");
        }
        if (event.getSource().equals(ai3MediumCheck)){
            ai3EasyCheck.setSelected(false);
            ai3HardCheck.setSelected(false);
            aiList.set(2,"MEDIUM");
        }
        if (event.getSource().equals(ai3HardCheck)){
            ai3MediumCheck.setSelected(false);
            ai3EasyCheck.setSelected(false);
            aiList.set(2,"HARD");
        }




    }

}
