package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.people.People;

import java.util.ArrayList;

public class Church extends Building{
    private int addToPopularity;
    private ArrayList<People> people;
    public Church(int x , int y , Government government, int hp, GroupOfBuilding group, int addToPopularity) {
        super(x,y,government, hp, group);
        this.addToPopularity = addToPopularity;
    }

    public static Church makeChurchByName(String Name) {
        return null;
    }
    public int improvePopularity() {
        return 0;
    }

    public void changeNormalToFightingMonk(People people) {

    }
    public void makePriest(People people){

    }
}
