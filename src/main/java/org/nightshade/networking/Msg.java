package org.nightshade.networking;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {

    int PLAYER_NEW_MSG = 1;
    int PLAYER_MOVE_MSG = 2;
    int PLAYER_DEAD_MSG = 3;

    void send(DatagramSocket ds, String IP, int UDP_Port);
    void parse(DataInputStream dis);

}
