package model.chat;

import model.user.User;

public class PrivateChat extends Chat{
    @Override
    public synchronized void addPeople(User user) {
        if (super.groupId.size() == 0||super.groupId.size()==1)
            super.addPeople(user);
    }
}
