package org.example;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    private Socket socket;
    private String input;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        while (true) {

            try {
                input = dataInputStream.readUTF();
                System.out.println(input);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (input.equals("loginView")){
                try {
                    loginCommands();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void loginCommands() throws IOException {
        while (true){
            input = dataInputStream.readUTF();
        if (input.equals("goMainMenu")) {
            goMainMenu();
        } else if (true) {

        }
    }
    }
    private void goMainMenu() throws IOException {
            input = dataInputStream.readUTF();
            String loginData[] = input.split("\\+");
            if (loginData[0].equals("") || loginData[1].equals("")) {
                dataOutputStream.writeUTF("fillUP");
                System.out.println("user or password is not fill");
            } else if (!loginData[2].equals(loginData[3])){
                dataOutputStream.writeUTF("wrong captcha");
                System.out.println("wrong captcha");
            }

    }
}
