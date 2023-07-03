package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class FriendView extends Application {
    public TextField searchText;
    private HBox chooseHBox ;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/friend-view.fxml"));
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
    public void search(MouseEvent mouseEvent) throws IOException {
        if (searchText.getText() != null) {
            StartGame.getDataOutputStream().writeUTF("search");
            String result = searchText.getText();
            StartGame.getDataOutputStream().writeUTF(result);
            String answer = StartGame.getDataInputStream().readUTF();
            if (answer.equals("success")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("make chat successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("there is no user with this id");
                alert.showAndWait();
            }
        }
    }

    public void reject(MouseEvent mouseEvent) throws IOException {
        if (chooseHBox != null) {
            StartGame.getDataOutputStream().writeUTF("reject");
        }
    }

    public void accept(MouseEvent mouseEvent) throws IOException {
        if (chooseHBox != null) {
            StartGame.getDataOutputStream().writeUTF("accept");
        }
    }

}
