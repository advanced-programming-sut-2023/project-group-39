package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Market extends Building{
    private ArrayList<Resource>resources;

    public Market(int x, int y, Government government, int hp) {
        super(x, y, government, hp, "industry", "market");
        resources = new ArrayList<>();
    }
    public static Market makeMarketByName(String name, int x , int y, Government government) {
        if (name.equals("market")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                Market market = new Market(x, y, government, 500);
                return market;
            }
        }
        return null;
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
