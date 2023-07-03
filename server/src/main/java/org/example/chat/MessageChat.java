package org.example.chat;

import org.example.User;

public class MessageChat {
    private String userSendMessage ;
    private String message;
    private String date;

    public MessageChat(String userSendMessage, String message, String date) {
        this.userSendMessage = userSendMessage;
        this.message = message;
        this.date = date;
    }
    public MessageChat(){

    }

    public String getUserSendMessage() {
        return userSendMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
