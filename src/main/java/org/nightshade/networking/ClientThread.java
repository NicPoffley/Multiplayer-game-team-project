package org.nightshade.networking;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {

    private BufferedReader input;
    private PrintWriter output;
    private int clientNo;
    private Socket socket;
    private GameLogic game;
    private ClientServerController csc;

    public ClientThread(Socket client, GameLogic game, int clientNo) {

        this.game = game;
        this.clientNo = clientNo;
        this.socket = client;
        csc = new ClientServerController();
        //super.setCsc(csc);
        //super.setGame(game);
        System.out.println(client.toString());

        // create the streams:
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), false);
            //following line adds player to the game and tells them the goal:
            // super.addGame(game, this); // IMPLEMENT LATER
        } catch (IOException e) {
            System.err.println("Streams not set up for client");
        }

    }


    @Override
    public void run() {

        String command = "";
        try {
            while(command != null) {
                command = input.readLine();
                if(command == null || command.equalsIgnoreCase("STOP")) {
                    System.out.println("Client " + clientNo + " left");
                } else {
                    System.out.println("Command sent was '" + command + "' by client no. " + clientNo);
                    outputMessage("Executing command: " + command);
                    //super.processCommand(command, this);
                }
            }
        } catch (IOException e) {
            System.out.println("Client " + clientNo + " left");
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    // send a message to the client:
    protected void outputMessage(String message) {
        csc.serverToClientMessage(output, message);
    }


}
