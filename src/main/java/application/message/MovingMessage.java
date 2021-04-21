package application.message;


import application.game.MainGame;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class MovingMessage implements Message {

    private int id;
    private ArrayList<String> movingMessage;
    private MainGame mainGame;

    public MovingMessage(int id, ArrayList<String> message){
        this.id = id;
        this.movingMessage = message;
    }

    public MovingMessage(MainGame mainGame){
        this.mainGame = mainGame;
    }

    @Override
    public void send(DatagramSocket ds, String IP, int UDP_Port) {
        ByteArrayOutputStream packages = new ByteArrayOutputStream(30);
        DataOutputStream dos = new DataOutputStream(packages);
        try {
            dos.writeInt(id);
            dos.writeUTF(movingMessage.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buf = packages.toByteArray();
        try{
            DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, UDP_Port));
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> parse(int clientsId, DataInputStream dis) {
        try{
            int id = dis.readInt();
            String Message= dis.readUTF();
            ArrayList<String> movingMessage = new ArrayList<>(Arrays.asList(Message.split(",")));
                if(clientsId == id){
                    return movingMessage;
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
