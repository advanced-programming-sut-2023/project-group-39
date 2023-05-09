package control;

import model.Game;
import model.government.Government;
import model.government.building.Building;
import model.government.building.CagedWarDog;
import model.government.building.StockPileBuilding;
import model.government.people.People;
import model.government.people.units.State;
import model.government.people.units.Units;
import model.government.people.units.UnitsName;
import model.government.resource.Resource;
import model.map.type.Type;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

import javax.swing.plaf.ButtonUI;
import java.util.HashMap;

public class BuildingControl {
    public static BuildingMessage dropBuilding(int x, int y, String name) {
        Game.setSelectedBuilding(null);
        if (!isAppropriateCoordinate(x, y))
            return BuildingMessage.WRONG_AMOUNT;
        else if (isAnotherBuilding(x, y))
            return BuildingMessage.EXIST;
        else if (!isAppropriateGround(x, y, name))
            return BuildingMessage.BAD_GROUND;
        Building building = Building.makeBuildingByName(name, x, y, Game.getTurnedUserForGame().getUserGovernment(), 0);
        if (building == null)
            return BuildingMessage.NOT_ENOUGH_SOURCE;
        Game.getTurnedUserForGame().getUserGovernment().addBuilding(building);
        if (building instanceof StockPileBuilding)
            building.getGovernment().addStockPile((StockPileBuilding) building);
        if (!building.getResourceNeedToBuild().isEmpty()) {
            for (Resource resource : building.getResourceNeedToBuild().keySet()) {
                building.getGovernment().removeFromResources(resource , building.getResourceNeedToBuild().get(resource));
                building.getGovernment().removeResourceFromStockPile(resource, building.getResourceNeedToBuild().get(resource));
            }
        }
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
        Game.setSelectedBuilding(null);
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
        if (Game.getSelectedBuilding().getGovernment().getUnWorkedPeople().size() < count)
            return BuildingMessage.NOT_ENOUGH_POPULATION;
        UnitsName unitsName = getUnitNameByType(type);
        Building building = Game.getSelectedBuilding();
        Government government = building.getGovernment();
        for (int i = 0; i < count; i++) {
            Units units = new Units(building.getX(), building.getY(), unitsName, building.getGovernment().getUser());
            People people = government.getUnWorkedPeople().get(0);
            government.removeUnWorkedPeople(people);
            government.addToPeople(units);
            Game.getMapInGame().getMap()[building.getY()][building.getX()].addPeople(units);
        }
        return BuildingMessage.SUCCESS;
    }
    public static BuildingMessage repair() {
        if (Game.getSelectedBuilding().getType() != "castle building")
            return BuildingMessage.NOT_GOOD_BUILDING;
        if (Game.getSelectedBuilding().getHp() == Game.getSelectedBuilding().getMaxHP())
            return BuildingMessage.HAS_FULL_HP;
        int count;
        if ( (count =isEnoughStone(Game.getSelectedBuilding())) == -1)
            return BuildingMessage.NOT_ENOUGH_STONE;
        if (isNearEnemy(Game.getSelectedBuilding()))
            return BuildingMessage.NEAR_ENEMY;
        Game.getSelectedBuilding().setHp(Game.getSelectedBuilding().getMaxHP());
        Game.getSelectedBuilding().getGovernment().removeResourceFromStockPile(Resource.STONE, count);
        Game.getSelectedBuilding().getGovernment().removeFromResources(Resource.STONE, count);
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

    private static boolean isNearEnemy(Building building) {
        int x = building.getX();
        int y = building.getY();
        for (int j = y -2; j <= y+2; j++) {
            for (int i = x - 2; i <= x+2; i++) {
                for(People people : Game.getMapInGame().getMap()[j][i].getPeopleOnTile()) {
                    if (!people.getOwnerPerson().equals(building.getGovernment().getUser()))
                        return true;
                }
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

    private static int isEnoughStone(Building building) {
        if (building.getResourceNeedToBuild().containsKey(Resource.STONE)) {
            int stoneCount = building.getResourceNeedToBuild().get(Resource.STONE) / 2;
            HashMap<Resource, Integer> stoneResource = new HashMap<>();
            stoneResource.put(Resource.STONE, stoneCount);
            if (building.getGovernment().hasEnoughResources(stoneResource))
                return stoneCount;
        }
        return -1;
    }

    public static BuildingMessage openCagedDog(String state) {
        if (!state.equals("open") && !state.equals("close"))
            return BuildingMessage.WRONG_AMOUNT;
        if (!(Game.getSelectedBuilding() instanceof CagedWarDog))
            return BuildingMessage.NOT_GOOD_BUILDING;
        if (state.equals("open"))
            ((CagedWarDog) Game.getSelectedBuilding()).openDoor(true);
        if (state.equals("close"))
            ((CagedWarDog) Game.getSelectedBuilding()).openDoor(false);
        return BuildingMessage.SUCCESS;
    }
}
