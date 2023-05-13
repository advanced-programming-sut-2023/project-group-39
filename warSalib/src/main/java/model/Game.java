package model;

import model.government.Government;
import model.government.building.Building;
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

    private static User GameStarter;
    private static Building selectedBuilding;
    private static ArrayList<Government> governments = new ArrayList<>();
    //how many player plays in game
    private static ArrayList <User> players = new ArrayList<>();

    public static ArrayList<Government> getGovernments() {
        return governments;
    }

    private static ArrayList<User> playersInGame=new ArrayList<>();
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

    public static ArrayList<User> getPlayersInGame() {
        return playersInGame;
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

    public static void setGameStarter(User gameStarter) {
        GameStarter = gameStarter;
    }

    public static User getGameStarter() {
        return GameStarter;
    }

    public static void setPlayers(ArrayList<User> players) {
        Game.players = players;
    }

}
