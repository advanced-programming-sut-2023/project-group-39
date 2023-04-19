package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;

abstract public class Building {
    Government government;
    ArrayList<People> worker;
    ArrayList<Resource> costs;
    int hp;
    GroupOfBuilding group;

    int x , y;

    public Building(int x , int y ,Government government, int hp, GroupOfBuilding group) {
        this.x=x;
        this.y=y;
        this.hp = hp;
        this.group = group;
        this.government = government;
    }

    public static GroupOfBuilding getGroupByType(String type) {
        return null;
    }

    public void addWorker(People people) {
        worker.add(people);
    }

    public void addCosts(Resource resource) {
        costs.add(resource);
    }

    public boolean IsVisibleBuilding() {
        return true;
    }
}
