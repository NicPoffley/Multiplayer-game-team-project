package org.nightshade.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.nightshade.Main;
import org.nightshade.multiplayer.Client;
import org.nightshade.multiplayer.Game;

public class ServerLogic {

    private int portValue;
    //static GameLogic game = null;
    static Game game;
    static ServerSocket serverSocket;
    private static ArrayList<ClientThread> clientThreads;

    public ServerLogic(int portValue) throws IOException {
        this.portValue = portValue;
        serverSocket = new ServerSocket(portValue);
        System.out.println("Started game server");
        clientThreads = new ArrayList<>();
        //game = new GameLogic();
        //game = new Game(Server.serverStage);
    }

    public static Game getGame() {
        return game;
    }

    public static ArrayList<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public void waitForPlayers() throws IOException {
        int clientNo = 1;
        while(clientNo < 5) {
            Socket client = serverSocket.accept();
            System.out.println("Client arrived");
            System.out.println("Starting thread for client " + clientNo);
            ClientThread task = new ClientThread(client, game, clientNo);
            clientThreads.add(task);
            System.out.println("number of client threads: " + clientThreads.size());
            System.out.println("number of client threads: " + getClientThreads().size());
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
