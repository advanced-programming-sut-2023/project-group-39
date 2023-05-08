package model.government.building;

import com.sun.jdi.request.BreakpointRequest;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.HashMap;

public class ProductiveBuilding extends Building {
    private int rate;
    private Resource resourceThatMake;

    public ProductiveBuilding(int x, int y, Government government, int hp, String type, String name, int rate,
                              Resource resourceThatMake, HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, hp, resource);
        this.rate = rate;
        this.resourceThatMake = resourceThatMake;
    }

    public static ProductiveBuilding makeProductiveBuildingByName(String name, int x, int y, Government government) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("wood cutter")) {
            resource.put(Resource.WOOD, 3);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding woodCutter = new ProductiveBuilding(x, y, government, 300, "industry",
                        "wood cutter", 30, Resource.WOOD, resource);
                woodCutter.setWorkerDataBase(JobsName.WOODCUTTER.getJobsName(), 1);
                return woodCutter;
            }
        }
        if (name.equals("oil smelter")) {
            resource.put(Resource.IRON, 10);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding oilSmelter = new ProductiveBuilding(x, y, government, 600, "industry",
                        "oil smelter", 50, Resource.OIL, resource);
                //oilSmelter.setWorkerDataBase();
                return oilSmelter;
            }
        }
        if (name.equals("stable")) {
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 400);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding stable = new ProductiveBuilding(x, y, government, 400, "castle building",
                        "stable", 4, Resource.HORSE, resource);
                return stable;
            }
        }
        if (name.equals("apple garden")) {
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding appleGarden = new ProductiveBuilding(x, y, government, 300, "farm building"
                        , "apple garden", 100, Resource.APPLE, resource);
                appleGarden.setWorkerDataBase(JobsName.APPLEFARMER.getJobsName(), 1);
                return appleGarden;
            }
        }
        if (name.equals("diary product")) {
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding diaryProduct = new ProductiveBuilding(x, y, government, 200, "farm building"
                        , "diary product", 30, Resource.CHEESE, resource);
                diaryProduct.setWorkerDataBase(JobsName.DIARYPRODUCER.getJobsName(), 1);
                return diaryProduct;
            }
        }
        if (name.equals("barley field")) {
            resource.put(Resource.WOOD, 15);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding barleyField = new ProductiveBuilding(x, y, government, 100, "farm building"
                        , "barley field", 80, Resource.HOP, resource);
                barleyField.setWorkerDataBase(JobsName.HOPFARMER.getJobsName(), 1);
                return barleyField;
            }
        }
        if (name.equals("hunting post")) {
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding huntingPost = new ProductiveBuilding(x, y, government, 300, "farm building"
                        , "hunting post", 20, Resource.MEAT, resource);
                //huntingPost.setWorkerDataBase();
                return huntingPost;
            }
        }
        if (name.equals("wheat field")) {
            resource.put(Resource.WOOD, 15);
            if (government.hasEnoughResources(resource)) {
                ProductiveBuilding wheatField = new ProductiveBuilding(x, y, government, 100, "farm building"
                        , "wheat field", 70, Resource.WHEAT, resource);
                wheatField.setWorkerDataBase(JobsName.WHEATFARMER.getJobsName(), 1);
                return wheatField;
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
