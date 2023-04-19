package control;

import model.government.building.Building;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

public class BuildingControl {
    public static BuildingMessage dropBuilding(int x, int y, String type) {
        return null;
    }
    private static boolean isAppropriateCoordinate(int x, int y) {
        return false;
    }

    private static boolean isAppropriateGround(String ground, String type) {
        return false;
    }

    private static boolean isAnotherBuilding(int x, int y) {
        return false;
    }

    public static BuildingMessage selectBuilding(int x, int y) {
        return null;
    }

    private static boolean isBuildingForCurrentUser(Building building) {
        return false;
    }
    public static BuildingMessage createUnit(String type, int count) {
        return null;
    }
    public static BuildingMessage repair() {
        return null;
    }

    private static boolean isEnoughStone() {
        return false;
    }
    private static boolean IsNearEnemyForce(Building building) {
        return false;
    }
}
