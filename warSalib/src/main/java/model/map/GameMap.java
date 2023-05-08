package model.map;

import model.Game;
import model.government.Government;

public class GameMap {

   private int selectedX, selectedY;

    private Tile [][] map;

    public GameMap() {
        this.map = new Tile[200][200];
        for (int i = 0 ; i<200 ; i++) {
            for (int j = 0; j<200; j++) {
                this.map[i][j] = new Tile();
            }
        }
        for (int i = 0; i < Game.getGovernments().size(); i++) {
            defineTerritory(i, Game.getGovernments().get(i), this.map);
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public void defineTerritory (int a , Government government, Tile[][] map) {
        int rowDown = 0;
        int colDown = 0;
        if (a == 0) {
            rowDown = 0;
            colDown = 150;
        }
        else if (a == 1) {
            rowDown = 100;
            colDown = 50;
        }
        else if (a == 2) {
            rowDown = 0;
            colDown = 50;
        }
        else if (a == 3) {
            rowDown = 100;
            colDown = 150;
        }
        else if (a == 4) {
            rowDown = 150;
            colDown = 0;
        }
        else if (a == 5) {
            rowDown = 50;
            colDown = 100;
        }
        else if (a == 6) {
            rowDown = 50;
            colDown = 0;
        }
        else if (a == 7) {
            rowDown = 150;
            colDown = 100;
        }
        for (int j = colDown; j < colDown + 50; j++) {
            for (int i = rowDown; i< rowDown + 50; i++) {
                map[j][i].setGovernment(government);
            }
        }
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

    public int getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }
}
