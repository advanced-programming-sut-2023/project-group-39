package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class Hovel extends Building{

    private final int numberAddPopulation = 8;
    public Hovel(int x, int y, Government government, int hp, String type) {
        super(x, y, government, hp, type);
    }

    public int addPopulation() {
        return 0;
    }

}
