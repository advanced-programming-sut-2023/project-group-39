package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Game;

public class RepairMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RepairMenu.class.getResource("/fxml/repairMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void quit(MouseEvent mouseEvent) throws Exception {
        Game.getMapMenu().start(StartGame.stage);
    }

    public void increaseHp(MouseEvent mouseEvent) {

    }
}
