package org.example.chat;

import org.example.User;

public class MessageChat {
    private User userSendMessage ;
    private String message;
    private String date;

    public MessageChat(User userSendMessage, String message, String date) {
        this.userSendMessage = userSendMessage;
        this.message = message;
        this.date = date;
    }
    public MessageChat(){

    }

    public User getUserSendMessage() {
        return userSendMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
