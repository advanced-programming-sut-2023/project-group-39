package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class Wall extends Building{
    public Wall(int x, int y, Government government, int hp, GroupOfBuilding group) {
        super(x, y, government, hp, group);
    }
}
