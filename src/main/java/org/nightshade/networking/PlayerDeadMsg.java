package org.nightshade.networking;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public class PlayerDeadMsg implements Msg {

    private int msgType = Msg.PLAYER_DEAD_MSG;
    private String name;
    private Client c; // should this be a Client from multiplayer or networking ??

    public PlayerDeadMsg(String name) {
        this.name = name;
    }

    public PlayerDeadMsg(Client c) {
        this.c = c;
    }

    @Override
    public void send(DatagramSocket ds, String IP, int UDP_Port) {
        // add code here

    }

    @Override
    public void parse(DataInputStream dis) {
        // add code here

    }

}
