package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.resource.Resource;
import model.map.type.Type;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class Building {

    private boolean isNearEnemy = true;
    private Government government;
    private HashMap<String, Integer> workerDataBase;
    private ArrayList<People> workerWorked;

    private HashMap<Resource, Integer> resourceNeedToBuild;
    private int hp;
    private String type;

    private String name;
    private int maxHP;
    private int x, y;

    public Building(int x, int y, Government government, int hp, String type, String name, int maxHP,
                    HashMap<Resource, Integer> resourceNeedToBuild) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.type = type;
        this.government = government;
        this.name = name;
        workerDataBase = new HashMap<>();
        workerWorked = new ArrayList<>();
        this.maxHP = maxHP;
        this.resourceNeedToBuild = resourceNeedToBuild;
    }

    public static GroupOfBuilding getGroupByName(String name) {
        GroupOfBuilding[] groups = GroupOfBuilding.values();
        for (GroupOfBuilding group : groups) {
            for (String instance : group.getGroup()) {
                if (name.equals(instance))
                    return group;
            }
        }
        return null;
    }

    public static Building makeBuildingByName(String name, int x, int y, Government government) {
        GroupOfBuilding group = getGroupByName(name);
        if (group == null)
            return null;
        if (group.equals(GroupOfBuilding.CONVERTED_BUILDING))
            return ConvertedBuilding.makeConvertedBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.CAGED_WAR_DOG))
            return CagedWarDog.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.CHURCH))
            return Church.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.DRAWBRIDGE))
            return DrawBridge.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.GATEHOUSE))
            return Gatehouse.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.HOVEL))
            return Hovel.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.INN))
            return Inn.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.KILLING_PIT))
            return KillingPit.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.MAKING_FORCE_BUILDING))
            return MakingForceBuilding.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.MARKET))
            return Market.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.MINE_BUILDING))
            return MineBuilding.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.OX_TETHER))
            return OxTether.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.PITCH_DITCH))
            return PitchDitch.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.PRODUCTIVE_BUILDING))
            return ProductiveBuilding.makeProductiveBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.SIEGE_TENT))
            return SiegeTent.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.STOCK_PILE_BUILDING))
            return StockPileBuilding.makeBuildingByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.TOWER))
            return Tower.makeTowerByName(name, x, y, government);

        else if (group.equals(GroupOfBuilding.WALL))
            return Wall.makeWallByName(name, x, y, government);
        else return null;
    }

    public void addWorker(People people) {
        workerWorked.add(people);
    }


    public boolean IsVisibleBuilding() {
        return true;
    }

    public Government getGovernment() {
        return government;
    }

    public int getHp() {
        return hp;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public HashMap<String, Integer> getWorkerDataBase() {
        return workerDataBase;
    }

    public ArrayList<People> getWorkerWorked()
    {
        return workerWorked;
    }

    public void setWorkerDataBase(String people, int number) {
        workerDataBase.put(people, number);
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public boolean isNearEnemy() {
        return isNearEnemy;
    }

    public void setNearEnemy(boolean nearEnemy) {
        isNearEnemy = nearEnemy;
    }

    public HashMap<Resource, Integer> getResourceNeedToBuild() {
        return resourceNeedToBuild;
    }

    public static boolean isAppropriateGround(Type type, String name) {
        if (!type.getPermeability())
            return false;
        if (name.equals("quarry") && !(type.equals(Type.SLATE)))
            return false;
        if (name.equals("iron mine") && !(type.equals(Type.IRON_GROUND)))
            return false;
        if (name.equals("pitch rig") && !(type.equals(Type.PLAIN)))
            return false;
        if ((name.equals("apple garden") || name.equals("barley field") || name.equals("wheat field")) &&
                !(type.equals(Type.GRASS) || type.equals(Type.DENSE_GRASSLAND)))
            return false;
        return true;
    }
}
