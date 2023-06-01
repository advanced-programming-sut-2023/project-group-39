package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    public static Stage loginStage;

    public static void start(Stage stage) throws IOException {
        loginStage=stage;
        Pane pane=FXMLLoader.load(StartGame.class.getResource("/fxml/Login.fxml"));
        Scene scene=new Scene(pane);
        loginStage.setScene(scene);
        Label userLabel=new Label("Username");
        Label passwordLabel=new Label("Password");
        userLabel.setLayoutX(180);
        userLabel.setLayoutY(120);
        userLabel.setFont(Font.font(20));
        passwordLabel.setLayoutY(200);
        passwordLabel.setLayoutX(180);
        pane.getChildren().addAll(userLabel,passwordLabel);



    }
}
