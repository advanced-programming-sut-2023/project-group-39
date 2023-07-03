package view;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.DataBaseUser;
import model.Game;

import java.awt.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.passay.PasswordData.Origin.User;

public class ChatView extends Application {
    public Label username;
    public VBox chats;
    public VBox message;
    public TextField messageBeSent;
    public TextField search;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/chat.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws IOException {
        String[] input = StartGame.getDataInputStream().readUTF().split(":");
        username.setText(input[0]);
        //'Image image = new Image(get);
        // todo : handle avatar
    }

    public void newChat(MouseEvent mouseEvent) throws IOException {
        if (search.getText() != null) {
            StartGame.getDataOutputStream().writeUTF("newChat");
            StartGame.getDataOutputStream().writeUTF(search.getText());
            String result = StartGame.getDataInputStream().readUTF();
            if (result.equals("not found user")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("not found this user");
                alert.showAndWait();
            } else if (result.startsWith("Data")) {
                result = result.substring(4, result.length());
                makeDataBase(result, search.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("make chat");
                alert.showAndWait();
            }
        }
    }

    private void makeDataBase(String result, String username) {
        System.out.println(result);
        String privatesRegex = "privateChats\\\":(?<privateChats>.+)\\\"publicChats\\\"";
        String roomRegex = "roomChats\\\":(?<roomChats>.+)\\\"privateChats\\\"";
        String publicRegex = "publicChats\\\":(?<publicChats>.+)^";
        Matcher privateMatcher = Pattern.compile(privatesRegex).matcher(result);
        Matcher roomMatcher = Pattern.compile(roomRegex).matcher(result);
        Matcher publicMatcher = Pattern.compile(publicRegex).matcher(result);
        if (privateMatcher.find()) {
            //System.out.println(privateMatcher.group("privateChats"));
            String[] chats = privateMatcher.group("privateChats").split("groupId");
            for (int i = 0; i < chats.length; i++) {
                String usersRegex = "\\\":\\[\\\"(?<user1>[^\\\"]+)\\\"\\,\\\"(?<user2>[^\\\"]+)\\\"\\]";
                Matcher userMatcher = Pattern.compile(usersRegex).matcher(chats[i]);
                String messageRegex="\\\":\\\"(?<userSender>[^\\\"]+)\\\"\\,\\\"message\\\":\\\"(?<message>[^\\\"]+)\\\"\\,\\\"date\\\":\\\"(?<date>[^\\\"]+)\\\"";
                if (userMatcher.find()) {
                    System.out.println("hi");
                    String[] messages=chats[i].split("userSendMessage");
                    for (int j=1;j<messages.length;j++){
                        Matcher messageMatcher=Pattern.compile(messageRegex).matcher(messages[j]);
                        if(messageMatcher.find()) {
                            if((userMatcher.group("user1").equals(username)&&userMatcher.group("user2").equals(LoginView.loginUser))||(userMatcher.group("user2").equals(username)&&userMatcher.group("user1").equals(LoginView.loginUser))){
                                String username1 = messageMatcher.group("userSender");
                                String message = messageMatcher.group("message");
                                String date = messageMatcher.group("date");
                                System.out.println(username1 + "   " + message + "  " + date);
                            }
                        }
                    }
                }
            }
        }
        if (roomMatcher.find()) {

        }
        if (publicMatcher.find()) {

        }

    }

    public void send(MouseEvent mouseEvent) throws IOException {
        if (messageBeSent.getText() != null) {
            StartGame.getDataOutputStream().writeUTF("Message to Send" + messageBeSent.getText());
            String result = StartGame.getDataInputStream().readUTF();
            if (result.equals("first declare user to send")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("first declare user to send");
                alert.showAndWait();
            } else if (result.equals("message send")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("message send successfully");
                alert.showAndWait();

            }
        }
    }
}