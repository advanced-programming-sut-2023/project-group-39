package model.government.building;

import model.government.Government;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;

public class Tower extends Building{
    private int defendRange;
    private int fireRange;

    Resource resource;
    ArrayList<People> peopleOfTower;

    public Tower(int x, int y, Government government, int hp, String type, String name, int defendRange, int fireRange, Resource resource) {
        super(x, y, government, hp, type, name);
        this.defendRange = defendRange;
        this.fireRange = fireRange;
        this.resource = resource;
    }

    public static Tower makeTowerByName(String Name) {
        return null;
    }

    public int addTowerDefending () {
        return 0;
    }

    public void addPeople(People people) {
        peopleOfTower.add(people);
    }
}
