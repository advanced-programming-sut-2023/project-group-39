package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class Building {
    Government government;
    ArrayList<People> worker;
    HashMap<Resource , Integer> costs;
    int hp;
    String type;

    String name;
    int x , y;

    public Building(int x , int y ,Government government, int hp, String type, String name) {
        this.x=x;
        this.y=y;
        this.hp = hp;
        this.type = type;
        this.government = government;
        this.name = name;
        worker = new ArrayList<>();
        costs = new HashMap<>();
    }

    public static GroupOfBuilding getGroupByType(String type) {
        return null;
    }

    public void addWorker(People people) {
        worker.add(people);
    }

    public void addCosts(Resource resource , int number) {
        costs.put(resource , number);
    }

    public boolean IsVisibleBuilding() {
        return true;
    }

    public Government getGovernment() {
        return government;
    }

    public HashMap<Resource , Integer> getCosts() {
        return costs;
    }

    public int getHp() {
        return hp;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
