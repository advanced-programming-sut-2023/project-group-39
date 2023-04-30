package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class ProductiveBuilding extends Building {
    private int rate;
    private Resource resourceThatMake;

    public ProductiveBuilding(int x, int y, Government government, int hp, String type, String name, int rate,
                              Resource resourceThatMake) {
        super(x, y, government, hp, type, name);
        this.rate = rate;
        this.resourceThatMake = resourceThatMake;
    }

    public static ProductiveBuilding makeProductiveBuildingByName(String name, int x, int y, Government government) {
        if (name.equals("wood cutter")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 3);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding woodCutter = new ProductiveBuilding(x, y, government, 300, "industry",
                        "wood cutter", 30, Resource.WOOD);
                return woodCutter;
            }
        }
        if (name.equals("oil smelter")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.IRON, 10);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding oilSmelter = new ProductiveBuilding(x, y, government, 600, "industry",
                        "oil smelter", 50, Resource.OIL);
                return oilSmelter;
            }
        }
        if (name.equals("stable")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 400);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding stable = new ProductiveBuilding(x, y, government, 400, "castle building",
                        "stable", 4, Resource.HORSE);
                return stable;
            }
        }
        if (name.equals("apple garden")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding appleGarden = new ProductiveBuilding(x, y, government, 300, "farm building"
                        , "apple garden", 100, Resource.APPLE);
                return appleGarden;
            }
        }
        if (name.equals("diary product")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding diaryProduct = new ProductiveBuilding(x, y, government, 200, "farm building"
                        , "diary product", 30, Resource.CHEESE);
                return diaryProduct;
            }
        }
        if (name.equals("barley field")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 15);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding barleyField = new ProductiveBuilding(x, y, government, 100, "farm building"
                        , "barley field", 80, Resource.HOP);
                return barleyField;
            }
        }
        if (name.equals("hunting post")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding huntingPost = new ProductiveBuilding(x, y, government, 300, "farm building"
                        , "hunting post", 20, Resource.MEAT);
                return huntingPost;
            }
        }
        if (name.equals("wheat field")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 15);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding barleyField = new ProductiveBuilding(x, y, government, 100, "farm building"
                        , "wheat field", 70, Resource.WHEAT);
                return barleyField;
            }
        }
        return null;
    }

    public Resource makeResourceWithRate() {
        return null;
    }

    public int getRate() {
        return rate;
    }

    public Resource getResourceThatMake() {
        return resourceThatMake;
    }
}
