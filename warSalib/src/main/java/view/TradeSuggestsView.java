package view;

import control.TradeControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;
import model.government.Government;
import model.government.resource.Resource;
import model.user.User;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TradeSuggestsView extends Application {
    @javafx.fxml.FXML
    private VBox userTrades;
    @javafx.fxml.FXML
    private VBox gameTrades;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/tradeList.fxml"));
        userTrades = (VBox) pane.lookup("#userTrades");
        gameTrades = (VBox) pane.lookup("#gameTrades");
        //usertrades contains the output of tradehistory
        LinkedHashMap<User, HashMap<Resource, Integer>> history = Game.getTurnedUserForGame().getUserGovernment().getTradeHistory();
        LinkedHashMap<Government, HashMap<Resource, Integer>> trades = TradeMenu.tradeList;
        //render a row for each trade in history
        for (User user : history.keySet()) {
            for (Resource resource : history.get(user).keySet()) {
                userTrades.getChildren().add(new Label(user.getNickname() + " traded " + history.get(user).get(resource) + " " + resource.getName()));
            }
        }

        for (Government government : trades.keySet()) {
            for (Resource resource : trades.get(government).keySet()) {
                HBox temp = new HBox();
                temp.getChildren().add(new Label(government.getUser().getNickname() + " traded " + trades.get(government).get(resource) + " " + resource.getName()));
                Button tempButton = new Button("Accept");
                tempButton.setOnAction(event -> {
                    TradeControl.acceptTrade(government.getUser().getUsername(), resource.getName());
                    tempButton.setDisable(true);
                });
                gameTrades.getChildren().add(temp);
            }
        }

        stage.setScene(new Scene(pane));
        stage.show();
    }

}
