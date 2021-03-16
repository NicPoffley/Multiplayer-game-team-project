package org.nightshade;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nightshade.game.Game;
import org.nightshade.networking.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends Application {

    private static boolean created = false;
    private static ServerLogic serverLogic;
    private static int serverPort;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(!created) {
            try {
                //Server server = new Server();
                System.out.println("Please enter the server port number: ");
                serverPort = Integer.parseInt(br.readLine());
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

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game();
        game.initGame(stage);

    }
}
