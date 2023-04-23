package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;

import java.util.ArrayList;

public class Church extends Building{
    private int addToPopularity;
    private ArrayList<People> people;

    public Church(int x, int y, Government government, int hp, String type, String name, int addToPopularity) {
        super(x, y, government, hp, type, name);
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
