package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

public class OxTether extends Building{
    private Resource resourceCarried;
    private int rate;

    public OxTether(int x, int y, Government government, int hp, GroupOfBuilding group, Resource resourceCarried, int rate) {
        super(x,y,government, hp, group);
        this.resourceCarried = resourceCarried;
        this.rate = rate;
    }

    public Resource caryResourceWithRate() {
        return null;
    }
}
