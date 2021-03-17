package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int serverPort;
    private static boolean created = false;
    private static ServerLogic serverLogic;

    public Server() throws NumberFormatException, IOException {
        System.out.println("Please enter the server port number: ");
        serverPort = Integer.parseInt(br.readLine());
    }

    public static void main(String[] args) {
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
