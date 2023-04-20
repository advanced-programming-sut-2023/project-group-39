package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.resource.Resource;

public class ConvertedBuilding extends Building{
    private int rate;
    private Resource primitiveResource;
    private Resource finalResource;

    public ConvertedBuilding(int x, int y, Government government, int hp, GroupOfBuilding group, int rate, Resource primitiveResource, Resource finalResource) {
        super(x, y, government, hp, group);
        this.rate = rate;
        this.primitiveResource = primitiveResource;
        this.finalResource = finalResource;
    }

    public static ConvertedBuilding makeConvertedBuildingByName(String Name) {
        return null;
    }

    public int convertResourceToAnotherWithRate() {
        return 0;
    }


}
