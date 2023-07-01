package view;

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

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatView extends Application {
    public Label username;
    public VBox chats;
    public VBox message;
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
    public void initialize () throws IOException {
        String []input = StartGame.getDataInputStream().readUTF().split(":");
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("not found this user");
                alert.showAndWait();
            } else if (result.equals("make chat")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("make chat");
                alert.showAndWait();
            } else if (result.equals("you already have this chat")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("you already have this chat");
                alert.showAndWait();
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
            message.getChildren().add(hbox);
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

    public void edit(MouseEvent mouseEvent) {
        if (chooseHBox != null) {
            Label label = (Label) chooseHBox.getChildren().get(0);
            String []result = label.getText().split(":");
            String mes = result[0] + ": " + messageBeSent.getText();
            label.setText(mes);
            Label time = (Label) chooseHBox.getChildren().get(2);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localTime = LocalDateTime.now();
            time.setText(dateTimeFormatter.format(localTime));
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

    public void back(MouseEvent mouseEvent) throws Exception {
        StartGame.getDataOutputStream().writeUTF("back");
        StartGame.getDataOutputStream().writeUTF("mainView");
        MainView mainView = new MainView();
        mainView.start(StartGame.stage);
    }
}

