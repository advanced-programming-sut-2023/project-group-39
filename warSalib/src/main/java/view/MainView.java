package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;

import java.util.ArrayList;

public class MainView extends Application {
    public Pane mainPane;

    public Stage mainStage;


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/MainMenu.fxml"));
        mainPane = pane;
        Scene scene = new Scene(pane, 840, 720);
        mainStage = stage;
        stage.setTitle("Main Menu");
        Image image = new Image(StartGame.class.getResource("/images/09.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void StartNewGame(MouseEvent mouseEvent) {
        ArrayList<Label> labels=new ArrayList<>();
        ArrayList<TextField> players=new ArrayList<>();
        for (int i=2;i<9;i++){
            Label label=new Label("Player  "+ i);
            label.setLayoutX(120);
            label.setLayoutY(20+40*i);
            label.setTextFill(Color.WHITE);
            labels.add(label);
            TextField textField=new TextField();
            textField.setPromptText("Player    "+i);
            textField.setFont(Font.font(String.valueOf(Color.WHITE)));
            textField.setLayoutX(200);
            textField.setLayoutY(20+40*i);
            players.add(textField);
            mainPane.getChildren().addAll(label,textField);

        }
        Button start=new Button("Start Game");
        start.setLayoutX(120);
        start.setLayoutY(500);
        mainPane.getChildren().add(start);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                checkPlayers(players);
                if(Game.getPlayersInGame().size()!=0){
                    Game.getPlayersInGame().add(Game.getCurrentUser());
                    Game.setGameStarter(Game.getCurrentUser());
                }
            }
        });
    }

    private void checkPlayers(ArrayList<TextField> players) {
        if(players.size()==0){
            Label label=new Label("Players are empty");
            label.setLayoutX(120);
            label.setLayoutY(700);
            mainPane.getChildren().add(label);
        }
       for (int i=2;i<9;i++){
                if(LoginSignupControl.findUser(players.get(i-2).getText())){
                    Game.getPlayersInGame().add(LoginSignupControl.getUserByName(players.get(i-2).getText()));
                }
                else {
                    Label label=new Label("player doesnt find");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(360);
                    label.setLayoutY(20+40*i);
                    mainPane.getChildren().add(label);

                }

        }
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Game.setCurrentUser(null);
        StartGame startGame=new StartGame();
        startGame.start(StartGame.stage);
    }

    public void goProfileMenu(MouseEvent mouseEvent) throws Exception {
        profileView profileView=new profileView();
        profileView.start(StartGame.stage);
    }
}