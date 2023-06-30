package org.example.chat;

import org.example.User;

public class PrivateChat extends Chat{
    @Override
    public synchronized void addPeople(User user) {
        if (super.groupId.size() == 1)
            super.addPeople(user);
    }
}
