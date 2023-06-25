package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.user.User;

public class TradeView extends Application {
    public static Stage stage;
    private User user;


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/TradeView.fxml"));
        TradeView.stage = stage;
        stage.setTitle("Trade Menu");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void viewTradeList(MouseEvent mouseEvent) throws Exception {
        new TradeSuggestsView().start(TradeView.stage);
    }

    public void makeNewTrade(MouseEvent mouseEvent) throws Exception {
        new MakeTradeView().start(TradeView.stage);
    }
}
