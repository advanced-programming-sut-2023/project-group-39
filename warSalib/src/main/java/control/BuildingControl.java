package control;

import model.Game;
import model.government.Government;
import model.government.building.Building;
import model.government.people.units.UnitsName;
import model.government.resource.Resource;
import model.map.type.Type;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

import java.util.HashMap;

public class BuildingControl {
    public static BuildingMessage dropBuilding(int x, int y, String name) {
        if (!isAppropriateCoordinate(x, y))
            return BuildingMessage.WRONG_AMOUNT;
        else if (isAnotherBuilding(x, y))
            return BuildingMessage.EXIST;
        else if (!isAppropriateGround(x, y, name))
            return BuildingMessage.BAD_GROUND;
        Building building = Building.makeBuildingByName(name, x, y, Game.getTurnedUserForGame().getUserGovernment());
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
        if (getUnitNameByType(type) == null)
            return BuildingMessage.NOT_EXIST_UNIT;
        if (!hasEnoughWeaponAndGold(type))
            return BuildingMessage.NOT_ENOUGH_SOURCE;
        if (!isRelatedBuilding(type))
            return BuildingMessage.NOT_APPROPRIATE_UNIT;

        return BuildingMessage.SUCCESS;
    }
    public static BuildingMessage repair() {
        if (Game.getSelectedBuilding().getType() != "castle building")
            return BuildingMessage.NOT_GOOD_BUILDING;
        if (Game.getSelectedBuilding().getHp() == Game.getSelectedBuilding().getMaxHP())
            return BuildingMessage.HAS_FULL_HP;
        if (!isEnoughStone())
            return BuildingMessage.NOT_ENOUGH_STONE;
        if (Game.getSelectedBuilding().isNearEnemy())
            return BuildingMessage.NEAR_ENEMY;
            //TODO :delete resource in stock pile
        Game.getSelectedBuilding().setHp(Game.getSelectedBuilding().getMaxHP());
            return BuildingMessage.SUCCESS;
    }

    private static boolean isRelatedBuilding(String type) {
        for (UnitsName unitsName : UnitsName.values()) {
            if (unitsName.getName().equals(type)) {
                if (Game.getSelectedBuilding().getName().equals(unitsName.getBuilding()))
                    return true;
            }
        }
        return false;
    }

    private static UnitsName getUnitNameByType(String type) {
        for (UnitsName unitsName:UnitsName.values()) {
            if (unitsName.getName().equals(type))
                return unitsName;
        }
        return null;
    }
    private static boolean hasEnoughWeaponAndGold(String type) {
        UnitsName unitsName = getUnitNameByType(type);
        if (unitsName==null)
            return false;
        HashMap <Resource, Integer> resourceNeedUnit = new HashMap<>();
        resourceNeedUnit.put(Resource.COIN, unitsName.getCost());
        //TODO : add weapon
        return Game.getSelectedBuilding().getGovernment().hasEnoughResources(resourceNeedUnit);
    }

    private static boolean isEnoughStone() {
        return true;
    }
}
