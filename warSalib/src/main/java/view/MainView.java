package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

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
    }

    public void logout(MouseEvent mouseEvent) {
    }

    public void goProfileMenu(MouseEvent mouseEvent) throws Exception {
        profileView profileView=new profileView();
        profileView.start(StartGame.stage);
    }
}
