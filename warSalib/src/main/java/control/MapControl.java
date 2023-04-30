package control;

import model.Game;
import model.government.building.Building;
import model.government.building.group.GroupOfBuilding;
import model.map.Tile;

public class MapControl {

    public static String showMap(int x, int y) {
        String result = "";
        char c;
        Tile [][] smallMap = Game.getMapInGame().getMapAroundCoordinate(x, y);
        for (int j = 0 ; j < 10; j++) {
            for (int i = 0; i< 10; i++) {
                if (smallMap[j][i] == null)
                    continue;
                else if ((c = hasBuilding(smallMap, x, y) )!= 'N') {
                    result += " " + c;
                }
                else if (smallMap [j][i].getTree() != null) {
                    result += " T";
                }
                else result += " " + smallMap[j][i].getType().getName();
            }
        }
        return result;
    }

    public static String moveMap(int up, int down, int right, int left) {
        return null;
    }

    public static String showDetails(int x, int y) {
        return null;
    }

    private static boolean validCoordinates(int x, int y) {
        if (x>=0 && x<200 && y >=0 && y<200)
            return true;
        return false;
    }

    private static char hasBuilding(Tile [][] smallMap , int x, int y) {
                if (smallMap[y][x] == null)
                    return 'N';
                if (smallMap[y][x].getBuilding() != null) {
                    Building building = smallMap[y][x].getBuilding();
                    GroupOfBuilding group = Building.getGroupByType(building.getName());
                    if (group == GroupOfBuilding.WALL || group == GroupOfBuilding.GATEHOUSE ||
                            group == GroupOfBuilding.TOWER)
                        return 'W';
                    return 'B';
                }
        return 'N';
    }
    private static boolean validDirection(String[] direction) {
        return false;
    }
}
