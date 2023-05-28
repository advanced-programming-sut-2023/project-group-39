package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    public static Stage stage = StartGame.stage;
    public static Pane loginPane;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public static void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Login.fxml"));
        LoginView.loginPane = pane;
        LoginView.stage = stage;
        stage.setTitle("Login Menu");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void Login(MouseEvent mouseEvent) {

    }
}
