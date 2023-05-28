package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupView {
    public static Stage stage;
    public static Pane signupPane;
    @FXML
    private TextField slogan;
    @FXML
    private PasswordField confirm;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField nickname;
    @FXML
    private TextField email;
    @FXML
    private Spinner spinner;

    public static void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Signup.fxml"));
        SignupView.signupPane = pane;
        SignupView.stage = stage;
        stage.setTitle("Sing up Menu");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void getSpinnerValue(MouseEvent mouseEvent) {

    }

    public void signUp(MouseEvent mouseEvent) {
        String securityAnswer = (String) spinner.getValue();
        LoginSignupMenu.createUser(this.username.getText(), this.password.getText(), this.confirm.getText(),
                this. nickname.getText(), this.slogan.getText(), securityAnswer);
    }
}
