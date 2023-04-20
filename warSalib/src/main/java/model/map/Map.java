package main.java.model.map;

import java.util.ArrayList;

public enum Map {
    ;
    //we have three template of map for game

    private Tile [][] map;

    Map(Tile[][] map) {
        this.map = map;
    }

    public Tile [][] getMapAroundCoordinate(int x, int y) {
        return null;
    }

    public Tile getTileWithItsCoordinate(int x, int y) {
        return null;
    }

    public Tile [][] getRectangleOfTileWithCoordinates(int x, int y, int x1, int y1){
        return null;
    }
}
