package main.java.model.government.request;

import main.java.model.user.User;

public class Request {
    private String request;
    private User senderUser;
    private User takerUser;

    public Request(String request, User senderUser, User takerUser) {
        this.request = request;
        this.senderUser = senderUser;
        this.takerUser = takerUser;
    }

    public String getRequest() {
        return request;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public User getTakerUser() {
        return takerUser;
    }
}
