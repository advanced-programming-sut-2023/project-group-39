package model;

import model.user.User;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private User user;
    private Socket socket;
    private Client client;


    public Player(User user) {
        this.user = user;
    }

    public void setClient(Client client) { this.client = client; }

    public void joinRoom(Room room, String id) {
        if (!room.isPublic() && !room.getEntryId().equals(id))
            return;

        room.addPlayer(this);
    }
}
