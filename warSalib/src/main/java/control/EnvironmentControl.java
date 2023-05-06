package control;

import model.Game;
import model.map.type.Type;
import view.enums.messages.EnvironmentMenuMessage;

public class EnvironmentControl {
    public static EnvironmentMenuMessage chooseMap(int number) {
        return null;
    }

    private static boolean validNumberOfMap(int number) {
        return false;
    }

    public static EnvironmentMenuMessage setTexture(int x, int y, String type) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (getTypeByName(type) == null)
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (hasBuilding(x, y, x, y))
            return EnvironmentMenuMessage.EXIST_BUILDING;
        Type typeOfGround = getTypeByName(type);
        Game.getMapInGame().getMap()[y][x].setType(typeOfGround);
        return EnvironmentMenuMessage.SUCCESS;
    }

    public static EnvironmentMenuMessage setTextureWithRectangle(int x, int y, int x1, int y1, String type) {
        if (!validCoordinate(x, y) && !validCoordinate(x1, y1) && x<=x1 && y1<=y)
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (getTypeByName(type) == null)
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (hasBuilding(x, y, x1, y1))
            return EnvironmentMenuMessage.EXIST_BUILDING;
        Type typeOFGround = getTypeByName(type);
        for (int j = y1 ; j <= y; j++) {
            for (int i = x; i <= x1; i++) {
                Game.getMapInGame().getMap()[j][i].setType(typeOFGround);
            }
        }
        return EnvironmentMenuMessage.SUCCESS;
    }

    private static Type getTypeByName(String type) {
        for (Type type1: Type.values()) {
            if (type1.getName().equals(type))
                return type1;
        }
        return null;
    }
    private static boolean validCoordinate(int x, int y) {
        return x>=0 && x <200 && y>=0 && y<200;
    }

    //if you want check one tile --> x = x1 , y = y1

    public static EnvironmentMenuMessage clearTile(int x, int y) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        Game.getMapInGame().getMap()[y][x].setType(Type.GROUND);
        return EnvironmentMenuMessage.SUCCESS;
    }

    public static EnvironmentMenuMessage dropRock(int x, int y, String direction) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
    }

    public static EnvironmentMenuMessage dropTree(int x, int y, String type) {
        return null;
    }

    public static EnvironmentMenuMessage dropBuilding(int x, int y, String type) {
        return null;
    }

    public static EnvironmentMenuMessage dropUnit(int x, int y, String type, int count) {
        return null;
    }

    private static boolean validTypeOfTree(String type) {
        return false;
    }

    private static boolean validDirection(String direction) {
        
    }

    private static boolean isGroundAppropriateForTree(String type) {
        return false;
    }

    private static boolean isGroundAppropriateForBuilding(String type) {
        return false;
    }

    private static boolean isGroundAppropriateForUnit(String type) {
        return false;
    }

    public static EnvironmentMenuMessage chooseColor(String color) {
        return null;
    }
    private static boolean hasBuilding(int x, int y, int x1, int y1) {
        for (int j = y1 ; j <= y; j++) {
            for (int i = x; i<=x1; i++) {
                if (Game.getMapInGame().getMap()[j][i].getBuilding() != null)
                    return true;
            }
        }
        return false;
    }

    public static EnvironmentMenuMessage setKeep(String color) {
        return null;
    }
}
