package view;

import com.google.gson.Gson;
import control.LoginSignupControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.awt.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;
import model.user.User;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.ScrollPane;

public class scoreBoardView  extends Application {

    public static GridPane scoreBoardPane;

    public static Stage scoreBoardStage;
    @Override
    public void start(Stage stage) throws Exception {
            GridPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/ScoreBoard.fxml"));
            scoreBoardPane = pane;
          // javafx.scene.control.ScrollPane scrollPane=new ScrollPane(pane);
            Scene scene = new Scene(pane,840,720);
            scoreBoardStage = stage;
            stage.setTitle("Score Board");
            stage.setScene(scene);
            stage.show();
       LoginSignupControl.sort(Game.getPlayers());
       String ranking= String.valueOf(Game.getPlayers().indexOf(Game.getCurrentUser())+1);
       Text rank=new Text(ranking);
       String scoring= String.valueOf(Game.getCurrentUser().getScore());
       Text score=new Text(scoring);
       rank.setLayoutX(60);
       rank.setLayoutY(30);
       score.setLayoutX(60);
       score.setLayoutY(50);
       scoreBoardPane.getChildren().addAll(rank,score);
            showRankings(Game.getPlayers());
    }

    private void showRankings(ArrayList<User> sortedUsers) {
        VBox ranking=new VBox();
        ranking.setAlignment(Pos.CENTER);
        for (int i=0;i<Game.getPlayers().size();i++){
            String rank= String.valueOf(i+1);
            Text username=new Text(Game.getPlayers().get(i).getUsername());
            Text userRank=new Text(rank);
            String scoring= String.valueOf(Game.getPlayers().get(i).getScore());
            Text userScore=new Text(scoring);
            HBox hBox=new HBox((Node) userRank, (Node) username, (Node) userScore);
            ranking.getChildren().add(hBox);
        }
        ScrollPane scrollPane=new ScrollPane();
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(view.adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //   scoreBoardPane.getChildren().add(scrollPane);
    }
}
