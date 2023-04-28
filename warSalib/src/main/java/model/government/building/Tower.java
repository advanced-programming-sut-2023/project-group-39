package model.government.building;

import model.government.Government;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Tower extends Building{
    private int defendRange;
    private int fireRange;

    ArrayList<People> peopleOfTower;

    public Tower(int x, int y, Government government, int hp, String type, String name, int defendRange, int fireRange) {
        super(x, y, government, hp, type, name);
        this.defendRange = defendRange;
        this.fireRange = fireRange;
    }

    public static Tower makeTowerByName(String name, int x, int y, Government government) {
        if (name.equals("lookout tower")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.STONE, 10);
            if (government.hasEnoughResources(resource)) {
                Tower lookoutTower = new Tower(x, y, government, 1000, "castle building", name,
                        1000, 700);
                return lookoutTower;
            }
        }
        if (name.equals("perimeter tower")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.STONE, 10);
            if (government.hasEnoughResources(resource)) {
                Tower perimeterTower = new Tower(x, y, government, 800, "castle building", name,
                        400, 900);
                return perimeterTower;
            }
        }
        if (name.equals("defensive tower")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.STONE, 15);
            if (government.hasEnoughResources(resource)) {
                Tower defensiveTower = new Tower(x, y, government, 1200, "castle building", name,
                        400, 900);
                return defensiveTower;
            }
        }
        if (name.equals("square tower")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.STONE, 35);
            if (government.hasEnoughResources(resource)) {
                Tower squareTower = new Tower(x, y, government, 1500, "castle building", name, 800,
                        1200);
                return squareTower;
            }
        }
        if (name.equals("circle tower")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.STONE, 40);
            if (government.hasEnoughResources(resource)) {
                Tower circleTower = new Tower(x, y, government, 2000, "castle building", name, 900,
                        1300);
                return circleTower;
            }
        }
        return null;
    }

    public int addTowerDefending () {
        return 0;
    }

    public void addPeople(People people) {
        peopleOfTower.add(people);
    }
}
