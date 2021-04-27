package org.nightshade.gui;

public class Player {
    private String ip;
    public String name;
    public String ready;

    public Player(String name){
        this.name = name;
        this.ready = "NOT READY";
    }

    public Player(String name, String ready) {
        this.name = name;
        this.ready = ready;
    }

    public String getIp(){
        return this.ip;
    }
    public String getName(){
        return this.name;
    }
    public String getReady(){
        return this.ready;
    }

    public void setIp(String newIp){
        this.ip = newIp;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setReady(String newReady){
        this.ready = newReady;
    }
}
