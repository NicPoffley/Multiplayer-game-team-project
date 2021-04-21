package application.networking;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
            output.println(clientNo);
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
                    //outputMessage("Executing command: " + command);
                    //super.processCommand(command, this);
                    String[] pos = input.readLine().split(" ");
                    int[] position = new int[]{Integer.parseInt(pos[0]), Integer.parseInt(pos[1])};
                    // record client updated location
                    if (Server.clientsPos.containsKey(clientNo)) {
                        Server.clientsPos.get(clientNo).add(position);
                    }
                    else {
                        Server.clientsPos.put(clientNo, new ArrayList<>());
                        Server.clientsPos.get(clientNo).add(position);
                    }
                    // send location to client

                    for (int k: Server.clientsPos.keySet())
                    {
                        if (k != clientNo) {
                            int n = Server.clientsPos.get(k).size();
                            output.println(k + " " + Server.clientsPos.get(k).get(n-1)[0] + " " + Server.clientsPos.get(k).get(n-1)[1]);
                        }

                    }
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
