package model.government.building;

import model.government.Government;
import model.government.resource.Resource;

import java.util.HashMap;

public class ConvertedBuilding extends Building{
    private int rate;
    private Resource primitiveResource;
    private Resource finalResource;

    public ConvertedBuilding(int x, int y, Government government, int hp, String type, String name, int rate, Resource primitiveResource, Resource finalResource) {
        super(x, y, government, hp, type, name);
        this.rate = rate;
        this.primitiveResource = primitiveResource;
        this.finalResource = finalResource;
    }

    public static ConvertedBuilding makeConvertedBuildingByName(String name, int x, int y, Government government) {
        if (name.equals("mill")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding mill = new ConvertedBuilding(x, y, government, 300, "food processing building", name, 20, Resource.WHEAT, Resource.FLOUR);
                return mill;
            }
        }
        if (name.equals("armourer")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding armourer = new ConvertedBuilding(x, y, government, 600, "weapon", name, 3, Resource.IRON, Resource.ARMOUR);
                return armourer;
            }
        }
        if (name.equals("blacksmith")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding blacksmith = new ConvertedBuilding(x, y, government, 700, "weapon", name, 3, Resource.IRON, Resource.SWORD);
                return blacksmith;
            }
        }
        if (name.equals("fletcher")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding fletcher = new ConvertedBuilding(x, y, government, 600, "weapon", name, 5, Resource.IRON, Resource.ARCHER);
                return fletcher;
            }
        }
        if (name.equals("pole turner")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding poleTurner = new ConvertedBuilding(x, y, government, 500, "weapon", name, 6, Resource.IRON, Resource.SPEAR);
                return poleTurner;
            }
        }
        if (name.equals("bakery")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding bakery = new ConvertedBuilding(x, y, government, 200, "food processing building", name, 40, Resource.FLOUR, Resource.BREAD);
                return bakery;
            }
        }
        if (name.equals("brewery")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding brewery = new ConvertedBuilding(x, y, government, 350, "food processing building", name, 30, Resource.HOP, Resource.BEAR);
                return brewery;
            }
        }
        return null;
    }

    public int convertResourceToAnotherWithRate() {
        return 0;
    }


}
