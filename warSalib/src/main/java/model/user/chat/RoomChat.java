package model.user.chat;

import model.user.User;

import java.util.ArrayList;

public class RoomChat {
    ArrayList<User> groupId = new ArrayList<>();
    ArrayList <MessageChat> messages = new ArrayList<>();

    private String groupName;

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

    public ArrayList<MessageChat> getMessages() {
        return messages;
    }

    public String getGroupName() {
        return groupName;
    }
}
