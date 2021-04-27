package org.nightshade.networking;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nightshade.gui.GuiHandler;
import org.nightshade.multiplayer.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Server extends Application {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int serverPort;
    private static boolean created = false;
    private static ServerLogic serverLogic;
    public static Stage serverStage;

    private static int portValue;
    //static GameLogic game = null;
    static Game game;
    static ServerSocket serverSocket;

    public Server() throws NumberFormatException, IOException {
        System.out.println("Please enter the server port number: ");
        serverPort = Integer.parseInt(br.readLine());
    }

    @Override
    public void start(Stage window) throws Exception {

        serverStage = window;
        GuiHandler gh = new GuiHandler();

        serverStage.setScene(gh.loadGui(serverStage));
        serverStage.show();

    }

    public static void main(String[] args) {
        //launch(args);
        while(!created) {
            try {
                Server server = new Server();
                serverLogic = new ServerLogic(serverPort);
                serverLogic.waitForPlayers();
            } catch (IOException e1) {
                System.out.println("Could not create server - check firewall");
            } catch (NumberFormatException e2) {
                System.out.println("Entered server port number incorrectly");
            } finally {
                if(serverLogic != null) {
                    serverLogic.kill();
                }
            }
        }
    }



}
