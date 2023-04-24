package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

public class ProductiveBuilding extends Building {
    private int rate;
    private Resource resourceThatMake;

    public ProductiveBuilding(int x, int y, Government government, int hp, String type, String name, int rate, Resource resourceThatMake) {
        super(x, y, government, hp, type, name);
        this.rate = rate;
        this.resourceThatMake = resourceThatMake;
    }

    public static ProductiveBuilding makeProductiveBuildingByName(String name, int x, int y, Government government) {
        if (name.equals("wood cutter")) {
            ProductiveBuilding woodCutter = new ProductiveBuilding(x, y, government, 300, "industry", "wood cutter", 30, Resource.WOOD);
            return woodCutter;
        }
        if (name.equals("oil smelter")) {
            ProductiveBuilding oilSmelter = new ProductiveBuilding(x, y, government, 600, "industry", "oil smelter", 50, Resource.OIL);
            return oilSmelter;
        }
        if (name.equals("stable")) {
            ProductiveBuilding stable = new ProductiveBuilding(x, y, government,400, "castle building", "stable",  4, Resource.HORSE);
            return stable;
        }
        if (name.equals("apple garden")) {
            ProductiveBuilding appleGarden = new ProductiveBuilding(x, y, government,300, "farm building", "apple garden",  100, Resource.APPLE);
            return appleGarden;
        }
        if (name.equals("diary product")) {
            ProductiveBuilding diaryProduct = new ProductiveBuilding(x, y, government,200, "farm building", "diary product",  30, Resource.CHEESE);
            return diaryProduct;
        }
        if (name.equals("barley field")) {
            ProductiveBuilding barleyField = new ProductiveBuilding(x, y, government, 100, "farm building", "barley field",  80, Resource.HOP);
            return barleyField;
        }
        if (name.equals("hunting post")) {
            ProductiveBuilding huntingPost = new ProductiveBuilding(x, y, government, 300, "farm building", "hunting post",  20, Resource.MEAT);
            return huntingPost;
        }
        if (name.equals("barley field")) {
            ProductiveBuilding barleyField = new ProductiveBuilding(x, y, government, 100, "farm building", "wheat field",  70, Resource.WHEAT);
            return barleyField;
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
