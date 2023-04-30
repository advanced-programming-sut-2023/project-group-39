package model.map;

import model.government.building.Building;
import model.government.people.People;
import model.government.resource.Resource;
import model.map.rock.Rock;
import model.map.tree.Tree;
import model.map.type.Type;

import java.util.ArrayList;

public class Tile {
    private Type type;
    private Rock rock;

    private Tree tree;
    private Building building;
    private ArrayList <People> peopleOnTile;
    private Resource resource;

    public Tile() {
        this.type = Type.GROUND;
        peopleOnTile = new ArrayList<>();
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
        return peopleOnTile;
    }

    public Resource getResource() {
        return resource;
    }

    public void addPeople(People people) {
        peopleOnTile.add(people);
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
        peopleOnTile.clear();
    }
}
