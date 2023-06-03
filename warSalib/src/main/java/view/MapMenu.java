package view;

import control.MapControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Game;
import javafx.scene.image.Image;
import model.map.GameMap;
import model.map.Tile;
import view.enums.commands.MapMenuCommands;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu extends Application {

    private int chooseX = 9;
    private int chooseY = 0;

    private Image image;
    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = createTileMap();
        javafx.scene.control.ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        Scene scene = new Scene(scrollPane);
        stage.setTitle("Scrollable Tile Map Example");
        stage.setScene(scene);
        stage.show();
    }

    private GridPane createTileMap() {
        GameMap gameMap = new GameMap();
        Game.setMapInGame(gameMap);
        GridPane gridPane = new GridPane();
        Tile[][] tiles = Game.getMapInGame().getMap();
        for (int i = 0 ; i < 200 ; i++) {
            for (int j = 0; j< 200; j++) {
                tiles[i][j].setOnMouseEntered(this :: handleMouseEntered);
                tiles[i][j].setOnMouseExited(this :: handleMouseExited);
                setTileTooltip(tiles[i][j], i, j);
                gridPane.add(tiles[i][j], i, j);
            }
        }
        return gridPane;
    }

    private void setTileTooltip(Tile tile, int i, int j) {
        String details = MapControl.showDetails(j,i);
        Tooltip tooltip = new Tooltip(details);
        Tooltip.install(tile,tooltip);
    }

    private void handleMouseExited(MouseEvent mouseEvent) {
        Tile tile = (Tile) mouseEvent.getSource();
        tile.setOpacity(1);
    }

    private void handleMouseEntered(MouseEvent mouseEvent) {
        Tile tile = (Tile) mouseEvent.getSource();
        tile.setOpacity(0.5);

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
        if ((result = MapControl.showMap(x, y))==null)
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

    public void setChooseX(int chooseX) {
        this.chooseX = chooseX;
    }

    public void setChooseY(int chooseY) {
        this.chooseY = chooseY;
    }
}
