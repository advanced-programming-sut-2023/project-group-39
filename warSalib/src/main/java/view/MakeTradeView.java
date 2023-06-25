package view;

import control.TradeControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;

public class MakeTradeView extends Application {
    private int resourceMax = 0;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private String selectedResource;

    @javafx.fxml.FXML
    private TextField priceField;
    @javafx.fxml.FXML
    private Button decreaseButton;
    @javafx.fxml.FXML
    private Label resourceAmountLabel;
    @javafx.fxml.FXML
    private Button increaseButton;
    @javafx.fxml.FXML
    private TextArea messageTextArea;
    @javafx.fxml.FXML
    private RadioButton donateRadio;
    @javafx.fxml.FXML
    private RadioButton requestRadio;
    @javafx.fxml.FXML
    private Button sendButton;
    @javafx.fxml.FXML
    private GridPane resourceGrid;
    @javafx.fxml.FXML
    private ChoiceBox userSelectDropdown;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/makeTrade.fxml"));
        decreaseButton = (Button) pane.lookup("#decreaseButton");
        resourceAmountLabel = (Label) pane.lookup("#resourceAmountLabel");
        increaseButton = (Button) pane.lookup("#increaseButton");
        messageTextArea = (TextArea) pane.lookup("#messageTextArea");
        donateRadio = (RadioButton) pane.lookup("#donateRadio");
        requestRadio = (RadioButton) pane.lookup("#requestRadio");
        sendButton = (Button) pane.lookup("#sendButton");
        resourceGrid = (GridPane) pane.lookup("#resourceGrid");
        priceField = (TextField) pane.lookup("#priceField");
        userSelectDropdown = (ChoiceBox) pane.lookup("#userSelectDropdown");
        userSelectDropdown.setSelectionModel(new SingleSelectionModel<String>() {
            @Override
            protected String getModelItem(int i) {
                return Game.getPlayers().get(i).getNickname();
            }

            @Override
            protected int getItemCount() {
                return Game.getPlayers().size();
            }
        });
        userSelectDropdown.getSelectionModel().selectFirst();
        decreaseButton.setOnAction(event -> resourceAmountLabel.setText(String.valueOf(Math.max(Integer.parseInt(resourceAmountLabel.getText()) - 1, 0))));
        increaseButton.setOnAction(event -> resourceAmountLabel.setText(String.valueOf(Math.min(Integer.parseInt(resourceAmountLabel.getText()) + 1, resourceMax))));

        donateRadio.setToggleGroup(toggleGroup);
        requestRadio.setToggleGroup(toggleGroup);

        String[] resources = {"wood", "archery", "armour", "pitch", "bread", "coin", "flour", "food", "hammer", "hop", "oil", "spear", "steel", "stone", "sword", "wheat"};
        for (int i = 0; i < resources.length; i++) {
            ImageView temp = new ImageView(new Image(MakeTradeView.class.getResource("/images/resource/" + resources[i] + ".png").toExternalForm()));
            temp.setOnMouseClicked(event -> {
                resourceAmountLabel.setText("0");
                resourceGrid.getChildren().forEach(node -> {
                    ((ImageView) node).setFitHeight(32);
                    ((ImageView) node).setFitWidth(32);
                    node.setMouseTransparent(false);
                });
                temp.setFitHeight(64);
                temp.setFitWidth(64);
                temp.setMouseTransparent(true);
                selectedResource = resources[resourceGrid.getChildren().indexOf(temp)];
                Game.getTurnedUserForGame().getUserGovernment().getResources().forEach((resource, amount) -> {
                    if (resource.equals(selectedResource)) {
                        resourceMax = amount;
                    }
                });
            });
            temp.setFitHeight(32);
            temp.setFitWidth(32);
            temp.setPickOnBounds(true);
            resourceGrid.add(temp, i % 6, i / 6);
        }

        sendButton.setOnAction(event -> {
            if (toggleGroup.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No trade type selected");
                alert.setContentText("Please select a trade type");
                alert.showAndWait();
            } else if (selectedResource == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No resource selected");
                alert.setContentText("Please select a resource");
                alert.showAndWait();
            } else if (Integer.parseInt(resourceAmountLabel.getText()) == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No resource amount selected");
                alert.setContentText("Please select a resource amount");
                alert.showAndWait();
            } else {
                TradeControl.trade(selectedResource, Integer.parseInt(resourceAmountLabel.getText()), Integer.parseInt(priceField.getText()) , messageTextArea.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Trade request sent");
                alert.setContentText("Your trade request has been sent");
                alert.showAndWait();
                stage.close();
            }
        });
        stage.setTitle("Make a Trade");
        stage.setScene(new Scene(pane));
    }
}
