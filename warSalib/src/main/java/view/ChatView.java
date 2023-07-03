package view;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatView extends Application {
    public Label username;
    public VBox Chats;
    public  VBox MessageVBox;
    HBox chooseHBox;
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
                    String[] messages=chats[i].split("userSendMessage");
                    for (int j=1;j<messages.length;j++){
                        Matcher messageMatcher=Pattern.compile(messageRegex).matcher(messages[j]);
                        if(messageMatcher.find()) {
                            if((userMatcher.group("user1").equals(username)&&userMatcher.group("user2").equals(LoginView.loginUser))||(userMatcher.group("user2").equals(username)&&userMatcher.group("user1").equals(LoginView.loginUser))){
                                String username1 = messageMatcher.group("userSender");
                                String message = messageMatcher.group("message");
                                String date = messageMatcher.group("date");
                                String sending=username1 + " :  " + message;
                                Label messageSending=new Label(sending);
                                Label Date=new Label(date);
                                Image image = new Image(ChatView.class.getResource("/images/tick.png").toExternalForm());
                                ImageView imageView = new ImageView(image);
                                imageView.setFitWidth(10.0);
                                imageView.setFitHeight(10.0);
                                HBox toSend=new HBox();
                                toSend.getChildren().addAll(messageSending,imageView,Date);
                                MessageVBox.getChildren().add(toSend);

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

    public void sending() throws IOException {
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
//                String username=LoginView.loginUser;
//                String message=messageBeSent.getText();
//                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//                LocalDateTime localTime = LocalDateTime.now();
//                Label time = new Label(dateTimeFormatter.format(localTime));
//                Label messageToSend=new Label(username+" :  "+message);
//                Image image = new Image(ChatView.class.getResource("/images/tick.png").toExternalForm());
//                ImageView imageView = new ImageView(image);
//                imageView.setFitWidth(10.0);
//                imageView.setFitHeight(10.0);
//                HBox hBox=new HBox();
//                hBox.getChildren().addAll(messageToSend,imageView,time);
//                MessageVBox.getChildren().add(hBox);


            }
        }
 }

    public void send(MouseEvent mouseEvent) throws IOException {
        if (!messageBeSent.getText().equals("")) {
            StartGame.getDataOutputStream().writeUTF("send");
            String username = StartGame.getDataInputStream().readUTF();
            HBox hbox = new HBox();
            hbox.setSpacing(5.0);
            hbox.setOnMouseClicked(this :: choose);
            Label label = new Label(username + " : " + messageBeSent.getText());
            Image image = new Image(ChatView.class.getResource("/images/tick.png").toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(10.0);
            imageView.setFitHeight(10.0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localTime = LocalDateTime.now();
            Label time = new Label(dateTimeFormatter.format(localTime));
            hbox.getChildren().add(label);
            hbox.getChildren().add(imageView);
            hbox.getChildren().add(time);
            MessageVBox.getChildren().add(hbox);
            sending();
            messageBeSent.setText("");
        }
    }

    private void choose(MouseEvent mouseEvent) {
        HBox hBox = (HBox) mouseEvent.getSource();
        if (chooseHBox == null) {
            hBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            chooseHBox = hBox;
        } else if (chooseHBox == hBox) {
            hBox.setStyle("-fx-border-color: transparent; -fx-border-width: 2px;");
            chooseHBox = null;
        } else  {
            chooseHBox.setStyle("-fx-border-color: transparent; -fx-border-width: 2px;");
            hBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            chooseHBox = hBox;
        }
    }

    public void edit(MouseEvent mouseEvent) throws IOException {
        if (chooseHBox != null) {
            Label label = (Label) chooseHBox.getChildren().get(0);
            String previousMessage=label.getText();
            String []result = label.getText().split(":");
            String mes = result[0] + ": " + messageBeSent.getText();
            label.setText(mes);
            StartGame.getDataOutputStream().writeUTF("send");
            System.out.println(previousMessage+"xxx");
            sending2(previousMessage,mes);
            Label time = (Label) chooseHBox.getChildren().get(2);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localTime = LocalDateTime.now();
            time.setText(dateTimeFormatter.format(localTime));
        }
    }

    private void sending2(String previousMessage, String mes) throws IOException {
        StartGame.getDataOutputStream().writeUTF("Previous:" + previousMessage + ":" + mes);
        String result = StartGame.getDataInputStream().readUTF();
        if (result.equals("message edited")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("message edited successfully");
            alert.showAndWait();
        }
    }

    public void delete(MouseEvent mouseEvent) throws IOException {
        if (chooseHBox != null) {
            Label label = (Label) chooseHBox.getChildren().get(0);
            String deleteMessage=label.getText();
            delteing(deleteMessage);
            label.setText("");
            Label time = (Label) chooseHBox.getChildren().get(2);
            time.setText("");
            ImageView imageView = (ImageView) chooseHBox.getChildren().get(1);
            imageView.setImage(null);
        }
    }

    private void delteing(String deleteMessage) throws IOException {
        StartGame.getDataOutputStream().writeUTF("Delete:" + deleteMessage + ":");
        String result = StartGame.getDataInputStream().readUTF();
        if (result.equals("message deleted")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("message edited successfully");
            alert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        StartGame.getDataOutputStream().writeUTF("back");
        StartGame.getDataOutputStream().writeUTF("mainView");
        MainView mainView = new MainView();
        mainView.start(StartGame.stage);
    }
}

