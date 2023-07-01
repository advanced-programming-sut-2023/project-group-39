package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PublicChat extends Application {
    public TextField message;

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
        }
    }
}
