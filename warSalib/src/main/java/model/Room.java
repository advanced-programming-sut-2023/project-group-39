package model;

import model.user.User;
import view.Lobby;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Room {
    private boolean isPublic;
    private Game game;
    private int capacity;
    private ArrayList<Player> players;
    private ArrayList<Socket> sockets;
    private Player admin;
    private String entryId;

    public ArrayList<Player> getPlayers() { return players; }

    public Room(boolean isPublic, Game game, int capacity, Player admin) {
        this.isPublic = isPublic;
        this.game = game;
        this.capacity = capacity;
        this.players = new ArrayList<>(capacity);
        this.admin = admin;
        this.entryId = generateRoomId();
        new StatusChecker().start();
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getEntryId() {
        return entryId;
    }

    public String generateRoomId() {
        String id = null;

        outer:
        while (true) {
            id = "R" + new Random().nextInt(11111111, 99999999) + "m";
            for (Room room : Server.rooms)
                if (room.entryId.equals(id))
                    continue outer;

            break;
        }
        return id;
    }


    public void addPlayer(Player player) {
        players.add(player);
        player.getOut().println("New player has been added");
        if (players.size() == capacity) {
            // TODO: 6/29/2023 start the game
        }
    }

    public void removePlayer(Player player) {
        int index = players.indexOf(player);
        if (players.size() == 1) {
            // TODO: Perform any necessary actions when the room is closed
            return;
        }
        if (player.equals(admin)) {
            admin = players.get(index + 1);
            // TODO: Perform any necessary actions after changing the admin
        }
        this.players.remove(player);
    }

    private void closeRoom(Player admin) {
        Server.rooms.remove(this);
        admin.getOut().println("The room has been closed by the admin.");
    }

    private class StatusChecker extends Thread {
        private static final long INACTIVITY_TIMEOUT = 5 * 60 * 1000;

        @Override
        public void run() {
            try {
                long startTime = System.currentTimeMillis();
                while (true) {
                    long currentDuration = System.currentTimeMillis() - startTime;
                    if (currentDuration >= INACTIVITY_TIMEOUT) {
                        closeRoom();
                        break;
                    } else if (!players.isEmpty()) {
                        startTime = System.currentTimeMillis();
                    }

                    System.out.println("No players have been added for " + currentDuration/(1000 * 60) + " minutes");
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                System.out.println("StatusChecker encountered a problem: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private void closeRoom() {
            // TODO: Perform any necessary cleanup or actions when closing the room
            Server.rooms.remove(Room.this);
            for (Player player : players)
                player.getOut().println("The room has been closed due to inactivity.");
        }
    }
}