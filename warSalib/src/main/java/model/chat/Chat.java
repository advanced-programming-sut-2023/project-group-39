package org.example.chat;

import org.example.Message;
import org.example.User;

import java.util.ArrayList;

public class Chat {
    ArrayList <User> groupId = new ArrayList<>();
    ArrayList <MessageChat> messages = new ArrayList<>();
    public synchronized void addPeople(User user) {
         groupId.add(user);
    }

    public ArrayList<User> getGroupId() {
        return groupId;
    }

    public void setGroupId(ArrayList<User> groupId) {
        this.groupId = groupId;
    }

    public synchronized void addMessage(MessageChat message) {
        messages.add(message);
    }

    public void setMessages(ArrayList<MessageChat> messages) {
        this.messages = messages;
    }
}
