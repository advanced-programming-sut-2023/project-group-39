package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Market extends Building{
    private HashMap<Resource, Integer>resources;

    public Market(int x, int y, Government government, int hp, HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, "industry", "market", hp, resource);
        resources = new HashMap<>();
    }
    public static Market makeMarketByName(String name, int x , int y, Government government, int flag) {
        if (name.equals("market")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Market market = new Market(x, y, government, 500, resource);
                market.setWorkerDataBase(JobsName.SHOPPER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.SHOPPER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return market;
            }
        }
        return null;
    }

    public void addResourceToMarket(Resource resource, int number) {
        resources.put(resource, number);
    }
    public Resource buyResource(Resource resource) {
        return null;
    }

    public void sellResource(Resource resource, int number){

    }
}
