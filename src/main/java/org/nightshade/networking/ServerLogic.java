package org.nightshade.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.nightshade.Main;
import org.nightshade.multiplayer.Client;
import org.nightshade.multiplayer.Game;

public class ServerLogic {

    private int portValue;
    static GameLogic game = null;
    static ServerSocket serverSocket;

    public ServerLogic(int portValue) throws IOException {
        this.portValue = portValue;
        serverSocket = new ServerSocket(portValue);
        System.out.println("Started game server");
        //game = new GameLogic();
        Game game = new Game(Main.stage);
    }

    public void waitForPlayers() throws IOException {
        int clientNo = 1;
        while(true) {
            Socket client = serverSocket.accept();
            System.out.println("Client arrived");
            System.out.println("Starting thread for client " + clientNo);
            ClientThread task = new ClientThread(client, game, clientNo);
            clientNo++;
            new Thread(task).start();
        }
    }

    public void kill() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close serverSocket");
        }

    }
}
