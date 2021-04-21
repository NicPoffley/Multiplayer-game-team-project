package application.networking;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ClientServerController {

    public ClientServerController() {
    }

    public void serverToClientMessage(PrintWriter output, String message) {
        output.println(message);
        output.flush();
    }

    public void clientToServerMessage(PrintWriter output, String message) {
        output.println(message);
        output.flush();
    }

    public void clientToClientMessage(NetworkClient networkClient, String serverReply) {
        networkClient.outputSent(serverReply);
    }

}
