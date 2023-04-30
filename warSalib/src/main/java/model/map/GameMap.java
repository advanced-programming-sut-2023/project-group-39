package model.map;

public class GameMap {

    //we have three template of map for game

    private Tile [][] map;

    public GameMap() {
        this.map = new Tile[200][200];
        for (int i = 0 ; i<200 ; i++) {
            for (int j = 0; j<200; j++) {
                map[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public Tile [][] getMapAroundCoordinate(int x, int y) {
        Tile [][] shownMap = new Tile[10][10];
        addToShownMap(x, y+1, x-4, y+5, shownMap, "up left");
        addToShownMap(x+1, y+1, x+5, y+5, shownMap, "up right");
        addToShownMap(x, y, x-4, y-4, shownMap, "down left");
        addToShownMap(x+1 , y, x+5, y-4, shownMap, "down right");
        return shownMap;
    }

    private void addToShownMap(int x, int y, int x1, int y1, Tile[][] shownMap, String direction) {
        int i,j;
        if (x1 < 0 ) {
            x1 = 0;
        }
        if (y1 < 0 ) {
            y1 = 0;
        }
        if (y1 >= 200) {
            y1 = 199;
        }
        if (x1 >= 200) {
            x1 = 199;
        }
        if (direction.equals("up left")) {
            for (i = x1; i <= x; i++) {
                for (j = y; j <= y1; j++) {
                    if (y>=5)
                    shownMap[j-y+5][i-x1] = map[j][i];
                    else shownMap[j][i-x1]= map[j][i];
                }
            }
        }
        if (direction.equals("up right")) {
            for (i = x; i <= x1; i++) {
                for (j = y; j <= y1; j++) {
                    if (y>=5 && x>=5)
                    shownMap[j-y+5][i-x1+5] = map[j][i];
                    else if (y>=5 && x<5)
                        shownMap[j-y+5][i] = map[j][i];
                    else if (y<5 && x>=5)
                        shownMap[j][i-x1+5] = map[j][i];
                    else shownMap[j][i]= map[j][i];
                }
            }
        }
        if (direction.equals("down left")) {
            for (i = x1; i <= x; i++) {
                for (j = y1; j <= y; j++) {
                    shownMap[j-y][i-x1] = map[j][i];
                }
            }
        }
        if (direction.equals("down right")) {
            for (i = x; i <= x1; i++) {
                for (j = y1; j <= y; j++) {
                    if (x>=5)
                    shownMap[j-y][i-x1+5] = map[j][i];
                    else shownMap[j-y][i] = map[j][i];
                }
            }
        }
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
}
