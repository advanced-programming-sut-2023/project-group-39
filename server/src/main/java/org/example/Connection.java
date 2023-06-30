package org.example;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    private Socket socket;
    private boolean isTrue = true;
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
            if (!socket.isConnected()){
                System.out.println("client is disconnected from server : " + socket.getPort());
                return;
            }
            isTrue = true;
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
            } else if (input.equals("mainView")) {
                try {
                    mainViewCommands();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void mainViewCommands() throws IOException {
        while (isTrue) {
            if (!socket.isConnected()){
                System.out.println("client is disconnected from server : " + socket.getPort());
                return;
            }
            input = dataInputStream.readUTF();
            if (input.equals("logout")) {
                Database.setLoggedInUser(null);
                System.out.println("logout");
            }
        }
    }

    private void loginCommands() throws IOException {
        while (isTrue){
            if (!socket.isConnected()){
                System.out.println("client is disconnected from server : " + socket.getPort());
                return;
            }
            input = dataInputStream.readUTF();
        if (input.equals("goMainMenu")) {
            goMainMenu();
        } else if (input.equals("forgotPassword")) {
            forgotPassword();
        }
    }
    }

    private void forgotPassword() throws IOException {
        if (!socket.isConnected()){
            System.out.println("client is disconnected from server : " + socket.getPort());
            return;
        }
        input = dataInputStream.readUTF();
        String [] forgotData = input.split("\\+");
        dataOutputStream.writeUTF(Database.hasCorrectSecurityQuestion(forgotData[0], forgotData[1]));
    }

    private void goMainMenu() throws IOException {
        if (!socket.isConnected()){
            System.out.println("client is disconnected from server : " + socket.getPort());
            return;
        }
            input = dataInputStream.readUTF();
            String loginData[] = input.split("\\+");
            if (loginData[0].equals("") || loginData[1].equals("")) {
                dataOutputStream.writeUTF("fillUP");
                System.out.println("user or password is not fill");
            } else if (!loginData[2].equals(loginData[3])){
                dataOutputStream.writeUTF("wrong captcha");
                System.out.println("wrong captcha");
            } else {
                Message message= Database.hasUser(loginData[0], loginData[1]);
                if (message.equals(Message.SUCCESS)) {
                    dataOutputStream.writeUTF("success");
                    System.out.println("success");
                    isTrue = false;
                    Database.setLoggedInUser(Database.getUserByName(loginData[0],loginData[1]));
                } else if (message.equals(Message.WRONG_PASSWORD)) {
                    dataOutputStream.writeUTF("wrong password");
                    System.out.println("wrong password");
                } else {
                    dataOutputStream.writeUTF("wrong user");
                    System.out.println("wrong user");
                }
            }

    }
}
