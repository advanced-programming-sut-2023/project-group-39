package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

public class ProductiveBuilding extends Building{
    private int rate;
    private Resource resourceThatMake;

    public ProductiveBuilding(int x, int y, Government government, int hp, String type, String name, int rate, Resource resourceThatMake) {
        super(x, y, government, hp, type, name);
        this.rate = rate;
        this.resourceThatMake = resourceThatMake;
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
