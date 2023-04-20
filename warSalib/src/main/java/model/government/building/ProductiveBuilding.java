package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.resource.Resource;

public class ProductiveBuilding extends Building{
    private int rate;
    private Resource resourceThatMake;
    public ProductiveBuilding(int x, int y, Government government, int hp, GroupOfBuilding group, int rate, Resource resource) {
        super(x,y,government, hp, group);
        this.rate=rate;
        resourceThatMake = resource;
    }

    public static ProductiveBuilding makeProductiveBuildingByName(String Name) {
        return null;
    }

    public Resource makeResourceWithRate() {
        return null;
    }

    public int getRate() {
        return rate;
    }

    public Resource getResourceThatMake() {
        return resourceThatMake;
    }
}
