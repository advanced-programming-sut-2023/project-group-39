package org.example;

import org.example.chat.PrivateChat;
import org.example.chat.PublicChat;
import org.example.chat.RoomChat;

import java.util.ArrayList;

public class DataBaseUser {
    private String username;

    public DataBaseUser(){
        this.username=getUsername();
        this.roomChats = roomChats;
        this.privateChats = privateChats;
        this.publicChats = publicChats;
        this.privateChats=new ArrayList<>();
        this.publicChats=new ArrayList<>();
        this.roomChats=new ArrayList<>();
    }

    private ArrayList<RoomChat> roomChats = new ArrayList<>();
    private ArrayList<PrivateChat> privateChats = new ArrayList<>();
    private ArrayList<PublicChat> publicChats = new ArrayList<>();

    public DataBaseUser(String username, ArrayList<RoomChat> roomChats, ArrayList<PrivateChat> privateChats, ArrayList<PublicChat> publicChats) {
        this.username = username;
        this.roomChats = roomChats;
        this.privateChats = privateChats;
        this.publicChats = publicChats;
        this.privateChats=new ArrayList<>();
        this.publicChats=new ArrayList<>();
        this.roomChats=new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<RoomChat> getRoomChats() {
        return roomChats;
    }

    public void setRoomChats(ArrayList<RoomChat> roomChats) {
        this.roomChats = roomChats;
    }

    public ArrayList<PrivateChat> getPrivateChats() {
        return privateChats;
    }

    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }

    public ArrayList<PublicChat> getPublicChats() {
        return publicChats;
    }

    public void setPublicChats(ArrayList<PublicChat> publicChats) {
        this.publicChats = publicChats;
    }
    public synchronized void addChatToPrivate(PrivateChat privateChat){
        this.privateChats.add(privateChat);
    }
    public synchronized void addChatToRoom(RoomChat roomChat){
        this.roomChats.add(roomChat);
    }
    public synchronized void addChatToPublic(PublicChat publicChat){
        this.publicChats.add(publicChat);
    }
    public synchronized boolean hasPrivateChats (String username) {
        if (privateChats == null || privateChats.size() == 0)
            return false;
        for (PrivateChat privateChat : privateChats) {
            for (String user1 : privateChat.getGroupId()) {
                if (user1.equals(username))
                    return true;
            }
        }
        return false;
    }
}
