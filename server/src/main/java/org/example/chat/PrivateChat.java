package org.example.chat;

import org.example.User;

public class PrivateChat extends Chat{
    @Override
    public synchronized void addPeople(String username) {
        if (super.groupId.size() == 0||super.groupId.size()==1)
            super.addPeople(username);
    }
}
