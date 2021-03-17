package org.nightshade.networking;

import java.io.PrintWriter;

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

    public void clientToClientMessage(Client client, String serverReply) {
        client.outputSent(serverReply);
    }





}
