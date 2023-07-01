package view;

import javafx.application.Application;
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

    public void back(MouseEvent mouseEvent) throws Exception {
        StartGame.getDataOutputStream().writeUTF("back");
        StartGame.getDataOutputStream().writeUTF("mainView");
        MainView mainView = new MainView();
        mainView.start(StartGame.stage);
    }

    public void send(MouseEvent mouseEvent) throws IOException {
        if (!message.getText().equals("")) {
            StartGame.getDataOutputStream().writeUTF("send");
            String username = StartGame.getDataInputStream().readUTF();
            HBox hbox = new HBox();
            hbox.setSpacing(5.0);
            hbox.setOnMouseClicked(this :: choose);
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
        } else  {
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
            String []result = label.getText().split(":");
            String mes = result[0] + ": " + message.getText();
            label.setText(mes);
            Label time = (Label) chooseHBox.getChildren().get(2);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localTime = LocalDateTime.now();
            time.setText(dateTimeFormatter.format(localTime));
        }
    }
}
