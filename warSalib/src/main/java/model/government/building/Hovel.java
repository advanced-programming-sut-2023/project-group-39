package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;

public class Hovel extends Building{

    private final int numberAddPopulation = 8;
    public Hovel(int x, int y, Government government, int hp, GroupOfBuilding group) {
        super(x, y, government, hp, group);
    }

    public int addPopulation() {
        return 0;
    }

}
