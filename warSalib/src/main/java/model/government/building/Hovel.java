package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class Hovel extends Building{


    public Hovel(int x, int y, Government government, int hp, String type, String name) {
        super(x, y, government, hp, type, name);
        addPopulation();
    }

    private void addPopulation() {
        getGovernment().setPopulation(getGovernment().getPopulation() + 8 );
    }

}
