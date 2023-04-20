package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.resource.Resource;

import java.util.ArrayList;

public class Market extends Building{
    private ArrayList<Resource>resources;

    public Market(int x, int y, Government government, int hp, GroupOfBuilding group) {
        super(x, y, government, hp, group);
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
