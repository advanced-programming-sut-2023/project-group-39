package control;

import model.Game;
import model.government.building.Building;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

public class BuildingControl {
    public static BuildingMessage dropBuilding(int x, int y, String type) {
        if (!isAppropriateCoordinate(x, y))
            return BuildingMessage.WRONG_AMOUNT;
        else if (isAnotherBuilding(x, y))
            return BuildingMessage.EXIST;

        return BuildingMessage.SUCCESS;
    }
    private static boolean isAppropriateCoordinate(int x, int y) {
        return x>=0 && x<200 && y>=0 && y<200;
    }

    private static boolean isAppropriateGround(String ground, String type) {
        return false;
    }

    private static boolean isAnotherBuilding(int x, int y) {
        if (Game.getMapInGame().getMap()[y][x].getBuilding() != null)
            return false;
        return true;
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
