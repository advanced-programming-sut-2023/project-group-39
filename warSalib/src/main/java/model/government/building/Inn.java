package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.people.People;
import main.java.model.government.resource.Resource;

import java.util.ArrayList;

public class Inn extends Building{
    private Resource resource;
    private int rate;
    private int popularityRate;
    private String wineUsage;
    //resource just hop change resource to hop in constructor

    ArrayList<People> peopleOfInn;
    public Inn(int x , int y , Government government, int hp, GroupOfBuilding group, Resource resource, int rate, int popularityRate, String wineUsage) {
        super(x , y ,government, hp, group);
        this.resource = resource;
        this.rate = rate;
        this.popularityRate = popularityRate;
        this.wineUsage = wineUsage;
    }

    public int improvePopularityBaseRate() {
        return 0;
    }

    public int serveBeerWithRate() {
        return 0;
    }

    public void addPeople(People people) {
        peopleOfInn.add(people);
    }

}
