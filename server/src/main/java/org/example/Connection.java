package org.example;


import com.google.gson.reflect.TypeToken;
import org.example.chat.MessageChat;
import org.example.chat.PrivateChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import org.example.chat.PublicChat;

public class Connection extends Thread {
    private Socket socket;
    private boolean isTrue = true;
    private DataBaseUser dataBaseUser;
    private String input;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private static PrivateChat privateChat;

    private static User privateChatUser;

    private DataBaseUser guestDataBaseUser;

    private String username;

    private static PublicChat gamePublicChat=new PublicChat();

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            if (!socket.isConnected()) {
                System.out.println("client is disconnected from server : " + socket.getPort());
                return;
            }
            isTrue = true;
            try {
                input = dataInputStream.readUTF();
                System.out.println(input);
            } catch (IOException e) {
                System.out.println("client disconnected!" + socket.getInetAddress() + "   " + socket.getPort());
                if (Database.getLoggedInUser() != null)
                    Database.removeUsersInGame(Database.getLoggedInUser());
                Database.setLoggedInUser(null);
                this.stop();
            }
            if (input.equals("loginView")) {
                try {
                    loginCommands();
                } catch (IOException e) {
                    System.out.println("client disconnected!" + socket.getInetAddress() + "   " + socket.getPort());
                    if (Database.getLoggedInUser() != null)
                        Database.removeUsersInGame(Database.getLoggedInUser());
                    Database.setLoggedInUser(null);
                    this.stop();
                }
            } else if (input.equals("mainView")) {
                try {
                    mainViewCommands();
                } catch (IOException e) {
                    System.out.println("client disconnected!" + socket.getInetAddress() + "   " + socket.getPort());
                    if (Database.getLoggedInUser() != null)
                        Database.removeUsersInGame(Database.getLoggedInUser());
                    Database.setLoggedInUser(null);
                    this.stop();
                }
            } else if (input.equals("chatView")) {
                try {
                    System.out.println("go to chat");
                    chatCommands();
                } catch (IOException e) {
                    System.out.println("client disconnected!" + socket.getInetAddress() + "   " + socket.getPort());
                    if (Database.getLoggedInUser() != null)
                        Database.removeUsersInGame(Database.getLoggedInUser());
                        Database.setLoggedInUser(null);
                    this.stop();
                }
            } else if (input.equals("public chat")) {
                try {
                    System.out.println("go to public chat");
                    String json=null;
                    json=new String(Files.readAllBytes(Paths.get("publicChat.json")));
                    dataOutputStream.writeUTF("Data"+json);
                    publicChatCommand();
                } catch (IOException e) {
                    System.out.println("client disconnected!" + socket.getInetAddress() + "   " + socket.getPort());
                    if (Database.getLoggedInUser() != null)
                        Database.removeUsersInGame(Database.getLoggedInUser());
                    Database.setLoggedInUser(null);
                    this.stop();
                }
            } else if (input.equals("room")) {
                System.out.println("room");
                try {
                    roomCommands();
                } catch (IOException e) {
                    System.out.println("client disconnected!" + socket.getInetAddress() + "   " + socket.getPort());
                    if (Database.getLoggedInUser() != null)
                        Database.removeUsersInGame(Database.getLoggedInUser());
                    Database.setLoggedInUser(null);
                    this.stop();
                }
            }
        }
    }

    private void roomCommands() throws IOException{
        while (isTrue) {
            input = dataInputStream.readUTF();
            if (input.equals("back")) {
                isTrue = false;
            } else if (input.equals("send")) {
                System.out.println("send");
                dataOutputStream.writeUTF(dataBaseUser.getUsername());
                // todo : handle it
            }
        }
    }

    private void publicChatCommand() throws IOException{
        while (isTrue) {
            input = dataInputStream.readUTF();
            if (input.equals("back")) {
                System.out.println("back to main menu");
                isTrue = false;
            } else if (input.equals("send")) {
                System.out.println("send");
                dataOutputStream.writeUTF(dataBaseUser.getUsername());
                String[] inputs=dataInputStream.readUTF().split("\\+");
                System.out.println(inputs[1]);
                MessageChat messageChat=new MessageChat(inputs[1],inputs[0],inputs[2]);
                gamePublicChat.addMessage(messageChat);
                FileWriter fileWriter = new FileWriter("publicChat.json");
                fileWriter.write(new Gson().toJson(gamePublicChat.getMessages()));
                fileWriter.close();
                dataOutputStream.writeUTF("message send");
            }
        }
    }

    private void goRoomChat() throws IOException {
        String message = new Gson().toJson(dataBaseUser);
        dataOutputStream.writeUTF(message);
        while (isTrue) {
            System.out.println(input);
        }
    }

    private void chatCommands() throws IOException {
        dataOutputStream.writeUTF(dataBaseUser.getUsername() + ":");
        while (isTrue) {
            input = dataInputStream.readUTF();
            if (input.equals("newChat")) {
                System.out.println("search user");
                newChat();
            }
        else if (input.equals("send")) {
            System.out.println("send");
            dataOutputStream.writeUTF(dataBaseUser.getUsername());
        } else if (input.equals("back")){
            System.out.println("back");
            isTrue = false;
        }
            else if(input.startsWith("Message to Send")){
                if(privateChat==null){
                    dataOutputStream.writeUTF("first declare user to send");
                }
                else {
                    System.out.println("message send");
                    String message = input.substring(15, input.length());
                    String time= String.valueOf(java.time.LocalTime.now());
                    time=time.substring(0,8);
                    System.out.println(dataBaseUser.getUsername()+"111");
                    MessageChat messageChat=new MessageChat(dataBaseUser.getUsername(),message,time);
                    privateChat.addMessage(messageChat);
                    int flag=0;
                    for(PrivateChat privateChat1:guestDataBaseUser.getPrivateChats()) {
                        if (privateChat1.getGroupId().contains(dataBaseUser.getUsername()) && privateChat1.getGroupId().contains(username)) {
                            privateChat1.addMessage(messageChat);
                            System.out.println("1");
                            flag = 1;
                            break;
                        }
                    }
                    if(flag==0){
                        PrivateChat privateChat1=new PrivateChat();
                        privateChat1.getGroupId().add(username);
                        privateChat1.getGroupId().add(dataBaseUser.getUsername());
                        privateChat1.addMessage(messageChat);
                        guestDataBaseUser.addChatToPrivate(privateChat1);
                    }
                    FileWriter fileWriter = new FileWriter("database.json");
                    fileWriter.write(new Gson().toJson(Database.getDataBaseUsers()));
                    fileWriter.close();
                    dataOutputStream.writeUTF("message send");

                }
            }
        }
    }

    private void newChat() throws IOException {
        username = dataInputStream.readUTF();
            if (Database.getUserByName(username) != null) {
                for (DataBaseUser dataBaseUser1:Database.getDataBaseUsers()){
                    if(dataBaseUser1.getUsername().equals(username)){
                        guestDataBaseUser=dataBaseUser1;
                        break;
                    }
                }
                if(guestDataBaseUser==null){
                    guestDataBaseUser=new DataBaseUser(username,null,null,null);
                    Database.getDataBaseUsers().add(guestDataBaseUser);
                }
                System.out.println("make chat");
                String data=new Gson().toJson(dataBaseUser);
                dataOutputStream.writeUTF("Data"+data);
                int flag=0;
                for (PrivateChat privateChat1:dataBaseUser.getPrivateChats()){
                    if(privateChat1.getGroupId().contains(dataBaseUser.getUsername())&&privateChat1.getGroupId().contains(username)){
                        privateChat=privateChat1;
                        flag=1;
                        break;
                    }
                }
                if(flag==0) {
                    privateChat=new PrivateChat();
                    System.out.println("oh no!");
                    privateChat.getGroupId().clear();
                    privateChat.addPeople(dataBaseUser.getUsername());
                    privateChat.addPeople(username);
                    dataBaseUser.addChatToPrivate(privateChat);
                }
                FileWriter fileWriter = new FileWriter("database.json");
                fileWriter.write(new Gson().toJson(Database.getDataBaseUsers()));
                fileWriter.close();
            } else dataOutputStream.writeUTF("not found user");
        }

    private void mainViewCommands() throws IOException {
        while (isTrue) {
            if (!socket.isConnected()) {
                System.out.println("client is disconnected from server : " + socket.getPort());
                return;
            }
            input = dataInputStream.readUTF();
            if (input.equals("logout")) {
                Database.removeUsersInGame(Database.getLoggedInUser());
                Database.setLoggedInUser(null);
                System.out.println("logout");
                isTrue = false;
            } else if (input.equals("chat"))
                isTrue = false;
            else if (input.equals("go to chat"))
                isTrue = false;
        }
    }

    private void loginCommands() throws IOException {
        while (isTrue) {
            if (!socket.isConnected()) {
                System.out.println("client is disconnected from server : " + socket.getPort());
                Database.setLoggedInUser(null);
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
        if (!socket.isConnected()) {
            System.out.println("client is disconnected from server : " + socket.getPort());
            return;
        }
        input = dataInputStream.readUTF();
        String[] forgotData = input.split("\\+");
        dataOutputStream.writeUTF(Database.hasCorrectSecurityQuestion(forgotData[0], forgotData[1]));
    }

    private void goMainMenu() throws IOException {
        if (!socket.isConnected()) {
            System.out.println("client is disconnected from server : " + socket.getPort());
            Database.setLoggedInUser(null);
            return;
        }
        input = dataInputStream.readUTF();
        String loginData[] = input.split("\\+");
        if (loginData[0].equals("") || loginData[1].equals("")) {
            dataOutputStream.writeUTF("fillUP");
            System.out.println("user or password is not fill");
        } else if (!loginData[2].equals(loginData[3])) {
            dataOutputStream.writeUTF("wrong captcha");
            System.out.println("wrong captcha");
        } else {
            Message message = Database.hasUser(loginData[0], loginData[1]);
            if (message.equals(Message.SUCCESS)) {
                if (checkUser(Database.getUserByName(loginData[0]))) {
                    dataOutputStream.writeUTF("success");
                    System.out.println("success");
                    isTrue = false;
                    Database.setLoggedInUser(Database.getUserByName(loginData[0]));
                    String json=null;
                    json=new String(Files.readAllBytes(Paths.get("database.json")));
                    ArrayList<DataBaseUser> dataBaseUsers=new Gson().fromJson(json,new TypeToken<ArrayList<DataBaseUser>>(){}.getType());
                    int flag=0;
                    if(dataBaseUsers!=null) {
                        Database.setDataBaseUsers(dataBaseUsers);
                    for (DataBaseUser dataBaseUser1:Database.getDataBaseUsers()) {
                        if (dataBaseUser1.getUsername().equals(Database.getUserByName(loginData[0]).getUsername())) {
                            dataBaseUser = dataBaseUser1;
                            flag = 1;
                        }
                    }
                    }
                        if (flag == 0) {
                            dataBaseUser = new DataBaseUser(Database.getUserByName(loginData[0]).getUsername(), null, null, null);
                            Database.getDataBaseUsers().add(dataBaseUser);
                        }
                    //todo : handle it then
                } else {
                    dataOutputStream.writeUTF("user has already logged in");
                }
            } else if (message.equals(Message.WRONG_PASSWORD)) {
                dataOutputStream.writeUTF("wrong password");
                System.out.println("wrong password");
            } else {
                dataOutputStream.writeUTF("wrong user");
                System.out.println("wrong user");
            }
        }
    }

    private synchronized boolean checkUser(User userByName) {
        for (User user : Database.getUsersInGame()) {
            if (user.getUsername().equals(userByName.getUsername())) {
                return false;
            }

        }
        Database.addUsersInGame(userByName);
        return true;
    }

    private User getUserByUsername(String username) {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username))
                return user;

        }
        return null;
    }

}
