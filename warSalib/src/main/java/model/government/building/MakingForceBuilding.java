package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.units.UnitsName;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class MakingForceBuilding extends Building{

    public MakingForceBuilding(int x, int y, Government government, int hp, String name) {
        super(x, y, government, hp, "castle building", name);
    }

    public static MakingForceBuilding makeMakingForceBuildingByName(String name, int x ,int y, Government government) {
        if (name.equals("barrack")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.STONE, 15);
            if (government.hasEnoughResources(resource)) {
                MakingForceBuilding barrack = new MakingForceBuilding(x, y, government, 1000, name);
                return barrack;
            }
        }
        if (name.equals("engineer guild")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 10);
            resource.put(Resource.GOLD, 100);
            if (government.hasEnoughResources(resource)) {
                MakingForceBuilding engineerGuild = new MakingForceBuilding(x, y, government, 800, name);
                return engineerGuild;
            }
        }
        if (name.equals("mercenary post")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                MakingForceBuilding mercenaryPost = new MakingForceBuilding(x, y, government, 1200, name);
                return mercenaryPost;
            }
        }
        return null;
    }

    public People getForceByCost() {
        return null;
    }

    public void changePeopleRoll(People people) {

    }
}
