package model;

import model.government.Government;
import model.government.building.Building;
import model.map.GameMap;
import model.map.GameMap;
import model.user.User;
import view.enums.commands.BuildingCommands;

import java.util.ArrayList;
import java.util.Map;

public class Game {
    //need to have current user
    private static User currentUser;
    private static User turnedUserForGame;
    //governments need to we have all player government
    private static GameMap mapInGame;
    private static Building selectedBuilding;
    private static ArrayList<Government> governments = new ArrayList<>();
    //how many player plays in game
    private static ArrayList <User> players = new ArrayList<>();

    public static ArrayList<Government> getGovernments() {
        return governments;
    }
    public static void addGovernment (Government government) {
        governments.add(government);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Game.currentUser = currentUser;
    }

    public static ArrayList<User> getPlayers() {
        return players;
    }

    public static void addPlayer(User user) {
        players.add(user);
    }

    public static GameMap getMapInGame() {
        return mapInGame;
    }

    public static void setMapInGame(GameMap mapInGame) {
        Game.mapInGame = mapInGame;
    }

    public static User getTurnedUserForGame() {
        return turnedUserForGame;
    }

    public static void setTurnedUserForGame(User turnedUserForGame) {
        Game.turnedUserForGame = turnedUserForGame;
    }

    public static Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public static void setSelectedBuilding(Building selectedBuilding) {
        Game.selectedBuilding = selectedBuilding;
    }
}
