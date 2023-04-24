package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;

import java.util.ArrayList;
import java.util.HashMap;

public class MakingForceBuilding extends Building{
    private HashMap<People , Integer> forceWithCost;

    public MakingForceBuilding(int x, int y, Government government, int hp, String name, HashMap<People, Integer> forceWithCost) {
        super(x, y, government, hp, "castle building", name);
        this.forceWithCost = forceWithCost;
    }

    public static MakingForceBuilding makeMakingForceBuildingByName(String name, int x ,int y, Government government) {
        if (name.equals("barrack")) {
            HashMap <People , Integer> forceOfBarrack =new HashMap<>();
            //TODO : add people to hasmap
            MakingForceBuilding barrack = new MakingForceBuilding(x, y, government, 1000, name, forceOfBarrack);
            return barrack;
        }
        if (name.equals("engineer guild")) {
            HashMap <People , Integer> forceOfBarrack = new HashMap<>();
            MakingForceBuilding engineerGuild = new MakingForceBuilding(x, y, government, 800, name, forceOfBarrack);
            return engineerGuild;
        }
        if (name.equals("mercenary post")) {
            HashMap <People , Integer> forceOfBarrack = new HashMap<>();
            MakingForceBuilding mercenaryPost = new MakingForceBuilding(x , y, government, 1200, name, forceOfBarrack);
            return mercenaryPost;
        }
        return null;
    }

    public People getForceByCost() {
        return null;
    }

    public void changePeopleRoll(People people) {

    }
}
