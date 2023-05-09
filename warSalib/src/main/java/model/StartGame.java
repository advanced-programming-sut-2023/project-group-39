package model;

import model.government.Government;
import model.government.building.Building;
import model.map.GameMap;
import model.user.User;

import java.util.ArrayList;

public class StartGame {
    //need to have current user
    private  User currentUser;
    private  User turnedUserForGame;
    //governments need to we have all player government
    private  GameMap mapInGame;

    private User GameStarter;
    private  Building selectedBuilding;
    //how many player plays in game
    private  ArrayList <User> players = new ArrayList<>();

    public User getCurrentUser() {
        return currentUser;
    }

    public User getTurnedUserForGame() {
        return turnedUserForGame;
    }

    public void setTurnedUserForGame(User turnedUserForGame) {
        this.turnedUserForGame = turnedUserForGame;
    }

    public GameMap getMapInGame() {
        return mapInGame;
    }

    public void setMapInGame(GameMap mapInGame) {
        this.mapInGame = mapInGame;
    }

    public User getGameStarter() {
        return GameStarter;
    }

    public void setGameStarter(User gameStarter) {
        GameStarter = gameStarter;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public StartGame(ArrayList<User> gamePlayers,GameMap mapInGame) {
        this.players=gamePlayers;
        this.mapInGame=mapInGame;
    }
}
