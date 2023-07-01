package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Client;
import model.Game;
import model.Player;
import model.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lobby extends Application {
    private List<Room> rooms;
    private List<Room> copyRooms;
    private Player player = new Player(Game.getCurrentUser());

    private VBox root;
    @Override
    public void start(Stage stage) {
        if (Server.rooms.size() != 0 && Server.rooms != null) {
            copyRooms = Server.rooms;
            Collections.shuffle(copyRooms);
            if (Server.rooms.size() < 10)
                rooms = copyRooms.subList(0, Server.rooms.size());
            else
                rooms = copyRooms.subList(0, 10);
        }

        root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        Label roomLabel = new Label("Rooms:");
        VBox.setMargin(roomLabel, new Insets(0, 0, 10, 0));

        TextField searchField = new TextField();
        searchField.setPromptText("Enter Room ID");
        searchField.setMaxWidth(400);
        searchField.setAlignment(Pos.CENTER);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String roomId = searchField.getText();
            if (!roomId.isEmpty()) {
                Room room = findRoomById(roomId);
                if (room != null)
                    showAlert(Alert.AlertType.INFORMATION, "Room Found", "Room found: " + room.getEntryId());
                else
                    showAlert(Alert.AlertType.WARNING, "Room Not Found", "Room not found!");
            }
        });

        HBox searchBox = new HBox(searchField, searchButton);
        searchBox.setSpacing(10);
        searchBox.setAlignment(Pos.CENTER);
        VBox.setMargin(searchBox, new Insets(0, 0, 10, 0));

        Button joinRoomButton = new Button("Join Room");
        joinRoomButton.setOnAction(event -> {
            showAlert(Alert.AlertType.INFORMATION, "Join Room", "Joining the selected room!");
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> {
            if (Server.rooms.size() != 0 && Server.rooms != null) {
                Collections.shuffle(copyRooms);
                if (Server.rooms.size() < 10)
                    rooms = copyRooms.subList(0, Server.rooms.size());
                else
                    rooms = copyRooms.subList(0, 10);
            }

            showAlert(Alert.AlertType.INFORMATION, "Refresh", "Room list refreshed!");
            updateRoomListUI();
        });

        Button createRoomButton = new Button("Create Room");
        createRoomButton.setOnAction(event -> {
            String command = "new room";
            Client client = new Client();
            client.setPlayer(this.player);
            client.start();
            client.sendCommand(command);
        });

        root.getChildren().addAll(roomLabel, searchBox, joinRoomButton, refreshButton, createRoomButton);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Game Lobby");
        stage.show();
    }

    private Room findRoomById(String roomId) {
        for (Room room : Server.rooms)
            if (room.getEntryId().equals(roomId))
                return room;

        return null;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void updateRoomListUI() {
        VBox roomList = new VBox();
        roomList.setSpacing(5);

        if (rooms != null)
            for (Room room : rooms) {
                Label roomLabel = new Label("Room ID: " + room.getEntryId());
                Button joinButton = new Button("Join");
                joinButton.setOnAction(event -> {
                    showAlert(Alert.AlertType.INFORMATION, "Join Room", "Joining the selected room!");
                });

                HBox roomRow = new HBox(roomLabel, joinButton);
                roomRow.setSpacing(10);
                roomList.getChildren().add(roomRow);
            }


        root.getChildren().remove(0);
        root.getChildren().add(0, roomList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
