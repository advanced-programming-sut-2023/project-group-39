package model;

import model.chat.PrivateChat;
import model.chat.PublicChat;
import model.chat.RoomChat;
import model.user.User;
import java.util.ArrayList;

public class DataBaseUser {
    private User user;

    public DataBaseUser(){
        this.user = user;
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

    public DataBaseUser(User user, ArrayList<RoomChat> roomChats, ArrayList<PrivateChat> privateChats, ArrayList<PublicChat> publicChats) {
        this.user = user;
        this.roomChats = roomChats;
        this.privateChats = privateChats;
        this.publicChats = publicChats;
        this.privateChats=new ArrayList<>();
        this.publicChats=new ArrayList<>();
        this.roomChats=new ArrayList<>();
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
    public synchronized boolean hasPrivateChats (String username) {
        if (privateChats ==null || privateChats.size() == 0)
            return false;
        for (PrivateChat privateChat : privateChats) {
            for (User user1 : privateChat.getGroupId()) {
                if (user1.getUsername().equals(username))
                    return true;
            }
        }
        return false;
    }
}
