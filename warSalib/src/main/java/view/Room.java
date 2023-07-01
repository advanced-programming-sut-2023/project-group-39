package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Game;
import control.BuildingControl;
import control.GameControl;
import control.MapControl;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
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
import view.enums.commands.MapMenuCommands;
import javafx.scene.layout.*;
import view.enums.messages.BuildingMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;

import java.awt.*;

public class Room extends Application {

    public static Pane RoomPane;

    public static Stage RoomStage;
 //   private RoomChat roomChat;

//    public Room(RoomChat roomChat) {
  //      this.roomChat = roomChat;
  //  }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Room.fxml"));
        RoomPane = pane;
        RoomStage = stage;
        Image image = new Image(StartGame.class.getResource("/images/roomsBack2.png").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("Rooms:");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() {
//        VBox chats=new VBox();
//        for (int i=0;i<this.roomChat.getMessages().size();i++){
//            HBox hBox=new HBox();
//            Rectangle image=null;
//            if(roomChat.getMessages().get(i).getUserSendMessage().getChooseImageAddress()!=null){
//                ImagePattern imagePattern=new ImagePattern(new Image(roomChat.getMessages().get(i).getUserSendMessage().getChooseImageAddress()));
//                image.setFill(imagePattern);
//            }
//            Label username=new Label(roomChat.getMessages().get(i).getUserSendMessage().getUsername());
//            Label label=new Label(roomChat.getMessages().get(i).getMessage()+"         "+roomChat.getMessages().get(i).getDate());
//            if(image!=null)
//            hBox.getChildren().add(image);
//            hBox.getChildren().addAll(username,label);
//            chats.getChildren().add(hBox);
//        }
//        RoomPane.getChildren().add(chats);
//    }
    }
}
