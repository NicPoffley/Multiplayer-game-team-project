package org.nightshade.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.nightshade.Main;
import org.nightshade.multiplayer.Client;
import org.nightshade.multiplayer.Game;
import org.nightshade.networking.ServerLogic;

public class MultiPlayerLobbyController implements Initializable {

    @FXML private TableView<Player> tableView;
    @FXML private TableColumn<Player,String>nameColumn;
    @FXML private TableColumn<Player,String>readyColumn;
    @FXML private Button readyButton;

    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    public void readyButton() {
        if (GuiHandler.player.getReady() == "NOT READY") {
            GuiHandler.player.setReady("READY");
            tableView.refresh();
            readyButton.setText("Not Ready");
        }else{
            GuiHandler.player.setReady("NOT READY");
            tableView.refresh();
            readyButton.setText("Ready");
        }

        /*if(ServerLogic.getClientThreads() != null) {
            int numClients = ServerLogic.getClientThreads().size();
            System.out.println("number of clients: " + numClients);
        } else {
            System.out.println("no client threads");
        }*/

        boolean allReady = false;
        for (Player ready : tableView.getItems()) {
            if(readyColumn.getCellObservableValue(ready).getValue() == "NOT READY") {
                allReady = false;
                break;
            } else {
                allReady = true;
            }
        }
        if(allReady) {
            // start game
            System.out.println("all players ready");
            Game game = new Game(Main.stage, tableView.getItems().size()); // need to create game in server and add to that instead
            //game.addClient(client);
            
        }


    }

    public void makeTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("Name"));
        readyColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("Ready"));
        tableView.setItems(getPlayers());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeTable();
    }

    public ObservableList<Player> getPlayers(){
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.add(GuiHandler.player);
        //System.out.println(GuiHandler.player.getReady());
        players.add(new Player("player 2", "READY"));
        players.add(new Player("FifaPlayer52", "READY"));
        players.add(new Player("brian1997", "READY"));

        return players;
    }
}
