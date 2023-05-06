package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Church extends Building{
    private final int addToPopularity = 2;
    private ArrayList<People> people;

    public Church(int x, int y, Government government, int hp, String name, int maxHp) {
        super(x, y, government, hp, "town building", name, maxHp);
        improvePopularity(government);
    }

    public static Church makeChurchByName(String name, int x, int y, Government government) {
        if (name.equals("church")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.COIN, 250);
            if (government.hasEnoughResources(resource)) {
                Church church = new Church(x, y, government, 600, name, 600);
                return church;
            }
        }
        if (name.equals("cathedral")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.COIN, 1000);
            if (government.hasEnoughResources(resource)) {
                Church cathedral = new Church(x, y, government, 1000, name, 1000);
                //TODO : MAKE PRIEST
                return cathedral;
            }
        }
        return null;
    }
    private void improvePopularity(Government government) {
        government.setPopularity(government.getPopularity() + 2);
    }

    public void changeNormalToFightingMonk(People people) {

    }
    public void makePriest(People people){

    }
}
