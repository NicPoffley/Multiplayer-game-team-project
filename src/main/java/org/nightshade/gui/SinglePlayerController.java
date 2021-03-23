package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.nightshade.Main;
import org.nightshade.ai.AI;
import org.nightshade.ai.Difficulty;
import org.nightshade.game.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SinglePlayerController implements Initializable {

    @FXML private CheckBox ai1Check;
    @FXML private CheckBox ai2Check;
    @FXML private CheckBox ai3Check;

    @FXML public RadioButton ai1EasyRadio;
    @FXML private RadioButton ai1MediumRadio;
    @FXML private RadioButton ai1HardRadio;

    @FXML private RadioButton ai2EasyRadio;
    @FXML private RadioButton ai2MediumRadio;
    @FXML private RadioButton ai2HardRadio;

    @FXML private RadioButton ai3EasyRadio;
    @FXML private RadioButton ai3MediumRadio;
    @FXML private RadioButton ai3HardRadio;

    @FXML private ToggleGroup ai1ToggleGroup;
    @FXML private ToggleGroup ai2ToggleGroup;
    @FXML private ToggleGroup ai3ToggleGroup;

    private ArrayList<RadioButton> easyRadioButtons;
    private ArrayList<RadioButton> mediumRadioButtons;
    private ArrayList<RadioButton> hardRadioButtons;

    private ArrayList<RadioButton> ai1RadioButtons;
    private ArrayList<RadioButton> ai2RadioButtons;
    private ArrayList<RadioButton> ai3RadioButtons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ai1ToggleGroup = new ToggleGroup();
        ai2ToggleGroup = new ToggleGroup();
        ai3ToggleGroup = new ToggleGroup();

        ai1EasyRadio.setToggleGroup(ai1ToggleGroup);
        ai1MediumRadio.setToggleGroup(ai1ToggleGroup);
        ai1HardRadio.setToggleGroup(ai1ToggleGroup);

        ai2EasyRadio.setToggleGroup(ai2ToggleGroup);
        ai2MediumRadio.setToggleGroup(ai2ToggleGroup);
        ai2HardRadio.setToggleGroup(ai2ToggleGroup);

        ai3EasyRadio.setToggleGroup(ai3ToggleGroup);
        ai3MediumRadio.setToggleGroup(ai3ToggleGroup);
        ai3HardRadio.setToggleGroup(ai3ToggleGroup);

        easyRadioButtons = new ArrayList<>();
        easyRadioButtons.add(ai1EasyRadio);
        easyRadioButtons.add(ai2EasyRadio);
        easyRadioButtons.add(ai3EasyRadio);

        mediumRadioButtons = new ArrayList<>();
        mediumRadioButtons.add(ai1MediumRadio);
        mediumRadioButtons.add(ai2MediumRadio);
        mediumRadioButtons.add(ai3MediumRadio);

        hardRadioButtons = new ArrayList<>();
        hardRadioButtons.add(ai1HardRadio);
        hardRadioButtons.add(ai2HardRadio);
        hardRadioButtons.add(ai3HardRadio);

        ai1RadioButtons = new ArrayList<>();
        ai1RadioButtons.add(ai1EasyRadio);
        ai1RadioButtons.add(ai1MediumRadio);
        ai1RadioButtons.add(ai1HardRadio);

        ai2RadioButtons = new ArrayList<>();
        ai2RadioButtons.add(ai2EasyRadio);
        ai2RadioButtons.add(ai2MediumRadio);
        ai2RadioButtons.add(ai2HardRadio);

        ai3RadioButtons = new ArrayList<>();
        ai3RadioButtons.add(ai3EasyRadio);
        ai3RadioButtons.add(ai3MediumRadio);
        ai3RadioButtons.add(ai3HardRadio);

        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.addAll(ai1RadioButtons);
        radioButtons.addAll(ai2RadioButtons);
        radioButtons.addAll(ai3RadioButtons);

        for (RadioButton radioButton : radioButtons) {
            radioButton.setDisable(true);
        }
    }

    @FXML
    public void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @FXML
    public void startGame(ActionEvent event) {
        Game game = new Game(Main.stage);
    }

    @FXML
    public void startButtonHandler(ActionEvent event) {
        Game game = new Game(Main.stage);

        addAiPlayers(game, ai1Check, ai1ToggleGroup);

        addAiPlayers(game, ai2Check, ai2ToggleGroup);

        addAiPlayers(game, ai3Check, ai3ToggleGroup);
    }

    private void addAiPlayers(Game game, CheckBox checkBox, ToggleGroup toggleGroup) {
        if (checkBox.isSelected()) {
            if (easyRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.EASY));
            } else if (mediumRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.MEDIUM));
            } else if (hardRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.HARD));
            }
        }
    }

    @FXML
    public void checkBoxHandler(ActionEvent event) {
        checkBoxHandlerHelper(ai1Check, ai1EasyRadio, ai1ToggleGroup, ai1RadioButtons);

        checkBoxHandlerHelper(ai2Check, ai2EasyRadio, ai2ToggleGroup, ai2RadioButtons);

        checkBoxHandlerHelper(ai3Check, ai3EasyRadio, ai3ToggleGroup, ai3RadioButtons);
    }

    private void checkBoxHandlerHelper(CheckBox checkBox, RadioButton easyRadioButton, ToggleGroup toggleGroup, ArrayList<RadioButton> radioButtons) {
        String onText = "ON";
        String offText = "OFF";

        if (checkBox.isSelected()) {
            checkBox.setText(onText);
            if (toggleGroup.getSelectedToggle() == null) {
                easyRadioButton.setSelected(true);
            }
            for (RadioButton radioButton : radioButtons) {
                radioButton.setDisable(false);
            }
        } else {
            checkBox.setText(offText);
            for (RadioButton radioButton : radioButtons) {
                radioButton.setDisable(true);
            }
        }
    }
}