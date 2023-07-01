package model;

import view.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 6666;
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private Player player;

        public ClientHandler(Socket socket) {
            try {
                this.clientSocket = socket;
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    processInput(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void processInput(String input) {
            if (input.equals("new room"))
                createNewRoom();
            else if (input.startsWith("join room")) {
                String[] splitInput = input.split(" ");
                if (splitInput.length == 3) {
                    String roomId = splitInput[2];
                    player.joinRoom(roomId);
                }
            } else if (input.startsWith("list room")) {
                for (Room room : rooms) {
                    player.getOut().println("room's id: " + room.getEntryId());
                }
            } else if (input.startsWith("leave room")) {
                boolean flag = false;
                for (Room room : rooms)
                    if (room.getPlayers().contains(player)) {
                        room.removePlayer(player);
                        flag = true;
                        player.getOut().println("you have successfully left the room " + room.getEntryId());
                    }
                if (!flag)
                    player.getOut().println("you didn't join any rooms");
            }
        }

        private Room createNewRoom() {
            Room room = new Room(true, new Game(), 4, player);
            rooms.add(room);
            out.println("Room created successfully!");
            player.joinRoom(room, room.getEntryId());
            return room;
        }

//        private void joinRoom(String roomId) {
//            for (Room room : rooms)
//                if (room.getEntryId().equals(roomId)) {
//                    room.addPlayer(player);
//                    out.println("Player joined the room successfully!");
//                    return;
//                }
//            out.println("Invalid room ID!");
//        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
