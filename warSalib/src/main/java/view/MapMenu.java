package view;

import control.GameControl;
import control.MapControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Game;
import model.government.Government;
import model.government.building.group.BuildingImages;
import model.government.people.People;
import model.government.people.units.UnitButton;
import model.government.people.units.Units;
import model.government.people.units.UnitButton;
import model.government.people.units.UnitsName;
import javafx.scene.image.Image;
import model.government.building.Building;
import model.map.GameMap;
import model.map.Tile;
import view.enums.commands.MapMenuCommands;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu extends Application {

    public ScrollPane scroll;

    public HBox buildingSelection;
    public Label popularity;
    public Label wealth;
    public Label population;
    private int tileSize = 50;

    private Tile[][] tiles;

    private static ArrayList<Tile> selectedTile = new ArrayList<>();

    private static Popup informationPopup = new Popup();

    private static Stage mapStage;

    @Override
    public void start(Stage stage) throws Exception {
        mapStage = stage;
        GridPane gridPane = createTileMap();
        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        BorderPane borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/mapMenu.fxml"));
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
                            } else if (keyEvent.getCode() == KeyCode.A) {
                                selectLocationForMove(selectedTile);

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
    private void selectLocationForMove(ArrayList<Tile> selectedTile) {
        ArrayList<Units> playerUnit = new ArrayList<>();
        ArrayList<ArrayList<Units>> unitsKind=new ArrayList<>();
        ArrayList<UnitButton> unitsButtons=new ArrayList<>();
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
                int flag=0;
                for (Units units : playerUnit) {
                    for (int i=0;i<unitsKind.size();i++){
                        if(unitsKind.get(i).get(0).getUnitsName().equals(units.getUnitsName())){
                            unitsKind.get(i).add(units);
                            flag=1;
                        }
                    }
                    if(flag==0){
                        ArrayList<Units> units1=new ArrayList<>();
                        units1.add(units);
                        unitsKind.add(units1);
                    }
                    flag=0;
                }
                Popup chooseUnits = new Popup();
                VBox vBox=new VBox();
                vBox.setSpacing(15);
                vBox.setStyle("-fx-background-color: #DCD291B6");
                vBox.setSpacing(15);
                for (int i=0;i<unitsKind.size();i++){
                    HBox hBox=new HBox();
                    hBox.setSpacing(10);
                    Label label1=new Label(unitsKind.get(i).get(0).getUnitsName().getName());
                    label1.setTextFill(Color.WHITE);
                    String speed= String.valueOf(unitsKind.get(i).get(0).getUnitsName().getSpeed());
                    Label label2=new Label(speed);
                    label2.setTextFill(Color.WHITE);
                    String hitPoint= String.valueOf(unitsKind.get(i).get(0).getHitPoint());
                    Label label3=new Label(hitPoint);
                    label3.setTextFill(Color.WHITE);
                    String number= String.valueOf(unitsKind.get(i).size());
                    Label label4=new Label(number);
                    label4.setTextFill(Color.WHITE);
                    Button button=new Button("choose");
                    UnitButton myButton=new UnitButton(unitsKind.get(i),button);
                    unitsButtons.add(myButton);
                    hBox.getChildren().addAll(label1,label2,label3,label4,button);
                    vBox.getChildren().add(hBox);
                }

                for(UnitButton Button:unitsButtons){
                    Button.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ArrayList<Units> selectingUnits=new ArrayList<>();
                            selectingUnits.addAll(Button.getUnits());
                            GameControl.currentUnits.addAll(selectingUnits);
                        }
                    });
                }
                TextField xLocation=new TextField();
                TextField yLocation=new TextField();
                HBox hBox=new HBox();
                hBox.setSpacing(30);
                xLocation.setPromptText("enter x location");
                yLocation.setPromptText("enter y location");
                hBox.getChildren().addAll(xLocation,yLocation);
                vBox.getChildren().add(hBox);
               Button submit=new Button("Go");
               chooseUnits.getContent().add(vBox);
               chooseUnits.show(mapStage);
               vBox.getChildren().add(submit);
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
         scroll.setFitToWidth(true);
    }

    private void initBuilding(HBox building) {
        for (Image image : BuildingImages.getMilitaryBuilding()) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        building.getChildren().add(imageView);
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
        //   Units.makeUnit(0,0,UnitsName.ARCHER,Game.getGameStarter());
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
}
