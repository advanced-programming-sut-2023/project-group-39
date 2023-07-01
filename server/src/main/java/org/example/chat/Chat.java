package org.example.chat;

import org.example.Message;
import org.example.User;

import java.util.ArrayList;

public class Chat {
    ArrayList <String> groupId = new ArrayList<>();
    ArrayList <MessageChat> messages = new ArrayList<>();
    public synchronized void addPeople(String username) {
         groupId.add(username);
    }

    public ArrayList<String> getGroupId() {
        return groupId;
    }

    public void setGroupId(ArrayList<String> groupId) {
        this.groupId = groupId;
    }

    public synchronized void addMessage(MessageChat message) {
        System.out.println(message.getUserSendMessage());
        messages.add(message);
    }

    public void setMessages(ArrayList<MessageChat> messages) {
        this.messages = messages;
    }

    public ArrayList<MessageChat> getMessages() {
        return messages;
    }
}
