package org.nightshade.networking;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public class PlayerMoveMsg implements Msg {

    private int msgType = Msg.PLAYER_MOVE_MSG;
    private String name;
    private int x;
    private int y;
    private String movement;
    private Client c; // should this be a Client from multiplayer or networking ??

    public PlayerMoveMsg(String name, int x, int y, String movement) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.movement = movement;
    }

    public PlayerMoveMsg(Client c) {
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
