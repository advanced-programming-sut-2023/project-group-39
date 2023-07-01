package model;

import model.user.User;
import view.Room;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private User user;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;


    public Player(User user, Socket socket) {
        try {
            this.user = user;
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player(User user) {
        try {
            this.user = user;
            this.socket = new Socket("127.0.0.1", 6666);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getIn() { return in; }

    public PrintWriter getOut() { return out; }

    public void joinRoom(Room room, String id) {
        if (!room.isPublic() && !room.getEntryId().equals(id))
            return;

        room.addPlayer(this);
    }

    public void joinRoom(String id) {
        boolean flag = false;
        for (Room room : Server.rooms)
            if (room.getEntryId().equals(id)) {
                room.addPlayer(this);
                flag = true;
            }

        if (!flag) {
            System.out.println("Room does not exist");
            out.println("Room does not exist");
        }
    }
}
