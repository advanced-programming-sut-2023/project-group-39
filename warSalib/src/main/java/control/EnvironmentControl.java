package control;

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
        else if (!validType(type))
            return EnvironmentMenuMessage.WRONG_TYPE;

        return EnvironmentMenuMessage.SUCCESS;
    }

    public static EnvironmentMenuMessage setTextureWithRectangle(int x, int y, int x1, int y1, String type) {
        return null;
    }

    private static boolean validType(String type) {
        for (Type type1: Type.values()) {
            if (type1.getName().equals(type))
                return true;
        }
        return false;
    }
    private static boolean validCoordinate(int x, int y) {
        return x>=0 && x <200 && y>=0 && y<200;
    }

    //if you want check one tile --> x = x1 , y = y1
    private static boolean hasBuilding(int x, int y, int x1, int y1) {
        return false;
    }

    public static EnvironmentMenuMessage clearTile(int x, int y) {
        return null;
    }

    public static EnvironmentMenuMessage dropRock(int x, int y, String direction) {
        return null;
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
        return false;
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

    public static EnvironmentMenuMessage setKeep(String color) {
        return null;
    }
}
