package main.java.model.map.rock;

import main.java.model.map.Tile;

public class Rock {

    private String direction;
    private Tile tile;

    public Rock(String direction, Tile tile) {
        this.direction = direction;
        this.tile = tile;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}
