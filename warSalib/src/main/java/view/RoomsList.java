package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;

import java.awt.*;
import java.util.ArrayList;
import control.GameControl;
import control.MapControl;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.shape.Rectangle;
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
import model.user.User;
import model.user.chat.RoomChat;
import view.enums.commands.MapMenuCommands;
import javafx.scene.layout.*;
import view.enums.messages.BuildingMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;

public class RoomsList extends Application {

    public static Pane roomsPane;

    public static Stage roomsStage;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/roomsList.fxml"));
        roomsPane = pane;
        roomsStage = stage;
        Image image = new Image(StartGame.class.getResource("/images/roomsBack.png").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("Rooms:");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private static void initialize() {
        VBox rooms=new VBox();
        ArrayList<Button> roomsButton=new ArrayList<>();
        for (int i = 0; i < Game.getCurrentUser().getUserRoomChats().size(); i++) {
            Button button=new Button(Game.getCurrentUser().getUserRoomChats().get(i).getGroupName());
            roomsButton.add(button);
            rooms.getChildren().add(button);
        }
        for (Button button:roomsButton){
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    RoomChat roomChat=Game.getCurrentUser().getUserRoomChats().get(roomsButton.indexOf(button));
                    try {
                        goToRoom(roomChat);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        roomsPane.getChildren().add(rooms);
    }

    private static void goToRoom(RoomChat roomChat) throws Exception {
        Room room=new Room(roomChat);
        room.start(StartGame.stage);
    }
}
