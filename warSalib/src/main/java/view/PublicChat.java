package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicChat extends Application {
    public TextField message;
    public VBox messages;
    HBox chooseHBox;


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/public-chat.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() throws IOException {
        String data = StartGame.getDataInputStream().readUTF();
        data = data.substring(4, data.length());
        String messageRegex = "\\\"userSendMessage\\\":\\\"(?<User>[^\\\"]+)\\\"\\,\\\"message\\\":\\\"(?<message>[^\\\"]+)\\\"\\,\\\"date\\\":\\\"(?<date>[^\\\"]+)\\\"";
        String[] messages1 = data.split("\\]");
        for (int i = 0; i < messages1.length; i++) {
            Matcher messageMatcher = Pattern.compile(messageRegex).matcher(messages1[i]);
            if (messageMatcher.find()) {
                String userSender=messageMatcher.group("User");
                Label userLabel=new Label(userSender+" :  ");
                String sending = messageMatcher.group("message");
                Label messageSending = new Label(sending+"  ");
                Label Date = new Label(messageMatcher.group("date"));
                Image image = new Image(ChatView.class.getResource("/images/tick.png").toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(10.0);
                imageView.setFitHeight(10.0);
                HBox toSend = new HBox();
                toSend.getChildren().addAll(userLabel,messageSending, imageView, Date);
                messages.getChildren().add(toSend);


            }
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        StartGame.getDataOutputStream().writeUTF("back");
        StartGame.getDataOutputStream().writeUTF("mainView");
        MainView mainView = new MainView();
        mainView.start(StartGame.stage);
    }

    public void send(MouseEvent mouseEvent) throws IOException {
        if (!message.getText().equals("")) {
            StartGame.getDataOutputStream().writeUTF("send");
            String username = LoginView.loginUser;
            HBox hbox = new HBox();
            hbox.setSpacing(5.0);
            hbox.setOnMouseClicked(this::choose);
            Label label = new Label(username + " : " + message.getText());
            Image image = new Image(PublicChat.class.getResource("/images/tick.png").toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(10.0);
            imageView.setFitHeight(10.0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localTime = LocalDateTime.now();
            Label time = new Label(dateTimeFormatter.format(localTime));
            hbox.getChildren().add(label);
            hbox.getChildren().add(imageView);
            hbox.getChildren().add(time);
            messages.getChildren().add(hbox);
            StartGame.getDataOutputStream().writeUTF(message.getText() + "+" + username + "+" + time.getText());
            message.setText("");
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
        } else {
            chooseHBox.setStyle("-fx-border-color: transparent; -fx-border-width: 2px;");
            hBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            chooseHBox = hBox;
        }
    }

    public void delete(MouseEvent mouseEvent) {
        if (chooseHBox != null) {
            Label label = (Label) chooseHBox.getChildren().get(0);
            label.setText("");
            Label time = (Label) chooseHBox.getChildren().get(2);
            time.setText("");
            ImageView imageView = (ImageView) chooseHBox.getChildren().get(1);
            imageView.setImage(null);
        }
    }

    public void edit(MouseEvent mouseEvent) {
        if (chooseHBox != null) {
            Label label = (Label) chooseHBox.getChildren().get(0);
            String[] result = label.getText().split(":");
            String mes = result[0] + ": " + message.getText();
            label.setText(mes);
            Label time = (Label) chooseHBox.getChildren().get(2);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localTime = LocalDateTime.now();
            time.setText(dateTimeFormatter.format(localTime));
        }
    }
}
