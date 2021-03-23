package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientLogic implements Runnable {

    private PrintWriter output;
    private BufferedReader input;
    private Client client;
    private Thread thread;
    private Socket server;
    private ClientServerController csc;

    public ClientLogic(String serverIp, int portValue, Client client) throws IOException {
        server = new Socket(serverIp, portValue);
        output = new PrintWriter(server.getOutputStream(), false);
        input = new BufferedReader(new InputStreamReader(server.getInputStream()));
        csc = new ClientServerController();
        this.client = client;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            waitForServer();
        } catch (SocketException e1) {
            csc.clientToClientMessage(client, "Connection lost to server");
        } catch (IOException e) {
            csc.clientToClientMessage(client, "Connection lost to server");
        } catch (RuntimeException e2) {
            csc.clientToClientMessage(client, "Connection lost to server");
        }
    }

    private void waitForServer() throws IOException, SocketException, RuntimeException {
        while(true) {
            String serverReply = input.readLine();
            csc.clientToClientMessage(client, serverReply);
        }
    }

    public void sendToServer(String message) {
        csc.clientToServerMessage(output, message);
    }


    public void moveL() {
        sendToServer("move left");
    }

    public void moveR() {
        sendToServer("move right");
    }

    public void jump() {
        sendToServer("jump");
    }

}
