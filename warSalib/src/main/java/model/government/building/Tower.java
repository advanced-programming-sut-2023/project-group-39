package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.people.People;
import main.java.model.government.resource.Weapon;

import java.util.ArrayList;

public class Tower extends Building{
    private int defendRange;
    private int fireRange;

    Weapon weapon;
    ArrayList<People> peopleOfTower;
    public Tower(int x, int y, Government government, int hp, GroupOfBuilding group, int defendRange, int fireRange, Weapon weapon) {
        super(x,y,government, hp, group);
        this.defendRange = defendRange;
        this.fireRange = fireRange;
        this.weapon = weapon;
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
