package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

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
