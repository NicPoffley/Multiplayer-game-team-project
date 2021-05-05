package org.nightshade.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ServerLogic class
 * The logic for the Server, which accept client connections
 */
public class ServerLogic {

    private final ServerSocket serverSocket;
    private int numClients;
    private final ArrayList<String> playerNames;
    private final ArrayList<PlayerMoveMsg> playerMoveMsgs;

    /**
     * Constructor for the ServerLogic class
     * Creates a new ServerSocket and calls a method to wait for clients to connect
     * @param portValue Port number for the Server
     * @throws IOException
     */
    public ServerLogic(int portValue) throws IOException {
        this.serverSocket = new ServerSocket(portValue);
        this.numClients = 0;
        this.playerNames = new ArrayList<>();
        this.playerMoveMsgs = new ArrayList<>();
        System.out.println("Started game server");
        waitForPlayers();
    }

    /**
     * Returns an ArrayList of PlayerMoveMsgs received from clients
     * @return ArrayList of received PlayerMoveMsgs
     */
    public ArrayList<PlayerMoveMsg> getMoveMsgs() {
        return playerMoveMsgs;
    }

    /**
     * Adds a received PlayerMoveMsg to the ArrayList
     * @param moveMsg Received PlayerMoveMsg object
     */
    public void addMsg(PlayerMoveMsg moveMsg) {
        playerMoveMsgs.add(moveMsg);
    }

    /**
     * Replaces a PlayerMoveMsg at a specified index with another
     * @param index Index position of the PlayerMoveMsg in the ArrayList
     * @param moveMsg Received PlayerMoveMsg object
     */
    public void replaceMsg(int index, PlayerMoveMsg moveMsg) {
        playerMoveMsgs.set(index, moveMsg);
    }

    /**
     * Returns the number of clients connected
     * @return Number of clients connected
     */
    public int getNumClients() {
        return this.numClients;
    }

    /**
     * Increments the number of clients connected
     */
    public void incNumClients() {
        this.numClients ++;
    }

    /**
     * Returns the ArrayList of players' names
     * @return ArrayList of players' names
     */
    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    /**
     * Adds the specified player's name to the ArrayList
     * @param playerName Name of the player to add
     */
    public void addPlayerName(String playerName) {
        playerNames.add(playerName);
    }

    /**
     * Waits for clients & accepts connections, and creates a new ClientThread for each Client
     * @throws IOException
     */
    public void waitForPlayers() throws IOException {
        int clientNo = 1;
        while(clientNo < 10) {
            Socket socket = serverSocket.accept();
            System.out.println("Client arrived");
            ClientThread task = new ClientThread(socket, clientNo, this);
            clientNo ++;
            incNumClients();
            new Thread(task).start();
        }
    }

    /**
     * Closes the ServerSocket
     */
    public void kill() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close serverSocket");
        }
    }
}
