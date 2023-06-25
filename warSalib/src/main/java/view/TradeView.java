package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.user.User;

import java.awt.*;

public class TradeView extends Application {
    public static Stage stage;
    private User user;


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        TradeView.stage = stage;
        stage.setTitle("Trade Menu");
        stage.setHeight(100);
        stage.setWidth(300);
        stage.setResizable(false);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        //add 2 buttons for suggestTrade and viewOffers

        pane.setCenter(hbox);
        Button suggestTrade = new Button("Make a new Trade");
        suggestTrade.setOnMouseClicked(this::makeNewTrade);
        Button viewOffers = new Button("View Trade list");
        viewOffers.setOnMouseClicked(this::viewTradeList);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(suggestTrade, viewOffers);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void viewTradeList(MouseEvent mouseEvent) {
        try {
            new TradeSuggestsView().start(TradeView.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeNewTrade(MouseEvent mouseEvent) {
        try {
            stage.setHeight(500);
            stage.setWidth(750);
            stage.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 375);
            stage.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 250);
            new MakeTradeView().start(TradeView.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
