package application.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NetworkClient {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String serverIp;
    private int portValue;
    private ClientLogic clientLogic;

    public NetworkClient() {
        try {
            System.out.println("Please enter the server name or IP address: ");
            serverIp = br.readLine();
            System.out.println("Please enter the port number for the server: ");
            portValue = Integer.parseInt(br.readLine());
            clientLogic = new ClientLogic(serverIp, portValue, this);
        } catch (IOException e1) {
            System.out.println("Buffered reader does not exist");
        } catch (NumberFormatException e2) {
            System.out.println("port can only be a number");
        }
    }

    public NetworkClient(String serverIp, int portValue) throws IOException {
        clientLogic = new ClientLogic(serverIp, portValue, this);
    }

    public void actOnInput() throws IOException {
        while(true) {
            System.out.println("Enter command: ");
            String command = br.readLine();
            switch (command) {
                case "L":
                    clientLogic.moveL();
                    break;
                case "R":
                    clientLogic.moveR();
                    break;
                case "J":
                    clientLogic.jump();
                    break;
                case "EXIT":
                    System.exit(0);
                    break;
            }
        }
    }


    public void outputSent(String reply) {
        System.out.println(reply);
    }

}
