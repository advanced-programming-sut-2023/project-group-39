package org.example.chat;

import org.example.User;

public class MessageChat {
    private User userSendMessage ;
    private String message;
    private int date;

    public MessageChat(User userSendMessage, String message, int date) {
        this.userSendMessage = userSendMessage;
        this.message = message;
        this.date = date;
    }

    public User getUserSendMessage() {
        return userSendMessage;
    }

    public String getMessage() {
        return message;
    }

    public int getDate() {
        return date;
    }
}
