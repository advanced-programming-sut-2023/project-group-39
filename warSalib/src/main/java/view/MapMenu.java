package view;

import control.BuildingControl;
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
import javafx.scene.input.Clipboard;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
import view.enums.messages.BuildingMessage;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;

public class MapMenu extends Application {

    public ScrollPane scroll;
    private BorderPane borderPane;

    public HBox buildingSelection;
    public Label popularity;
    public Label wealth;
    public Label population;
    public Circle church;
    public Circle resourceBuilding;
    public Circle foodBuilding;
    public Circle MilitaryBuilding;
    public Circle buildBuilding;
    public GridPane miniMap;
    private int tileSize = 50;

    private GridPane gridPane;

    private Tile[][] tiles;

    private static ArrayList<Tile> selectedTile = new ArrayList<>();

    private static Popup informationPopup = new Popup();

    private static Stage mapStage;

    @Override
    public void start(Stage stage) throws Exception {
        mapStage = stage;
        gridPane = createTileMap();
        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        borderPane = FXMLLoader.load(ProfileMenu.class.getResource("/fxml/mapMenu.fxml"));
        borderPane.setCenter(scrollPane);
        Scene scene = new Scene(borderPane);
        new Thread(new Runnable() {
            @Override
            public void run() {
                KeyCombination copyCombination = new KeyCodeCombination(KeyCode.C, KeyCombination.META_DOWN);
                KeyCombination pasteCombination = new KeyCodeCombination(KeyCode.V, KeyCombination.META_DOWN);
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

                            } else if (copyCombination.match(keyEvent)) {
                                copyBuildingImage();
                            } else if (pasteCombination.match(keyEvent)) {
                                pasteBuildingImage();
                            } else if (keyEvent.getCode() == KeyCode.B){
                                System.out.println("building is selected");
                                selectingBuilding();
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

    private void selectingBuilding() {
        if (selectedTile != null) {
            if (selectedTile.get(0).getBuilding() != null){
                if (selectedTile.get(0).getBuilding().getType().equals("castle building")){

                }
            }
        }
    }

    private void pasteBuildingImage() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String buildingName = clipboard.getString();
        Image buildingImage = new Image(buildingName);
        BuildingMessage message;
        if (buildingImage != null) {
            for (Tile tile : selectedTile){
                message = BuildingControl.dropBuilding(tile,BuildingImages.getNameOfBuildingByImage(buildingName));
                if (message.equals(BuildingMessage.SUCCESS)) {
                    tile.setBuildingImage(buildingName);
                    ImageView buildingView = new ImageView(buildingImage);
                    buildingView.setFitWidth(25);
                    buildingView.setFitHeight(25);
                    tile.getChildren().add(buildingView);
                } else if (message.equals(BuildingMessage.EXIST)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("there is another building in this tile");
                    alert.show();
                } else if (message.equals(BuildingMessage.BAD_GROUND)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("you can't drop building for ground");
                    alert.show();
                } else if (message.equals(BuildingMessage.NOT_ENOUGH_SOURCE)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("you have not enough source");
                    alert.show();
                }
            }
        }
    }

    private void copyBuildingImage() {
        if (selectedTile != null) {
            if (hasSameBuilding()){
                if (selectedTile.get(0).getBuildingImage() != null){
                    String image = selectedTile.get(0).getBuildingImage();
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(image);
                    clipboard.setContent(content);
                }
            }
            else {
                System.out.println("there are different building in selected tiles");
                Alert alert =  new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("different building and you can't copy");
                alert.showAndWait();
            }
        } else System.out.println("there is no selected tile");
    }

    private boolean hasSameBuilding() {
        String buildingName = "";
        if (selectedTile.size() == 1)
            return true;
        for (Tile tile : selectedTile){
            if (buildingName == null)
                buildingName = tile.getBuildingImage();
            else {
                if (buildingName.equals(tile.getBuildingImage()))
                    continue;
                else return false;
            }
        }
        return true;
    }

    private void selectLocationForMove(ArrayList<Tile> selectedTile) {
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
            }
        }

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
        church.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/churchSym.jpeg").toExternalForm())));
        foodBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/farming.png").toExternalForm())));
        buildBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/house.png").toExternalForm())));
        resourceBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/resourceSym.png").toExternalForm())));
        MilitaryBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/championcaribs.png").toExternalForm())));
        initMiniMap();
    }

    private void initMiniMap() {
        tiles = Game.getMapInGame().getMap();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                ImageView imageView = new ImageView(tiles[j][i].getImage());
                imageView.setFitHeight(0.8);
                imageView.setFitWidth(0.8);
                miniMap.add(imageView, j, i);
            }
        }
    }

//    private void updateGovernment(){
//        popularity.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getPopularity()));
//        wealth.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getWealth()));
//        population.setText(String.valueOf(Game.getTurnedUserForGame().getUserGovernment().getPopulation() + "/"
//                + Game.getTurnedUserForGame().getUserGovernment().getPopulationCapacity()));
//    }
    private void initBuilding(HBox building) {
        for (Image image : BuildingImages.getMilitaryBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            building.getChildren().add(imageView);
        }
    }

    private void handleDragBuilding(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getImage().getUrl());
        db.setContent(content);
        ImageView draggedContent = new ImageView(source.getImage());
        double smallerSize = 0.5 * source.getFitWidth();
        draggedContent.setFitWidth(smallerSize);
        draggedContent.setFitHeight(smallerSize);
        db.setDragView(draggedContent.snapshot(null, null));
        mouseEvent.consume();
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
                if (tile.getBuilding() != null) {
                    if (tile.getBuilding().getName().equals("keep")) {
                        ImageView buildingView = new ImageView(BuildingImages.getKeep());
                        buildingView.setFitWidth(25);
                        buildingView.setFitHeight(25);
                        tile.getChildren().add(buildingView);
                    }
                    else {
                        ImageView buildingView = new ImageView(BuildingImages.getStockPile());
                        buildingView.setFitWidth(25);
                        buildingView.setFitHeight(25);
                        tile.getChildren().add(buildingView);
                    }
                }
                dragEntered(tile);
                dragExited(tile);
                dragAndDrop(tile, i, j);
                dragOver(tile);
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

    private void dragOver(Tile tile) {
        tile.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString())
                    dragEvent.acceptTransferModes(TransferMode.COPY);
            }
        });
    }

    private void dragAndDrop(Tile tile, int i, int j) {
        tile.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                String buildingName;
                BuildingMessage message;
                boolean success = false;
                if (db.hasString()) {
                    String url = db.getString();
//                    Image image = db.getImage();
                    if ( (buildingName = BuildingImages.getNameOfBuildingByImage(url)) != null) {
                        message = BuildingControl.dropBuilding(tile,buildingName);
                        if (message.equals(BuildingMessage.SUCCESS)) {
                            Image image = new Image(url);
                            tile.setBuildingImage(url);
                            ImageView buildingView = new ImageView(image);
                            buildingView.setFitWidth(25);
                            buildingView.setFitHeight(25);
                            tile.getChildren().add(buildingView);
//                        updateGovernment();
                            success = true;
                        } else if (message.equals(BuildingMessage.EXIST)){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("there is another building in this tile");
                            alert.show();
                        } else if (message.equals(BuildingMessage.BAD_GROUND)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("you can't drop building for ground");
                            alert.show();
                        } else if (message.equals(BuildingMessage.NOT_ENOUGH_SOURCE)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("you have not enough source");
                            alert.show();
                        }
                    }
                }
                if (success == false)
                    System.out.println("not suc");
                else System.out.println("suc");
                dragEvent.setDropCompleted(success);
                dragEvent.consume();
            }
        });
    }

    private void dragEntered(Tile tile) {
        tile.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString())
                    tile.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
        });
    }

    private void dragExited(Tile tile) {
        tile.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString())
                    tile.setStyle("");
            }
        });
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

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void clickMiltary(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getMilitaryBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickBuildBuilding(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getBuildBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickFoodBuilding(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getFoodBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickResource(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getSourceBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickChurch(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getChurches().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            buildingSelection.getChildren().add(imageView);
        }
    }
}