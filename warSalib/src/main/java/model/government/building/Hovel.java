package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class Hovel extends Building{


    public Hovel(int x, int y, Government government, int hp, String type, String name) {
        super(x, y, government, hp, type, name);
        addPopulation();
    }

    public static Hovel makeHovelByName(String name, int x , int y, Government government) {
        if(name.equals("hovel")) {
            HashMap <Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD , 6);
            if (government.hasEnoughResources(resource)) {
                Hovel hovel = new Hovel(x, y, government, 500, "town building", name);
                return hovel;
            }
        }
        return null;
    }
    private void addPopulation() {
        getGovernment().setPopulation(getGovernment().getPopulation() + 8 );
    }

}
