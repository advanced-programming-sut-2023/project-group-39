package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.ArrayList;

public class Market extends Building{
    private ArrayList<Resource>resources;

    public Market(int x, int y, Government government, int hp, String type) {
        super(x, y, government, hp, type);
        resources=new ArrayList<>();
    }
    public void addResourceToMarket(Resource resource) {
        resources.add(resource);
    }
    public Resource buyResource() {
        return null;
    }

    public void sellResource(Resource resource){

    }
}
