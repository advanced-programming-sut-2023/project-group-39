package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class StockPileBuilding extends Building {
    private ArrayList<Resource> resources;

    private ArrayList<Resource> resourcesKeepIt;
    private int capacity;

    public StockPileBuilding(int x, int y, Government government, int hp, String type, String name, int capacity) {
        super(x, y, government, hp, type, name);
        this.capacity = capacity;
        resources = new ArrayList<>();
    }

    public static StockPileBuilding makeStockPileBuildingByName(String name, int x, int y, Government government) {
        if (name.equals("stock pile")) {
            StockPileBuilding stockPile = new StockPileBuilding(x, y, government, 500, "industry", "stock pile" ,800);
            return stockPile;
        }
        if (name.equals("food stock pile")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                StockPileBuilding foodStockPile = new StockPileBuilding(x, y, government, 400, "food processing building", "food stock pile", 700);
                return foodStockPile;
            }
        }
        if (name.equals("armoury")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                StockPileBuilding armoury = new StockPileBuilding(x, y, government, 900, "castle building", "armoury", 700);
                return armoury;
            }
        }
        return null;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public int getCapacity() {
        return capacity;
    }

    private boolean isGoodPlace(String ground) {
        return false;
    }

    public boolean addToResources(Resource resource) {
        return false;
    }
}
