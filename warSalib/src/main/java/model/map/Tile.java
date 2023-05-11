package model.map;

import model.government.Government;
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

    private Government government;
    private Tree tree;
    private Building building;
    private ArrayList <People> peopleOnTile;
    private Resource resource;

    private boolean hasTunnel;

    private boolean hasKillerTale;


    private boolean hasStair;

    private boolean hasOilTale;

    public boolean isHasTunnel() {
        return hasTunnel;
    }

    public Tile() {
        this.type = Type.GROUND;
        peopleOnTile = new ArrayList<>();
        this.hasTunnel=false;
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

    public ArrayList<People> getPeopleOnTile() {
        return peopleOnTile;
    }

    public Resource getResource() {
        return resource;
    }

    public void removePeople(People people) {
        peopleOnTile.remove(people);
    }

    public void addPeople(People people) {
        peopleOnTile.add(people);
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
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
    public void setHasTunnel(boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }

    public boolean isHasStair() {
        return hasStair;
    }

    public void setHasStair(boolean hasStair) {
        this.hasStair = hasStair;
    }

    public boolean isHasKillerTale() {
        return hasKillerTale;
    }

    public void setHasKillerTale(boolean hasKillerTale) {
        this.hasKillerTale = hasKillerTale;
    }

    public void setHasOilTale(boolean hasOilTale) {
        this.hasOilTale = hasOilTale;
    }

    public boolean isHasOilTale() {
        return hasOilTale;
    }
}
