package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 6666;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private Player player;

    public Client(Player player) {
        this.player = player;
        player.setClient(this);
    }

    public Client() {}

    public void setPlayer(Player player) { this.player = player; }

    public static void main(String[] args) {
        new Client().start();
    }

    public void start() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Listener().start();
            out.println("new client!");
        } catch (IOException e) {
            System.out.println("Socket encountered a problem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        out.println(command);
    }

    private class Listener extends Thread {
        @Override
        public void run() {
            try {
                String response;
                while ((response = in.readLine()) != null)
                    System.out.println("Server response: " + response);
            } catch (IOException e) {
                e.printStackTrace();
                this.interrupt();
            }
        }
    }
}
