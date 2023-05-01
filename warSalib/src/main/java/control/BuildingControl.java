package control;

import model.Game;
import model.government.building.Building;
import model.map.type.Type;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

public class BuildingControl {
    public static BuildingMessage dropBuilding(int x, int y, String name) {
        if (!isAppropriateCoordinate(x, y))
            return BuildingMessage.WRONG_AMOUNT;
        else if (isAnotherBuilding(x, y))
            return BuildingMessage.EXIST;
        else if (!isAppropriateGround(x, y, name))
            return BuildingMessage.BAD_GROUND;
        Building building = Building.makeBuildingByName(name, x, y, Game.getTurnedUserForGame().getUserGovernment());
        //TODO : add territory
        if (building == null)
            return BuildingMessage.NOT_ENOUGH_SOURCE;
        return BuildingMessage.SUCCESS;
    }
    private static boolean isAppropriateCoordinate(int x, int y) {
        return x>=0 && x<200 && y>=0 && y<200;
    }

    private static boolean isAppropriateGround(int x,int y, String name) {
        return Building.isAppropriateGround(Game.getMapInGame().getMap()[y][x].getType(), name);
    }

    private static boolean isAnotherBuilding(int x, int y) {
        if (Game.getMapInGame().getMap()[y][x].getBuilding() != null)
            return true;
        return false;
    }

    public static BuildingMessage selectBuilding(int x, int y) {
        if (!isAppropriateCoordinate(x, y))
            return BuildingMessage.WRONG_AMOUNT;
        if (!isAnotherBuilding(x, y))
            return BuildingMessage.NOT_EXIST;
        if (isBuildingForCurrentUser(x, y))
            return BuildingMessage.NOT_BELONG_TO_YOU;
        Game.setSelectedBuilding(Game.getMapInGame().getMap()[y][x].getBuilding());
        return BuildingMessage.SUCCESS;
    }

    private static boolean isBuildingForCurrentUser(int x, int y) {
        if (Game.getMapInGame().getMap()[y][x].getBuilding() == null)
            return false;
        return Game.getMapInGame().getMap()[y][x].getBuilding().getGovernment() == Game.getCurrentUser().getUserGovernment();
    }
    public static BuildingMessage createUnit(String type, int count) {
        if (count <= 0)
            return BuildingMessage.WRONG_AMOUNT;
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
