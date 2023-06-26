package view;

import control.GameControl;
import control.MapControl;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Game;
import model.government.Government;
import model.government.building.group.BuildingImages;
import model.government.people.People;
import model.government.people.units.*;
import model.government.people.units.UnitButton;
import javafx.scene.image.Image;
import model.government.building.Building;
import model.government.resource.Resource;
import model.map.GameMap;
import model.map.Tile;
import view.enums.commands.MapMenuCommands;
import javafx.scene.layout.*;
import view.enums.messages.GameMenuMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import javafx.geometry.Insets;

public class MapMenu extends Application {

    public HBox buildingSelection;
    public Label popularity;
    public Label wealth;
    public Label population;
    private int tileSize = 50;

    private Tile[][] tiles;

    private static int counterTurns=0;

    private static ArrayList<Tile> selectedTile = new ArrayList<>();

    private static Popup informationPopup = new Popup();

    public static Alert moveAlert = new Alert(Alert.AlertType.ERROR);

    private static Stage mapStage;

    public static Popup attackPopup = new Popup();

    public static ArrayList<Units> unitToAttack = new ArrayList<>();

    public static void showMoveAlert() {
        moveAlert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws Exception {
        mapStage = stage;
        GridPane gridPane = createTileMap();
        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/mapMenu.fxml"));
        ;
        borderPane.setCenter(scrollPane);
        Scene scene = new Scene(borderPane);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    scrollPane.requestFocus();
                    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent keyEvent) {
                            if (keyEvent.getCode() == KeyCode.EQUALS) {
                                System.out.println("zoom In");
                                zoomIn();
                            } else if (keyEvent.getCode() == KeyCode.MINUS) {
                                System.out.println("zoom out");
                                zoomOut();
                            } else if (keyEvent.getCode() == KeyCode.M) {
                                selectLocationForMove(selectedTile);

                            } else if (keyEvent.getCode() == KeyCode.C) {
                                selectTileforAttack(selectedTile);

                            } else if (keyEvent.getCode() == KeyCode.A) {
                                selectTileToAttack(selectedTile);

                            } else if (keyEvent.getCode() == KeyCode.U) {
                                moveAlert.showAndWait();

                            } else if (keyEvent.getCode() == KeyCode.E) {
                                selectTileToAirAttack(selectedTile);

                            } else if (keyEvent.getCode() == KeyCode.D) {
                                makeDitch(selectedTile);
                            } else if (keyEvent.getCode() == KeyCode.P) {
                                makePatrolUnit(selectedTile);

                            }
                        }
                    });
                }
            }

        }).start();
        stage.setTitle("Scrollable Tile Map Example");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        popularity.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getPopularity()));
        wealth.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getWealth()));
        population.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getPopulation() + "/"
                + Game.getTurnedUserForGame().getUserGovernment().getPopulationCapacity()));
//         popularity.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getPopularity()));
//         wealth.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getWealth()));
//         population.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getPopulation() + "/"
//         + Game.getTurnedUserForGame().getUserGovernment().getPopulationCapacity()));
        initBuilding(buildingSelection);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(buildingSelection);
        scrollPane.setFitToHeight(true);
    }

    private void selectLocationForMove(ArrayList<Tile> selectedTile) {
        GameControl.currentUnits.clear();
        ArrayList<Units> playerUnit = new ArrayList<>();
        ArrayList<ArrayList<Units>> unitsKind = new ArrayList<>();
        ArrayList<UnitButton> unitsButtons = new ArrayList<>();
        ArrayList<ArrayList<Units>> differentUnits = new ArrayList<>();
        if (selectedTile == null || selectedTile.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please select a tile");
            alert.showAndWait();
        } else if (selectedTile.size() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please choose just one tile");
            alert.showAndWait();

        } else {
            for (People units : selectedTile.get(0).getPeopleOnTile()) {
                if (units instanceof Units && units.getOwnerPerson().equals(Game.getTurnedUserForGame())) {
                    playerUnit.add((Units) units);

                }
            }
            if (playerUnit.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you dont have any unit on this tile");
                alert.showAndWait();

            } else {
                int flag = 0;
                for (Units units : playerUnit) {
                    for (int i = 0; i < unitsKind.size(); i++) {
                        if (unitsKind.get(i).get(0).getUnitsName().equals(units.getUnitsName())) {
                            unitsKind.get(i).add(units);
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        ArrayList<Units> units1 = new ArrayList<>();
                        units1.add(units);
                        unitsKind.add(units1);
                    }
                    flag = 0;
                }
                Popup chooseUnits = new Popup();
                VBox vBox = new VBox();
                vBox.setSpacing(15);
                vBox.setStyle("-fx-background-color: #DCD291B6");
                vBox.setSpacing(15);
                for (int i = 0; i < unitsKind.size(); i++) {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    Label label1 = new Label(unitsKind.get(i).get(0).getUnitsName().getName());
                    label1.setTextFill(Color.WHITE);
                    String speed = String.valueOf(unitsKind.get(i).get(0).getUnitsName().getSpeed());
                    Label label2 = new Label(speed);
                    label2.setTextFill(Color.WHITE);
                    String hitPoint = String.valueOf(unitsKind.get(i).get(0).getHitPoint());
                    Label label3 = new Label(hitPoint);
                    label3.setTextFill(Color.WHITE);
                    String number = String.valueOf(unitsKind.get(i).size());
                    Label label4 = new Label(number);
                    label4.setTextFill(Color.WHITE);
                    Button button = new Button("choose");
                    UnitButton myButton = new UnitButton(unitsKind.get(i), button);
                    unitsButtons.add(myButton);
                    hBox.getChildren().addAll(label1, label2, label3, label4, button);
                    vBox.getChildren().add(hBox);
                }

                for (UnitButton Button : unitsButtons) {
                    Button.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ArrayList<Units> selectingUnits = new ArrayList<>();
                            selectingUnits.addAll(Button.getUnits());
                            GameControl.currentUnits.addAll(selectingUnits);
                        }
                    });
                }
                TextField xLocation = new TextField();
                TextField yLocation = new TextField();
                HBox hBox = new HBox();
                hBox.setSpacing(30);
                xLocation.setPromptText("enter x location");
                yLocation.setPromptText("enter y location");
                hBox.getChildren().addAll(xLocation, yLocation);
                vBox.getChildren().add(hBox);
                Button submit = new Button("Go");
                chooseUnits.getContent().add(vBox);
                chooseUnits.show(mapStage);
                vBox.getChildren().add(submit);
                submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (xLocation.getText() == null || xLocation.getText().equals("") || yLocation.getText().equals("") || yLocation == null) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("please fill destination");
                            alert.showAndWait();

                        } else {
                            chooseUnits.hide();
                            int x = Integer.parseInt(xLocation.getText());
                            int y = Integer.parseInt(yLocation.getText());
                            moveAnimation moveAnimation = new moveAnimation(GameControl.currentUnits, x, y);
                            moveAnimation.play();
                        }
                    }
                });
            }
        }
    }

    private void makePatrolUnit(ArrayList<Tile> selectedTiles) {
        GameControl.currentUnits.clear();
        ArrayList<Units> playerUnit = new ArrayList<>();
        ArrayList<ArrayList<Units>> unitsKind = new ArrayList<>();
        ArrayList<UnitButton> unitsButtons = new ArrayList<>();
        ArrayList<ArrayList<Units>> differentUnits = new ArrayList<>();
        if (selectedTile == null || selectedTile.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please select a tile");
            alert.showAndWait();
        } else if (selectedTile.size() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please choose just one tile");
            alert.showAndWait();

        } else {
            for (People units : selectedTile.get(0).getPeopleOnTile()) {
                if (units instanceof Units && units.getOwnerPerson().equals(Game.getTurnedUserForGame())) {
                    playerUnit.add((Units) units);

                }
            }
            if (playerUnit.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you dont have any unit on this tile");
                alert.showAndWait();

            } else {
                int flag = 0;
                for (Units units : playerUnit) {
                    for (int i = 0; i < unitsKind.size(); i++) {
                        if (unitsKind.get(i).get(0).getUnitsName().equals(units.getUnitsName())) {
                            unitsKind.get(i).add(units);
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        ArrayList<Units> units1 = new ArrayList<>();
                        units1.add(units);
                        unitsKind.add(units1);
                    }
                    flag = 0;
                }
                Popup chooseUnits = new Popup();
                VBox vBox = new VBox();
                vBox.setSpacing(15);
                vBox.setStyle("-fx-background-color: #DCD291B6");
                vBox.setSpacing(15);
                for (int i = 0; i < unitsKind.size(); i++) {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    Label label1 = new Label(unitsKind.get(i).get(0).getUnitsName().getName());
                    label1.setTextFill(Color.WHITE);
                    String speed = String.valueOf(unitsKind.get(i).get(0).getUnitsName().getSpeed());
                    Label label2 = new Label(speed);
                    label2.setTextFill(Color.WHITE);
                    String hitPoint = String.valueOf(unitsKind.get(i).get(0).getHitPoint());
                    Label label3 = new Label(hitPoint);
                    label3.setTextFill(Color.WHITE);
                    String number = String.valueOf(unitsKind.get(i).size());
                    Label label4 = new Label(number);
                    label4.setTextFill(Color.WHITE);
                    Button button = new Button("choose");
                    UnitButton myButton = new UnitButton(unitsKind.get(i), button);
                    unitsButtons.add(myButton);
                    hBox.getChildren().addAll(label1, label2, label3, label4, button);
                    vBox.getChildren().add(hBox);
                }

                for (UnitButton Button : unitsButtons) {
                    Button.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ArrayList<Units> selectingUnits = new ArrayList<>();
                            selectingUnits.addAll(Button.getUnits());
                            GameControl.currentUnits.addAll(selectingUnits);
                        }
                    });
                }
                TextField xLocation = new TextField();
                TextField yLocation = new TextField();
                HBox hBox = new HBox();
                hBox.setSpacing(30);
                xLocation.setPromptText("enter  patrol x");
                yLocation.setPromptText("enter  pratrol y");
                hBox.getChildren().addAll(xLocation, yLocation);
                vBox.getChildren().add(hBox);
                Button submit = new Button("Start Patrol");
                chooseUnits.getContent().add(vBox);
                chooseUnits.show(mapStage);
                vBox.getChildren().add(submit);
                submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (xLocation.getText() == null || xLocation.getText().equals("") || yLocation.getText().equals("") || yLocation == null) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("please fill destination");
                            alert.showAndWait();

                        } else {
                            int patrolY = Integer.parseInt(yLocation.getText());
                            int patrolX = Integer.parseInt(xLocation.getText());
                            int deltaX = patrolX - GameControl.currentUnits.get(0).getxLocation();
                            int deltaY = patrolY - GameControl.currentUnits.get(0).getyLocation();
                            int destination = (int) Math.sqrt((Math.pow(deltaX, 2)) + Math.pow(deltaY, 2));
                            chooseUnits.hide();
                            if (destination > GameControl.currentUnits.get(0).getUnitsName().getSpeed()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("destination for patrol is bigger than unit speed");
                                alert.showAndWait();
                            } else {
                                for (Units units : GameControl.currentUnits) {
                                    units.setPatrolToX(patrolX);
                                    units.setPatrolToY(patrolY);
                                    //TODO another part of patrol should be complete in next turn
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void makeDitch(ArrayList<Tile> selectedTiles) {
        if (selectedTiles.size() != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please choose one tile for ditch");
            alert.showAndWait();
        } else {
            int flag = 0;
            Tile tile = selectedTiles.get(0);
            for (People people : tile.getPeopleOnTile()) {
                if (people instanceof Units) {
                    if (people.getOwnerPerson().equals(Game.getTurnedUserForGame()) && ((Units) people).getUnitsName().getName().equals("tunneler"))
                        tile.setHasKillerTale(true);
                    flag = 1;
                }
            }
            if (flag == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you don't have any tunneler on this house");
                alert.showAndWait();
            }
        }
    }

    private void selectTileforAttack(ArrayList<Tile> selectedTiles) {
        GameControl.currentUnits.clear();
        ArrayList<Units> playerUnit = new ArrayList<>();
        ArrayList<ArrayList<Units>> unitsKind = new ArrayList<>();
        ArrayList<UnitButton> unitsButtons = new ArrayList<>();
        ArrayList<ArrayList<Units>> differentUnits = new ArrayList<>();
        if (selectedTile == null || selectedTile.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please select a tile");
            alert.showAndWait();
        } else if (selectedTile.size() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please choose just one tile");
            alert.showAndWait();

        } else {
            for (People units : selectedTile.get(0).getPeopleOnTile()) {
                if (units instanceof Units && units.getOwnerPerson().equals(Game.getTurnedUserForGame())) {
                    playerUnit.add((Units) units);

                }
            }
            if (playerUnit.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you dont have any unit on this tile");
                alert.showAndWait();

            } else {
                int flag = 0;
                for (Units units : playerUnit) {
                    for (int i = 0; i < unitsKind.size(); i++) {
                        if (unitsKind.get(i).get(0).getUnitsName().equals(units.getUnitsName())) {
                            unitsKind.get(i).add(units);
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        ArrayList<Units> units1 = new ArrayList<>();
                        units1.add(units);
                        unitsKind.add(units1);
                    }
                    flag = 0;
                }
                Popup chooseUnits = new Popup();
                VBox vBox = new VBox();
                vBox.setSpacing(15);
                vBox.setStyle("-fx-background-color: #DCD291B6");
                vBox.setSpacing(15);
                for (int i = 0; i < unitsKind.size(); i++) {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    Label label1 = new Label(unitsKind.get(i).get(0).getUnitsName().getName());
                    label1.setTextFill(Color.WHITE);
                    String speed = String.valueOf(unitsKind.get(i).get(0).getUnitsName().getSpeed());
                    Label label2 = new Label(speed);
                    label2.setTextFill(Color.WHITE);
                    String hitPoint = String.valueOf(unitsKind.get(i).get(0).getHitPoint());
                    Label label3 = new Label(hitPoint);
                    label3.setTextFill(Color.WHITE);
                    String number = String.valueOf(unitsKind.get(i).size());
                    Label label4 = new Label(number);
                    label4.setTextFill(Color.WHITE);
                    Button button = new Button("choose");
                    UnitButton myButton = new UnitButton(unitsKind.get(i), button);
                    unitsButtons.add(myButton);
                    hBox.getChildren().addAll(label1, label2, label3, label4, button);
                    vBox.getChildren().add(hBox);
                }
                chooseUnits.getContent().add(vBox);
                chooseUnits.show(mapStage);

                for (UnitButton Button : unitsButtons) {
                    Button.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ArrayList<Units> selectingUnits = new ArrayList<>();
                            selectingUnits.addAll(Button.getUnits());
                            GameControl.currentUnits.addAll(selectingUnits);
                            chooseUnits.hide();
                        }
                    });
                }
            }
        }
    }

    private static void selectTileToAttack(ArrayList<Tile> selectedTile) {
        unitToAttack.clear();
        if (selectedTile.size() != 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please choose just one tile to attack");
            alert.showAndWait();

        } else {
            ArrayList<Units> playerUnit = new ArrayList<>();
            ArrayList<ArrayList<Units>> unitsKind = new ArrayList<>();
            ArrayList<UnitButton> unitsButtons = new ArrayList<>();
            ArrayList<ArrayList<Units>> differentUnits = new ArrayList<>();
            for (People units : selectedTile.get(1).getPeopleOnTile()) {
                if (units instanceof Units && !units.getOwnerPerson().equals(Game.getTurnedUserForGame())) {
                    playerUnit.add((Units) units);

                }
            }
            if (playerUnit.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("units of another users dont find!");
                alert.showAndWait();

            } else {
                int flag = 0;
                for (Units units : playerUnit) {
                    for (int i = 0; i < unitsKind.size(); i++) {
                        if (unitsKind.get(i).get(0).getUnitsName().equals(units.getUnitsName())) {
                            unitsKind.get(i).add(units);
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        ArrayList<Units> units1 = new ArrayList<>();
                        units1.add(units);
                        unitsKind.add(units1);
                    }
                    flag = 0;
                }
                Popup chooseUnits = new Popup();
                VBox vBox = new VBox();
                vBox.setSpacing(15);
                vBox.setStyle("-fx-background-color: #DCD291B6");
                vBox.setSpacing(15);
                for (int i = 0; i < unitsKind.size(); i++) {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    Label label1 = new Label(unitsKind.get(i).get(0).getUnitsName().getName());
                    label1.setTextFill(Color.WHITE);
                    String speed = String.valueOf(unitsKind.get(i).get(0).getUnitsName().getSpeed());
                    Label label2 = new Label(speed);
                    label2.setTextFill(Color.WHITE);
                    String hitPoint = String.valueOf(unitsKind.get(i).get(0).getHitPoint());
                    Label label3 = new Label(hitPoint);
                    label3.setTextFill(Color.WHITE);
                    String number = String.valueOf(unitsKind.get(i).size());
                    Label label4 = new Label(number);
                    label4.setTextFill(Color.WHITE);
                    Button button = new Button("choose");
                    UnitButton myButton = new UnitButton(unitsKind.get(i), button);
                    unitsButtons.add(myButton);
                    hBox.getChildren().addAll(label1, label2, label3, label4, button);
                    vBox.getChildren().add(hBox);
                }

                for (UnitButton Button : unitsButtons) {
                    Button.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ArrayList<Units> selectingUnits = new ArrayList<>();
                            selectingUnits.addAll(Button.getUnits());
                            unitToAttack.addAll(selectingUnits);
                        }
                    });
                }
                HBox hBox = new HBox();
                Button button = new Button("attack");
                hBox.getChildren().add(button);
                vBox.getChildren().add(hBox);
                chooseUnits.getContent().add(vBox);
                chooseUnits.show(mapStage);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        chooseUnits.hide();
                        ArrayList<Units> deathPersons = new ArrayList<>();
                        moveAnimation moveAnimation1 = new moveAnimation(GameControl.currentUnits, unitToAttack.get(0).getxLocation(), unitToAttack.get(0).getyLocation());
                        moveAnimation1.setAttackFlag(1);
                        if (moveAnimation1.getMoveRoad().get(moveAnimation1.getMoveRoad().size() - 1).getX() != unitToAttack.get(0).getxLocation() || moveAnimation1.getMoveRoad().get(moveAnimation1.getMoveRoad().size() - 1).getY() != unitToAttack.get(0).getyLocation()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("this destination is bigger than unit speed");
                            alert.showAndWait();
                        } else {
                            moveAnimation1.play();
                            HBox hBox1 = new HBox();
                            hBox1.setStyle("-fx-background-color: #DCDC91B6");
                            Label label1 = new Label(GameControl.currentUnits.get(0).getUnitsName().getName() + "   is attacking");
                            hBox1.getChildren().add(label1);
                            attackPopup.getContent().add(hBox1);
                            attackPopup.show(mapStage);


                        }
                    }
                });
            }
        }
    }

    private void selectTileToAirAttack(ArrayList<Tile> selectedTiles) {
        unitToAttack.clear();
        if (selectedTile.size() != 2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please choose just one tile to attack");
            alert.showAndWait();

        } else {
            if (!GameControl.currentUnits.get(0).getUnitsName().getUnitsType().equals(UnitsType.ARCHER)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("for air attack you should choose archers");
                alert.showAndWait();

            } else {

                ArrayList<Units> playerUnit = new ArrayList<>();
                ArrayList<ArrayList<Units>> unitsKind = new ArrayList<>();
                ArrayList<UnitButton> unitsButtons = new ArrayList<>();
                ArrayList<ArrayList<Units>> differentUnits = new ArrayList<>();
                for (
                        People units : selectedTile.get(1).getPeopleOnTile()) {
                    if (units instanceof Units && !units.getOwnerPerson().equals(Game.getTurnedUserForGame())) {
                        playerUnit.add((Units) units);

                    }
                }
                if (playerUnit.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("units of another users dont find!");
                    alert.showAndWait();

                } else {
                    int flag = 0;
                    for (Units units : playerUnit) {
                        for (int i = 0; i < unitsKind.size(); i++) {
                            if (unitsKind.get(i).get(0).getUnitsName().equals(units.getUnitsName())) {
                                unitsKind.get(i).add(units);
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            ArrayList<Units> units1 = new ArrayList<>();
                            units1.add(units);
                            unitsKind.add(units1);
                        }
                        flag = 0;
                    }
                    Popup chooseUnits = new Popup();
                    VBox vBox = new VBox();
                    vBox.setSpacing(15);
                    vBox.setStyle("-fx-background-color: #DCD291B6");
                    vBox.setSpacing(15);
                    for (int i = 0; i < unitsKind.size(); i++) {
                        HBox hBox = new HBox();
                        hBox.setSpacing(10);
                        Label label1 = new Label(unitsKind.get(i).get(0).getUnitsName().getName());
                        label1.setTextFill(Color.WHITE);
                        String speed = String.valueOf(unitsKind.get(i).get(0).getUnitsName().getSpeed());
                        Label label2 = new Label(speed);
                        label2.setTextFill(Color.WHITE);
                        String hitPoint = String.valueOf(unitsKind.get(i).get(0).getHitPoint());
                        Label label3 = new Label(hitPoint);
                        label3.setTextFill(Color.WHITE);
                        String number = String.valueOf(unitsKind.get(i).size());
                        Label label4 = new Label(number);
                        label4.setTextFill(Color.WHITE);
                        Button button = new Button("choose");
                        UnitButton myButton = new UnitButton(unitsKind.get(i), button);
                        unitsButtons.add(myButton);
                        hBox.getChildren().addAll(label1, label2, label3, label4, button);
                        vBox.getChildren().add(hBox);
                    }

                    for (UnitButton Button : unitsButtons) {
                        Button.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                ArrayList<Units> selectingUnits = new ArrayList<>();
                                selectingUnits.addAll(Button.getUnits());
                                unitToAttack.addAll(selectingUnits);
                            }
                        });
                    }
                    HBox hBox = new HBox();
                    Button button = new Button("Air attack");
                    hBox.getChildren().add(button);
                    vBox.getChildren().add(hBox);
                    chooseUnits.getContent().add(vBox);
                    chooseUnits.show(mapStage);
                    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            chooseUnits.hide();
                            airAttack(GameControl.currentUnits, unitToAttack);
                        }
                    });

                }
            }
        }
    }

    private void airAttack(ArrayList<Units> currentUnits, ArrayList<Units> unitToAttack) {
        int xDistance = unitToAttack.get(0).getxLocation() - currentUnits.get(0).getxLocation();
        int yDistance = unitToAttack.get(0).getyLocation() - currentUnits.get(0).getyLocation();
        double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        int dis = (int) distance;
        if ((((Archers) currentUnits.get(0)).getArrowRadius() / 20) < dis) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("destination is bigger than archer arrow radius");
            alert.showAndWait();
        } else {
            Resource wartool = ((Archers) currentUnits.get(0)).getWartool();
            if ((Game.getTurnedUserForGame().getUserGovernment().getResources().get(wartool) < currentUnits.size())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you dont have enough resource to air attack");
                alert.showAndWait();
            } else {
                for (People people : unitToAttack) {
                    int efficiently = ((Archers) currentUnits.get(0)).getFatality() * ((Archers) currentUnits.get(0)).getPrecision() / 100;
                    int eff = (int) efficiently;
                    ((Units) people).changeHitPoint(-1 * eff);
                    Game.getTurnedUserForGame().changeScore(eff);


                }
                ArrayList<Units> deathUnits = new ArrayList<>();
                for (Units units : unitToAttack) {
                    if (units.getHitPoint() <= 0) {
                        deathUnits.add(units);

                    }
                }
                for (Units unit : deathUnits) {
                    unit.getOwnerPerson().getUserGovernment().getPeople().remove(unit);
                    Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getPeopleOnTile().remove(unit);
                    unit.getOwnerPerson().getUserGovernment().setPopulation(unit.getOwnerPerson().getUserGovernment().getPopulation() - 1);
                    Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getChildren().remove(unit);
                    Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getTooltip().setText(MapControl.showDetails(unit.getxLocation(), unit.getyLocation()));
                }
                Game.getTurnedUserForGame().getUserGovernment().getResources().put(wartool, Game.getTurnedUserForGame().getUserGovernment().getResources().get(wartool) - currentUnits.size());
            }
        }
    }


    private void initBuilding(HBox building) {
        Button nextTurn =new Button("next Turn");
        building.getChildren().add(nextTurn);
        for (Image image : BuildingImages.getMilitaryBuilding()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            building.getChildren().add(imageView);
        }
        nextTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextTurn();
            }
        });
    }

    private static void attack() {
        ArrayList<Units> deathPersons = new ArrayList<>();
        moveAnimation moveAnimation = new moveAnimation(GameControl.currentUnits, unitToAttack.get(0).getxLocation(), unitToAttack.get(0).getyLocation());
        if (moveAnimation.getMoveRoad().get(moveAnimation.getMoveRoad().size() - 1).getX() != unitToAttack.get(0).getxLocation() || moveAnimation.getMoveRoad().get(moveAnimation.getMoveRoad().size() - 1).getY() != unitToAttack.get(0).getyLocation()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("this destination is bigger than unit speed");
            alert.showAndWait();
        } else {
            moveAnimation.play();
            if (moveAnimation.getStatus().equals(Animation.Status.STOPPED)) {
                for (Units units1 : GameControl.currentUnits) {
                    for (Units units2 : unitToAttack) {
                        int unit1ChangeHitPoint = units2.getUnitsName().getAttackingPower() * units2.getEfficientAttackingPower() / 5;
                        int unit2ChangeHitPoint = units1.getUnitsName().getAttackingPower() * units1.getEfficientAttackingPower() / 5;
                        units1.changeHitPoint(-1 * unit1ChangeHitPoint);
                        units2.changeHitPoint(-1 * unit2ChangeHitPoint);
                        if (units1.getHitPoint() <= 0 && !deathPersons.contains(units1)) {
                            deathPersons.add(units1);
                        }
                        if (units2.getHitPoint() <= 0 && !deathPersons.contains(units2)) {
                            deathPersons.add(units2);
                        }

                    }
                }
                //       System.out.println(deathPersons.size());
                for (Units unit : deathPersons) {
                    unit.getOwnerPerson().getUserGovernment().getPeople().remove(unit);
                    Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getPeopleOnTile().remove(unit);
                    unit.getOwnerPerson().getUserGovernment().setPopulation(unit.getOwnerPerson().getUserGovernment().getPopulation() - 1);
                    Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getChildren().remove(unit);
                }
            }
        }
    }

    private void zoomOut() {
        if (tileSize > 30 && tiles != null) {
            tileSize -= 5;
            Game.setTileSize(tileSize);
            for (int j = 0; j < 100; j++) {
                for (int i = 0; i < 100; i++) {
                    tiles[j][i].setMinWidth(tileSize);
                    tiles[j][i].setMinHeight(tileSize);
                }
            }
        }
    }

    private void zoomIn() {
        if (tileSize <= 60) {
            tileSize += 5;
            Game.setTileSize(tileSize);
            for (int j = 0; j < 100; j++) {
                for (int i = 0; i < 100; i++) {
                    tiles[j][i].setMinWidth(tileSize);
                    tiles[j][i].setMinHeight(tileSize);
                }
            }
        }
    }

    private GridPane createTileMap() {
        GameMap gameMap = new GameMap();
        Game.setMapInGame(gameMap);
        GridPane gridPane = new GridPane();
        tiles = Game.getMapInGame().getMap();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Tile tile = tiles[i][j];
                tile.setMinWidth(tileSize);
                tile.setMinHeight(tileSize);
                tile.setOnMouseEntered(this::handleMouseEntered);
                tile.setOnMouseExited(this::handleMouseExited);
                tile.setOnMouseClicked(event -> clickedAtBottom(tile));
                setTileTooltip(tiles[i][j], i, j);
                gridPane.add(tiles[i][j], i, j);
            }
        }
        for (Government government : Game.getGovernments()) {
            MainMenu.createInitialPeople(government, 30);
        }
        Units.makeUnit(0, 0, UnitsName.KNIGHT, Game.getGameStarter());
        Units.makeUnit(2, 0, UnitsName.SLINGERS, Game.getPlayersInGame().get(0));
        Units.makeUnit(0, 0, UnitsName.ARCHER, Game.getGameStarter());
        Game.getGameStarter().getUserGovernment().getResources().put(Resource.ARROW, 100);
        return gridPane;
    }

    private void clickedAtBottom(Tile tile) {
        //TODO : add something that see data about selected tile
        if (!selectedTile.contains(tile)) {
            selectedTile.add(tile);
            tile.setOpacity(0.2);
        } else {
            selectedTile.remove(tile);
            tile.setOpacity(1);
        }
    }

    private void setTileTooltip(Tile tile, int i, int j) {
        String details = MapControl.showDetails(j, i);
        Game.getMapInGame().getMap()[j][i].getTooltip().setText(details);
        Tooltip.install(tile, Game.getMapInGame().getMap()[j][i].getTooltip());
    }

    private void handleMouseExited(MouseEvent mouseEvent) {
        Tile tile = (Tile) mouseEvent.getSource();
        if (!selectedTile.contains(tile))
            tile.setOpacity(1);
        hideUnitsInformation(tile);
    }

    private void hideUnitsInformation(Tile tile) {
        if (informationPopup.isShowing()) {
            informationPopup.hide();

        }
    }

    private void showUnitsDetail(Tile tile) {
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #DCDC91B6");
        vBox.setSpacing(15);
        for (People units : tile.getPeopleOnTile()) {
            if (units instanceof Units) {
                HBox hBox = new HBox();
                hBox.setSpacing(10);
                Label empty = new Label("");
                Label unitName = new Label(((Units) units).getUnitsName().getName());
                Label unitOwner = new Label(units.getOwnerPerson().getUsername());
                String power = String.valueOf(((Units) units).getUnitsName().getAttackingPower());
                Label unitPower = new Label(power);
                String health = String.valueOf(((Units) units).getHitPoint());
                Label unitHealth = new Label(health);
                String Mode = String.valueOf(((Units) units).getState());
                Label unitMode = new Label(Mode);
                hBox.getChildren().addAll(empty, unitName, unitOwner, unitPower, unitHealth, unitMode);
                vBox.getChildren().add(hBox);

            }

        }
        if (informationPopup.getContent() != null) {
            informationPopup.getContent().clear();
        }
        if (tile.getPeopleOnTile().size() != 0) {
            informationPopup.getContent().add(vBox);
            informationPopup.show(mapStage);
        }
    }

    private void handleMouseEntered(MouseEvent mouseEvent) {
        Tile tile = (Tile) mouseEvent.getSource();
        if (!selectedTile.contains(tile))
            tile.setOpacity(0.5);
        showUnitsDetail(tile);

    }

    public static void run(String input, Scanner scanner) {
//        while (true) {
//            if (MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_MAP) != null) {
//                showMap(input);
//            } else if (MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_DETAILS) != null) {
//                showDetail(input);
//            } else if (MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_MAP) != null) {
//                moveMap(input);
//            } else if (input.matches("\\s*back\\s*")) {
//                System.out.println("back to game menu");
//                break;
//            } else System.out.println("invalid command!");
//            input = scanner.nextLine();
//        }
    }

    private Tile[][] showMap(int x, int y) {
        Tile[][] result;
        if ((result = MapControl.showMap(x, y)) == null)
            return result;
        else
            return null;
    }

    private static void moveMap(String input) {
        int up = 0, down = 0, left = 0, right = 0;
        Matcher matcher;
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_UP);
        if (matcher != null) {
            if (matcher.group("up") != null)
                up = Integer.parseInt(matcher.group("up"));
            else up = 1;
        }
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_DOWN);
        if (matcher != null) {
            if (matcher.group("down") != null)
                down = Integer.parseInt(matcher.group("down"));
            else down = 1;
        }
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_LEFT);
        if (matcher != null) {
            if (matcher.group("left") != null)
                left = Integer.parseInt(matcher.group("left"));
            else left = 1;
        }
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_RIGHT);
        if (matcher != null) {
            if (matcher.group("right") != null)
                right = Integer.parseInt(matcher.group("right"));
            else right = 1;
        }
        System.out.println(MapControl.moveMap(up, down, right, left));

    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    private void nextTurn(){
        System.out.println("Salam");
        counterTurns++;
             //TODO patrol unit should be added!
        if(counterTurns%5==0||counterTurns%8==0||counterTurns%11==0){
            ArrayList<Tile> sickTiles=new ArrayList<>();
            System.out.println(Game.getTurnedUserForGame().getUserGovernment().getXLeft());
            System.out.println(Game.getTurnedUserForGame().getUserGovernment().getYDown());
            sickTiles.add(Game.getMapInGame().getMap()[Game.getTurnedUserForGame().getUserGovernment().getXLeft()-1][Game.getTurnedUserForGame().getUserGovernment().getYDown()]);
            sickTiles.add(Game.getMapInGame().getMap()[Game.getTurnedUserForGame().getUserGovernment().getXLeft()-2][Game.getTurnedUserForGame().getUserGovernment().getYDown()]);
            sickTiles.add(Game.getMapInGame().getMap()[Game.getTurnedUserForGame().getUserGovernment().getXLeft()-3][Game.getTurnedUserForGame().getUserGovernment().getYDown()]);
            sickTiles.add(Game.getMapInGame().getMap()[Game.getTurnedUserForGame().getUserGovernment().getXLeft()-4][Game.getTurnedUserForGame().getUserGovernment().getYDown()]);
            sickTiles.add(Game.getMapInGame().getMap()[Game.getTurnedUserForGame().getUserGovernment().getXLeft()-5][Game.getTurnedUserForGame().getUserGovernment().getYDown()]);

            Color redOpacity=new Color(1,0,0,0.5);
            for (Tile tile:sickTiles){
                ImagePattern imagePattern=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/archer1.png").toExternalForm()));
                tile.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,Insets.EMPTY)));
                tile.setOpacity(0.5);
                tile.setBlendMode(BlendMode.SRC_OVER);
            }

        }
    }
}
