package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.ArrayList;

public class StockPileBuilding extends Building {
    private ArrayList<Resource> resources;

    private ArrayList<Resource> resourcesKeepIt;
    private int capacity;

    public StockPileBuilding(int x, int y, Government government, int hp, String type, String name, ArrayList<Resource> resources, ArrayList<Resource> resourcesKeepIt, int capacity) {
        super(x, y, government, hp, type, name);
        this.resources = resources;
        this.resourcesKeepIt = resourcesKeepIt;
        this.capacity = capacity;
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
