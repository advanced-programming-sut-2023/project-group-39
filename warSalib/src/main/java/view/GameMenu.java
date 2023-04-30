package view;

import control.GameControl;
import model.government.people.units.State;
import model.government.people.units.Units;
import view.enums.messages.GameMenuMessage;

import java.util.regex.Matcher;

public class GameMenu {
    static String type;

    public static void run() {
    }

    private static void selectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        //we should have type of units and should be complete(with enum)
        GameMenuMessage message = GameControl.selectUnit(x, y, type);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SUCCESS:
                System.out.println("you drop it successfully");
                break;
            case WITHOUTUNIT:
                System.out.println("we dont have unit on this tile");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }
    }

    private static void moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        //we should handle type with enum
        GameMenuMessage message = GameControl.moveUnit(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("unit moved successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SEA_HIGHHEIGHT:
                System.out.println("unit cant move on this tiles");
                break;
            case BIGGERTHANSPEED:
                GameControl.specialMoveUnit(x, y);
                break;
            default:
                System.out.println("invalid command!!?");
                break;
        }

    }

    private static void patrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.patrolUnit(x1, y1, x2, y2);
        switch (message) {
            case SUCCESS:
                break;
            case PROBLEM:
                System.out.println("unit cant patrol between two points");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x1 or x2 or y1 or y2");
                break;
            default:
                System.out.println("invalid command!!?");
                break;
        }
    }

    private static void setMode(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        State state = State.valueOf(matcher.group("moode"));
        GameMenuMessage message = GameControl.setMode(x, y, state);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SUCCESS:
                System.out.println("moode changed successfully");
                break;
            default:
                System.out.println("invalid command");
                break;
        }
    }

    private static void attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.attack(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("you attacked successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            default:
                System.out.println("invalid command!");
                break;
        }

    }

    private static void airAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.airAttack(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("you started shooting successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("position is out of archer's position");
                break;
            default:
                System.out.println("invalid command!");
                break;

        }
    }

    private static void pourOil(Matcher matcher) {
        String direction = matcher.group("direction");
        GameMenuMessage message = GameControl.pourOil(direction);
        switch (message) {
            case SUCCESS:
                System.out.println("oil poiled successfully");
                break;
            case INVALIDDIRECTION:
                System.out.println("direction is invalid");
                break;
            case INVALIDUNIT:
                System.out.println("unit is invalid");
                break;
            default:
                System.out.println("invalid command!!?");
                break;

        }
    }

    private static void digTunnel(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.digTunnel(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("tunnel dig was successfully");
                break;
            case INVALIDUNIT:
                System.out.println("unit is invalid");
                break;
            default:
                System.out.println("invalid command!!?");
                break;
        }


    }

    private static void build(Matcher matcher) {
        String equipment = matcher.group("equipment");
        GameMenuMessage message = GameControl.build(equipment);
        switch (message) {
            case SUCCESS:
                System.out.println("equipment maked successfully");
                break;
            case WITHOUTENGINNER:
                System.out.println("we dont have any engineer");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void disbandUnit(Matcher matcher) {
        GameMenuMessage message = GameControl.disbandUnit();
        switch (message) {
            case SUCCESS:
                System.out.println("units disband was successfully");
                break;
            default:
                System.out.println("invalid command!");
                break;

        }
    }

    private static void makeGate(Matcher matcher) {
        String direction = matcher.group("direction");
        GameMenuMessage message = GameControl.makeGate(direction);
        switch (message) {
            case SUCCESS:
                System.out.println("gate with " + direction + "  created successfully");
                break;
            case INVALIDDIRECTION:
                System.out.println("direction is invalid");
                break;
            default:
                System.out.println("invalid command!");
                break;
        }
    }

    private static void makeWall(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int width = Integer.parseInt(matcher.group("width"));
        int height = Integer.parseInt(matcher.group("height"));
        GameMenuMessage message = GameControl.makeWall(x, y, width);
        switch (message) {
            case SUCCESS:
                System.out.println("wall build successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("your resource is not enough");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }

    }

    private static void makeTower(Matcher matcher) {
        String type = matcher.group("type");
        GameMenuMessage message = GameControl.makeTower(type);
        switch (message) {
            case SUCCESS:
                System.out.println("tower build successfully");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("your resource is not enough");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }

    }

    private static void makeStair(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeStair(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("tale built successfully");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }

    }

    private static void makeKillerTale(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeKillerTale(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("Killer tale built successfully");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }
    }

    private static void makeOilTale(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeOilTale(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("oil tale built successfully");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }

    }

    private static void diggingDitch(Matcher matcher) {
        //should complete with type of unit;
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.diggingDitch(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("Ditch dig was successfully");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            case INVALIDUNIT:
                System.out.println("This unit cant dig ditch");
                break;
            default:
                System.out.println("invalid command!");
                break;
        }
    }
    private static void removeDitch(Matcher matcher){
        int x=Integer.parseInt(matcher.group("x"));
        int y=Integer.parseInt(matcher.group("y"));
        GameMenuMessage message=GameControl.removeDitch(x,y);
        switch (message){
            case SUCCESS:
                System.out.println("ditch removed successfully");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            case INVALIDDITCH:
                System.out.println("we dont have ditch on this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }
    private static void stopDitch(Matcher matcher){
        int x=Integer.parseInt(matcher.group("x"));
        int y=Integer.parseInt(matcher.group("y"));
        GameMenuMessage message=GameControl.stopDitch(x,y);
        switch (message){
            case SUCCESS:
                System.out.println("stop dig successfully");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }
    }

    private static void burningOil(Matcher matcher) {

    }

    private static void captureGate(Matcher matcher) {
        GameMenuMessage message=GameControl.captureGate();
        switch (message){
            case SUCCESS:
                GameMenuMessage message1=GameControl.openGate();
                switch (message1){
                    case SUCCESS:
                        System.out.println("Gate opened successfully");
                        break;
                }

        }
    }

    private static void makeProtection(Matcher matcher) {
        //should be complete with units
        GameMenuMessage message=GameControl.makeProtection();
        switch (message){
            case SUCCESS:
                System.out.println("protection built successfully");
                break;
            case PROBLEM:
                System.out.println("can't make protection");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void batteringRam(Matcher matcher) {
        //should be complete
        GameMenuMessage message=GameControl.batteringRam();
        switch (message){
            case SUCCESS:
                System.out.println("battering was successful");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void makeCatapult(Matcher matcher) {

    }

    private static void stoneTower(Matcher matcher) {

    }

    private static void fillingDitch(Matcher matcher) {

    }
}
