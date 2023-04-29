package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;
import model.map.type.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class StockPileBuilding extends Building {
    private HashMap<Resource, Integer> resources;

    private int capacity;

    public StockPileBuilding(int x, int y, Government government, int hp, String type, String name, int capacity) {
        super(x, y, government, hp, type, name);
        this.capacity = capacity;
        resources = new HashMap<>();
    }

    public static StockPileBuilding makeStockPileBuildingByName(String name, int x, int y, Government government) {
        if (name.equals("stock pile")) {
            StockPileBuilding stockPile = new StockPileBuilding(x, y, government, 500, "industry",
                    "stock pile", 800);
            return stockPile;
        }
        if (name.equals("food stock pile")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                StockPileBuilding foodStockPile = new StockPileBuilding(x, y, government, 400,
                        "food processing building", "food stock pile", 700);
                return foodStockPile;
            }
        }
        if (name.equals("armoury")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                StockPileBuilding armoury = new StockPileBuilding(x, y, government, 900, "castle building",
                        "armoury", 700);
                return armoury;
            }
        }
        return null;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public int getCapacity() {
        return capacity;
    }

    private static boolean isGoodPlace(String name, Type type,int x, int y, int numberOfBuilding) {
        //TODO:add this method to building control for stone and blue area
        if (name.equals("stock pile")) {
        }
        return true;
    }

    public void addToResources(Resource resource, int number) {
        if (getName().equals("stock pile")) {
            if (resource.getTypeOfResource() == Resource.TypeOfResource.INDUSTRY) {
                if (resources.containsKey(resource))
                    resources.replace(resource, resources.get(resource) + number);
                else
                    resources.put(resource, number);
                capacity -= number;
            }
        }
        if (getName().equals("food stock pile")) {
            if (resource.getTypeOfResource() == Resource.TypeOfResource.FOOD) {
                if (resources.containsKey(resource))
                    resources.replace(resource, resources.get(resource) + number);
                else
                    resources.put(resource, number);
                capacity -= number;
            }
        }
        if (getName().equals("armoury")) {
            if (resource.getTypeOfResource() == Resource.TypeOfResource.WEAPON) {
                if (resources.containsKey(resource))
                    resources.replace(resource, resources.get(resource) + number);
                else
                    resources.put(resource, number);
                capacity -= number;
            }
        }
    }

    public boolean useResource(Resource resource, int number) {
        if (resources.containsKey(resource)) {
            if (resources.get(resource) < number)
                return false;
            resources.replace(resource,resources.get(resource) - number);
            return true;
        } else
            return false;
    }
}
