package view;

import control.LoginSignupControl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.LoginSignupMenu;
import view.StartGame;
import view.enums.messages.LoginMenuMessage;

import javax.swing.*;
import java.io.IOException;

public class Signupview extends Application {
    public static Stage signUpStage;
    public TextField signUpUsername;
    public PasswordField signUpPassword;
    public TextField signUpNickname;
    public TextField signUpEmail;
    public TextField signUpPasswordRecovery;

    public static Pane signUpPane;

    public static String username;
    public static String password;

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
        Image eyeIcon = new Image(Signupview.class.getResource("/images/eyeicon.png").toExternalForm());
        ImageView eyeView = new ImageView(eyeIcon);
        eyeView.setFitHeight(16);
        eyeView.setFitWidth(16);
        eyeView.setLayoutX(228);
        eyeView.setLayoutY(205);
        pane.getChildren().add(eyeView);
        Button button = new Button("Generate Random password");
        button.setLayoutX(280);
        button.setLayoutY(200);
        pane.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                randomPassword();
            }

        });
        TextField passwordText = new TextField();
        passwordText.setLayoutX(100);
        passwordText.setLayoutY(200);
        pane.getChildren().add(passwordText);
        passwordText.setVisible(false);
        eyeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!passwordText.isVisible()) {
                    PasswordField passwordField = (PasswordField) pane.getChildren().get(1);
                    passwordText.setText(passwordField.getText());
                    pane.getChildren().remove(eyeView);
                    passwordText.setVisible(true);
                    pane.getChildren().add(eyeView);
                    passwordField.setVisible(false);
                } else {
                    PasswordField passwordField = (PasswordField) pane.getChildren().get(1);
                    passwordField.setText(passwordText.getText());
                    pane.getChildren().remove(eyeView);
                    passwordField.setVisible(true);
                    pane.getChildren().add(eyeView);
                    passwordText.setVisible(false);
                }

            }
        });
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
        invalidUsername.setLayoutX(261);
        invalidUsername.setLayoutY(120);
        invalidUsername.setTextFill(Color.CRIMSON);
        Label sameUsername = new Label("This username already exists");
        sameUsername.setLayoutX(260);
        sameUsername.setLayoutY(120);
        sameUsername.setTextFill(Color.CRIMSON);
        Label isNotStrong = new Label();
        isNotStrong.setLayoutX(460);
        isNotStrong.setLayoutY(200);
        isNotStrong.setTextFill(Color.CRIMSON);
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
                username = newText;
                if (signUpPane.getChildren().contains(sameUsername)) {
                    signUpPane.getChildren().remove(sameUsername);

                } else if (signUpPane.getChildren().contains(invalidUsername)) {
                    signUpPane.getChildren().remove(invalidUsername);

                }
            }

        });
        signUpPassword.textProperty().addListener((observable, oldText, newText) -> {
            if (LoginSignupMenu.validPassword(newText).equals("upper case")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("lower case")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("number")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("length")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("special character")) {
                if(signUpPane.getChildren().contains(isNotStrong)){

                }
                else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("success")) {
                signUpPane.getChildren().remove(isNotStrong);

            }
        });
    }

    private void randomPassword() {
        String randomPassword = LoginSignupControl.findRandomPassword();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "your random password is:  " + randomPassword, "Random Password", dialogButton);
        if (dialogButton == JOptionPane.YES_OPTION) {
            password = randomPassword;
            if (dialogButton == JOptionPane.NO_OPTION) {
            }
        }


    }
}


