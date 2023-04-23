package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;

import java.util.ArrayList;

public class MakingForceBuilding extends Building{
    private int CostOfEachForce;

    private ArrayList <People> people;
    public MakingForceBuilding(int x , int y, Government government, int hp, String type, int costOfEachForce, ArrayList<People> people) {
        super(x,y,government, hp, type);
        CostOfEachForce = costOfEachForce;
        this.people = people;
    }

    public static MakingForceBuilding makeMakingForceBuildingByName(String Name) {
        return null;
    }

    public People getForceByCost() {
        return null;
    }

    public void changePeopleRoll(People people) {

    }
}
