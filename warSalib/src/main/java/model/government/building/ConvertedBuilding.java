package model.government.building;

import model.government.Government;
import model.government.people.workingpersons.JobsName;
import model.government.people.workingpersons.makeWarTool;
import model.government.resource.Resource;

import java.util.HashMap;
import java.util.Scanner;

public class ConvertedBuilding extends Building {
    private int numberOfPrimitive;
    private int NumberOfFinal;
    private Resource primitiveResource;
    private Resource finalResource;

    public ConvertedBuilding(int x, int y, Government government, int hp, String type, String name,
                             int numberOfPrimitive, int NumberOfFinal, Resource primitiveResource, Resource finalResource) {
        super(x, y, government, hp, type, name);
        this.numberOfPrimitive = numberOfPrimitive;
        this.NumberOfFinal = NumberOfFinal;
        this.primitiveResource = primitiveResource;
        this.finalResource = finalResource;
    }

    public static ConvertedBuilding makeConvertedBuildingByName(String name, int x, int y, Government government) {
        if (name.equals("mill")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding mill = new ConvertedBuilding(x, y, government, 300, "food processing building"
                        , name, 1, 20, Resource.WHEAT, Resource.FLOUR);
                mill.setWorkerDataBase(JobsName.MILLER.getJobsName(), 3);
                return mill;
            }
        }
        if (name.equals("armourer")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding armourer = new ConvertedBuilding(x, y, government, 600, "weapon", name,
                        6, 1, Resource.IRON, Resource.ARMOUR);
                armourer.setWorkerDataBase(JobsName.ARMOUR.getJobsName(), 1);
                return armourer;
            }
        }
        if (name.equals("blacksmith")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding blacksmith = new ConvertedBuilding(x, y, government, 700, "weapon", name,
                        4, 1, Resource.IRON, Resource.SWORD);
                blacksmith.setWorkerDataBase(JobsName.BLACKSMITH.getJobsName(), 1);
                return blacksmith;
            }
        }
        if (name.equals("fletcher")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding fletcher = new ConvertedBuilding(x, y, government, 600, "weapon",
                        name, 5, 1, Resource.WOOD, Resource.ARCHER);
                fletcher.setWorkerDataBase(JobsName.FLETCHER.getJobsName(), 1);
                return fletcher;
            }
        }
        if (name.equals("pole turner")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 20);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding poleTurner = new ConvertedBuilding(x, y, government, 500, "weapon",
                        name, 10, 1, Resource.WOOD, Resource.SPEAR);
                poleTurner.setWorkerDataBase(JobsName.POLETURNER.getJobsName(), 1);
                return poleTurner;
            }
        }
        if (name.equals("bakery")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding bakery = new ConvertedBuilding(x, y, government, 200, "food processing building",
                        name, 10, 1, Resource.FLOUR, Resource.BREAD);
                bakery.setWorkerDataBase(JobsName.BAKER.getJobsName(), 1);
                return bakery;
            }
        }
        if (name.equals("brewery")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                ConvertedBuilding brewery = new ConvertedBuilding(x, y, government, 350, "food processing building",
                        name, 1, 30, Resource.HOP, Resource.BEAR);
                brewery.setWorkerDataBase(JobsName.BREWER.getJobsName(), 1);
                return brewery;
            }
        }
        return null;
    }

    public int convertResourceToAnotherWithRate() {
        return 0;
    }

    public int getNumberOfPrimitive() {
        return numberOfPrimitive;
    }

    public int getNumberOfFinal() {
        return NumberOfFinal;
    }
}
