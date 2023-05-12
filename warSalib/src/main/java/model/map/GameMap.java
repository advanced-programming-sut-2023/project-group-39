package model.map;

import model.Game;
import model.government.Government;
import model.government.building.Keep;

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
        Game.setMapInGame(this);
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
                if(i == rowDown + 24 && j == colDown + 24) {
                    Keep keep = new Keep(i, j, government, "keep");
                    map[j][i].setBuilding(keep);
                    government.addBuilding(keep);
                }
            }
        }
    }

    public Tile [][] getMapAroundCoordinate(int x, int y) {
        Tile [][] shownMap = new Tile[10][10];
        int x1 = x - 9;
        int y1 = y + 10;
        if (x1 < 0)
            x1 = 0;
        if (y1 > 200)
            y1 = 200;
        for (int j = y; j < y1; j++) {
            for (int i = x1; i <= x; i++) {
                shownMap[j-y][i-x1] = map[j][i];
            }
        }
        return shownMap;
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
