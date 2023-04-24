package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class Building {
    private Government government;
    private HashMap<People, Integer> workerDataBase;
    private HashMap<Resource, Integer> cost;
    private HashMap<People, Integer> workerWorked;
    private int hp;
    private String type;

    private String name;
    private int x , y;

    public Building(int x , int y ,Government government, int hp, String type, String name) {
        this.x=x;
        this.y=y;
        this.hp = hp;
        this.type = type;
        this.government = government;
        this.name = name;
        workerDataBase = new HashMap<>();
        cost = new HashMap<>();
        workerWorked = new HashMap<>();
    }

    public static GroupOfBuilding getGroupByType(String type) {
        GroupOfBuilding [] groups = GroupOfBuilding.values();
        for (GroupOfBuilding group : groups) {
            for (String instance : group.getGroup()) {
                if (type.equals(instance))
                    return group;
            }
        }
        return null;
    }

    public void addWorker(People people, int number) {
        workerWorked.put(people, number);
    }


    public boolean IsVisibleBuilding() {
        return true;
    }

    public Government getGovernment() {
        return government;
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
