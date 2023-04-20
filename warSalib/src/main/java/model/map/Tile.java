package main.java.model.map;

import main.java.model.government.building.Building;
import main.java.model.government.people.People;
import main.java.model.government.resource.Resource;
import main.java.model.map.rock.Rock;
import main.java.model.map.tree.Tree;
import main.java.model.map.type.Type;

import java.util.ArrayList;

public class Tile {
    private Type type;
    private Rock rock;

    private Tree tree;
    private Building building;
    private ArrayList <People> peopleOnTitle;
    private Resource resource;

    public Tile(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Rock getRock() {
        return rock;
    }

    public Tree getTree() {
        return tree;
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList<People> getPeopleOnTitle() {
        return peopleOnTitle;
    }

    public Resource getResource() {
        return resource;
    }

    public void addPeople(People people) {
        peopleOnTitle.add(people);
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setRock(Rock rock) {
        this.rock = rock;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public void clearAllPeopleOnTile() {

    }
}
