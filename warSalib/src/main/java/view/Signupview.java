package view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import view.LoginSignupMenu;
import view.StartGame;
import view.enums.messages.LoginMenuMessage;

import java.io.IOException;

public class Signupview extends Application {
    public static Stage signUpStage;
    public TextField signUpUsername;
    public PasswordField signUpPassword;
    public TextField signUpNickname;
    public TextField signUpEmail;
    public TextField signUpPasswordRecovery;

    public static Pane signUpPane;

    public void start(Stage stage) throws IOException {
        signUpStage = stage;
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/SignUp.fxml"));
        signUpPane = pane;
        Scene scene = new Scene(pane);
        signUpStage.setScene(scene);
        stage.setTitle("Sign up");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        Label nicknameLabel = new Label("nickname");
        Label EmailLabel = new Label("Email");
        Label passRecovery = new Label("Password recovery");
        usernameLabel.setLayoutX(20);
        usernameLabel.setLayoutY(125);
        usernameLabel.setTextFill(Color.WHITE);
        passwordLabel.setLayoutX(20);
        passwordLabel.setLayoutY(190);
        passwordLabel.setTextFill(Color.WHITE);
        nicknameLabel.setLayoutX(20);
        nicknameLabel.setLayoutY(270);
        nicknameLabel.setTextFill(Color.WHITE);
        EmailLabel.setLayoutX(20);
        EmailLabel.setLayoutY(350);
        EmailLabel.setTextFill(Color.WHITE);
        passRecovery.setLayoutX(5);
        passRecovery.setLayoutY(415);
        passRecovery.setTextFill(Color.WHITE);
        pane.getChildren().addAll(usernameLabel, passwordLabel, nicknameLabel, EmailLabel, passRecovery);

        Image image = new Image(StartGame.class.getResource("/images/26.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
    }

    @FXML
    public void initialize() {
        Label invalidUsername = new Label("Username format is invalid");
        invalidUsername.setLayoutX(260);
        invalidUsername.setLayoutY(120);
        invalidUsername.setTextFill(Color.CRIMSON);
        Label sameUsername = new Label("This username already exists");
        sameUsername.setLayoutX(260);
        sameUsername.setLayoutY(120);
        sameUsername.setTextFill(Color.CRIMSON);
        signUpUsername.textProperty().addListener((observable, oldText, newText) -> {
            if (LoginSignupMenu.checkUsername(newText).equals("invalid format")) {
                if (signUpPane.getChildren().contains(invalidUsername)) {

                } else {
                    signUpPane.getChildren().add(invalidUsername);
                }
            } else if (LoginSignupMenu.checkUsername(newText).equals("sameUsername")) {
                if (signUpPane.getChildren().contains(sameUsername)) {

                } else {
                    signUpPane.getChildren().add(sameUsername);
                }
            } else if (LoginSignupMenu.checkUsername(newText).equals("success")) {
                if (signUpPane.getChildren().contains(sameUsername)) {
                    signUpPane.getChildren().remove(sameUsername);

                } else if (signUpPane.getChildren().contains(invalidUsername)) {
                    signUpPane.getChildren().remove(invalidUsername);

                }
            }

        });
    }
}


