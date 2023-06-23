package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;

public class BarrackMenu extends Application {
    public ScrollPane soldiersSelection;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/barrack_menu.fxml"));
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(){
        initSoldiers();
    }

    private void initSoldiers() {

    }

    public void quit(MouseEvent mouseEvent) throws Exception {
        Game.getMapMenu().start(StartGame.stage);
    }

    public void create(MouseEvent mouseEvent) {

    }
}
