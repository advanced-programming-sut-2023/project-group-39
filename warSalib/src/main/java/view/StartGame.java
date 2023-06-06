package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import model.Game;
import model.map.GameMap;

public class StartGame extends Application {
    public static Stage stage;

    public static Pane startPane;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/StartGame.fxml"));
        StartGame.startPane=pane;
        Scene scene = new Scene(pane,840,720);
        StartGame.stage=stage;
        stage.setTitle("Welcome to the Game");
        Image image=new Image(StartGame.class.getResource("/images/thumb-1920-680255.jpg").toExternalForm());
        BackgroundFill backgroundFill=new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        pane.setBackground(background);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LoginSignupControl.readUsersData();
        launch();
    }

    public void goLoginView(MouseEvent mouseEvent) throws Exception {
        LoginView loginView=new LoginView();
        loginView.start(stage);
    }

    public void goSignupView(MouseEvent mouseEvent) throws IOException {
        signupview signupview=new signupview();
        signupview.start(stage);
    }

    public static void setStartPane(Pane startPane) {
        StartGame.startPane = startPane;
    }

}
