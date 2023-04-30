package model.map;

public class GameMap {

    //we have three template of map for game

    private Tile [][] map = new Tile [400][400];


    public Tile [][] getMapAroundCoordinate(int x, int y) {
        return null;
    }

    public Tile getTileWithItsCoordinate(int x, int y) {
        return null;
    }

    public Tile [][] getRectangleOfTileWithCoordinates(int x, int y, int x1, int y1){
        return null;
    }
    public boolean haveBuildingsAround (String name, int x , int y) {
        if (map[x-1][y].getBuilding().getName().equals(name))
            return true;
        if (map[x+1][y].getBuilding().getName().equals(name))
            return true;
        if (map[x][y-1].getBuilding().getName().equals(name))
            return true;
        if (map[x][y+1].getBuilding().getName().equals(name))
            return true;
        return false;
    }

    public Tile[][] getMap() {
        return map;
    }
}

