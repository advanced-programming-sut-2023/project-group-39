package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class Wall extends Building{
    public Wall(int x, int y, Government government, int hp, String type) {
        super(x, y, government, hp, type);
    }
}
