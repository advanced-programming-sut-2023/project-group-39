package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.ArrayList;

public class StockPileBuilding extends Building {
    private ArrayList<Resource> resources;

    private ArrayList<Resource> resourcesKeepIt;
    private int capacity;

    public StockPileBuilding(int x, int y, Government government, int hp, GroupOfBuilding group, int capacity, ArrayList<Resource> resourcesKeepIt) {
        super(x,y,government, hp, group);
        resources = new ArrayList<>();
        this.capacity = capacity;
        this.resourcesKeepIt = resourcesKeepIt;
    }

    public static StockPileBuilding makeStockPileBuildingByName(String Name) {
        return null;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public int getCapacity() {
        return capacity;
    }
}
