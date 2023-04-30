package control;

import model.Game;
import model.government.people.People;
import model.government.people.units.State;
import model.government.people.units.Units;
import model.government.people.units.UnitsType;
import model.map.Tile;
import model.map.GameMap;
import view.GameMenu;
import view.enums.messages.GameMenuMessage;

import java.util.regex.Matcher;
import model.map.*;
public class GameControl {
    public static Units currentUnit;
    public static GameMenuMessage selectUnit(int x, int y,String name) {
        if(x>400||y>400||x<0||y<0){
            return GameMenuMessage.WRONG_AMOUNT;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
        for(People people:tile.getPeopleOnTile()){
            if(people instanceof Units){
                if(((Units) people).getUnitsName().getName().equals(name)&&people.getOwnerPerson().equals(Game.getCurrentUser())){
                    currentUnit= (Units) people;
                    return GameMenuMessage.SUCCESS;
                }
                return GameMenuMessage.WITHOUTUNIT;
            }
        }


        return null;

    }

    public static GameMenuMessage moveUnit(int x, int y) {
    }

    public static GameMenuMessage patrolUnit(int x1, int y1,int x2,int y2) {

        return null;
    }

    public static GameMenuMessage setMode(int x, int y, State state) {
        if (x > 400 || y > 400 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
        for (People people : tile.getPeopleOnTile()) {
            if (people instanceof Units&&people.getOwnerPerson().equals(Game.getCurrentUser())) {
                ((Units) people).setState(state);
            }
        }
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage attack(int x, int y) {
        if (x > 400 || y > 400 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        Tile tile=Game.getMapInGame().getMap()[x][y];
        return null;

    }

    public static GameMenuMessage airAttack(int x, int y) {
        return null;

    }

    public static GameMenuMessage pourOil(String direction) {
        return null;

    }
    public static GameMenuMessage specialMoveUnit(int x,int y){
        return null;
    }

    public static GameMenuMessage digTunnel(int x, int y) {
        return null;

    }

    public static GameMenuMessage build(String equipmentName) {
        return null;
    }

    public static GameMenuMessage disbandUnit() {
        return null;
    }

    public static GameMenuMessage makeGate(String direction) {    //faghat ye ghale dare har hokoomat?
        return null;

    }

    public static GameMenuMessage makeWall(int x, int y, int width) {
        return null;

    }

    public static GameMenuMessage makeSmallTower() {
        return null;

    }
    public static GameMenuMessage makeTower(String type) {
        return null;

    }


    public static GameMenuMessage makeKillerTale(int x, int y) {
        return null;

    }
    public static GameMenuMessage makeOilTale(int x, int y) {
        return null;

    }

    public static GameMenuMessage makeStair(int x, int y) {
        return null;

    }

    public static GameMenuMessage diggingDitch(int x, int y) {
        return null;

    }
    public static GameMenuMessage removeDitch(int x,int y){
        return null;
    }
    public static GameMenuMessage stopDitch(int x,int y){
        return null;
    }

    private static GameMenuMessage burningOil() {

        return null;
    }

    public static GameMenuMessage captureGate() {

        return null;
    }
    public static GameMenuMessage openGate(){
        return null;

    }

    public static GameMenuMessage makeProtection() {
        return null;

    }

    public static GameMenuMessage batteringRam() {
        return null;

    }

    public static GameMenuMessage makeCatapult() {
        return null;

    }

    public static GameMenuMessage stoneTower(int x, int y) {
        return null;
    }

    public static GameMenuMessage fillingDitch(int x, int y) {
        return null;
    }

}
