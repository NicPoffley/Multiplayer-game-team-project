package application.networking;

import application.game.MainGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientLogic implements Runnable {
    public static PrintWriter output;
    public static BufferedReader input;
    private NetworkClient networkClient;
    private Thread thread;
    public static Socket server;
    public static int[] other;
    private ClientServerController csc;
    public static int clientname;


    public ClientLogic(String serverIp, int portValue, NetworkClient networkClient) throws IOException {
        server = new Socket(serverIp, portValue);

        input = new BufferedReader(new InputStreamReader(server.getInputStream()));

        output = new PrintWriter(server.getOutputStream(), false);
        csc = new ClientServerController();
        this.networkClient = networkClient;
        //clientname = Integer.parseInt(input.readLine());
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            waitForServer();
        } catch (SocketException e1) {
            csc.clientToClientMessage(networkClient, "Connection lost to server");
        } catch (IOException e) {
            csc.clientToClientMessage(networkClient, "Connection lost to server");
        } catch (RuntimeException e2) {
            csc.clientToClientMessage(networkClient, "Connection lost to server");
        }
    }

    private void waitForServer() throws IOException, SocketException, RuntimeException {
        while(true) {
            String[] serverReply = input.readLine().split(" ");
            other = new int[]{Integer.parseInt(serverReply[0]), Integer.parseInt(serverReply[1]), Integer.parseInt(serverReply[2])};
            System.out.println("debug......client");
            //csc.clientToClientMessage(networkClient, serverReply);
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
