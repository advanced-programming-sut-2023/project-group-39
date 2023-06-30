package org.example;

import org.example.chat.PrivateChat;
import org.example.chat.PublicChat;
import org.example.chat.RoomChat;

import java.util.ArrayList;

public class DataBaseUser {
    private User user;
    private ArrayList<RoomChat> roomChats;
    private ArrayList<PrivateChat> privateChats;
    private ArrayList<PublicChat> publicChats;

    public DataBaseUser(User user, ArrayList<RoomChat> roomChats, ArrayList<PrivateChat> privateChats, ArrayList<PublicChat> publicChats) {
        this.user = user;
        this.roomChats = roomChats;
        this.privateChats = privateChats;
        this.publicChats = publicChats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
