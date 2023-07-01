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
            hbox.setOnMouseClicked(this :: delete);
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

    private void delete(MouseEvent mouseEvent) {
    }
}
