package control;

import view.enums.messages.EnvironmentMenuMessage;

public class EnvironmentControl {
    public static EnvironmentMenuMessage chooseMap(int number) {
        return null;
    }

    private static boolean validNumberOfMap(int number) {
        return false;
    }

    public static EnvironmentMenuMessage setTexture(int x, int y) {
        return null;
    }

    public static EnvironmentMenuMessage setTextureWithRectangle(int x, int y, int x1, int y1) {
        return null;
    }

    private static boolean validCoordinate(int x, int y) {
        return false;
    }

    //if you want check one tile --> x = x1 , y = y1
    private static boolean hasBuilding(int x, int y, int x1, int y1) {
        return false;
    }

    public static void clearTile(int x, int y) {

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
