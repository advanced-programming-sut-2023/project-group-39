package control;

import model.Game;
import model.government.building.Building;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.units.Units;
import model.map.GameMap;
import model.map.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class MapControl {

    public static String showMap(int x, int y) {
        String result = "";
        if (!validCoordinates(x, y)) {
            return result;
        }
        char c;
        Tile[][] smallMap = Game.getMapInGame().getMapAroundCoordinate(x, y);
        Game.getMapInGame().setSelectedX(x);
        Game.getMapInGame().setSelectedY(y);
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                if (smallMap[j][i] == null)
                    continue;
                else if (hasUnits(smallMap[j][i].getPeopleOnTile())) {
                    result += " S";
                }
                else if ((c = hasBuilding(smallMap, x, y)) != 'N') {
                    result += " " + c;
                } else if (smallMap[j][i].getTree() != null) {
                    result += " T";
                } else result += " " + smallMap[j][i].getType().getName();
            }
        }
        return result;
    }

    public static String moveMap(int up, int down, int right, int left) {
        int x = Game.getMapInGame().getSelectedX();
        int y = Game.getMapInGame().getSelectedY();
        return showMap(x - left + right , y - down + up);
    }

    public static String showDetails(int x, int y) {
        String result = "";
        if (!validCoordinates(x, y)) {
            return result;
        }
        GameMap map = Game.getMapInGame();
        Tile tile = map.getMap()[y][x];
        map.setSelectedY(y);
        map.setSelectedX(x);
        result += "***----------ground-----------***\n";
        result += tile.getType() + "\n";
        if (tile.getResource() != null) {
            result += "***----------resource-----------***\n";
            result += tile.getResource() + "\n";
        }
        if (hasUnits(tile.getPeopleOnTile())) {
            result += "***----------units-----------***\n";
            HashMap <String, Integer> unitsPeople = countUnitsInHashMap(tile.getPeopleOnTile());
            for (String nameOfTroop : unitsPeople.keySet()) {
                result += nameOfTroop + "   " + unitsPeople.get(nameOfTroop);
            }
        }
        if (tile.getBuilding() != null) {
            result += "***----------building-----------***\n";
            result += tile.getBuilding().getName() + "\n";
        }
        return result;
    }

    private static boolean validCoordinates(int x, int y) {
        if (x >= 0 && x < 200 && y >= 0 && y < 200)
            return true;
        return false;
    }

    private static char hasBuilding(Tile[][] smallMap, int x, int y) {
        if (smallMap[y][x] == null)
            return 'N';
        if (smallMap[y][x].getBuilding() != null) {
            Building building = smallMap[y][x].getBuilding();
            GroupOfBuilding group = Building.getGroupByName(building.getName());
            if (group == GroupOfBuilding.WALL || group == GroupOfBuilding.GATEHOUSE ||
                    group == GroupOfBuilding.TOWER)
                return 'W';
            return 'B';
        }
        return 'N';
    }

    private static HashMap<String, Integer> countUnitsInHashMap (ArrayList<People> people) {
        HashMap <String,Integer> units = new HashMap<>();
        int num;
        for (People people1:people) {
            if (people1 instanceof Units) {
                if (units.containsKey(((Units) people1).getUnitsName().getName())) {
                    num = units.get(((Units) people1).getUnitsName().getName()) + 1;
                    units.replace(((Units) people1).getUnitsName().getName(), num);
                }
                else units.put(((Units) people1).getUnitsName().getName(), 1);
            }
        }
        return units;
    }

    private static boolean hasUnits(ArrayList<People> people) {
        if (people.size() == 0)
            return false;
        for (People subPeople: people) {
            if (subPeople instanceof Units)
                return true;
        }
        return false;
    }
    private static boolean validDirection(String[] direction) {
        return false;
    }
}
