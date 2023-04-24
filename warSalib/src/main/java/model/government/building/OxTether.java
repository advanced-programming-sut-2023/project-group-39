package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class OxTether extends Building{
    private final Resource resource = Resource.STONE;
    private final int rate = 12;

    public OxTether(int x, int y, Government government, int hp) {
        super(x, y, government, hp, "industry", "ox tether");
    }

    public static OxTether makeOxTetherByName(String name, int x , int y, Government government) {
        if (name.equals("ox tether")) {
            OxTether oxTether = new OxTether(x, y, government, 150);
            return oxTether;
        }
        return null;
    }
    public Resource caryResourceWithRate() {
        return null;
    }

    public Resource getResource() {
        return resource;
    }

    public int getRate() {
        return rate;
    }
}
