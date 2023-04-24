package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;

public class Inn extends Building{
    private final Resource resource = Resource.HOP;
    private int rate;
    private int popularityRate;
    private String wineUsage;
    //resource just hop change resource to hop in constructor

    ArrayList<People> peopleOfInn;

    public Inn(int x, int y, Government government, int hp, String type, String name) {
        super(x, y, government, hp, type, name);
    }

    public static Inn makeInnByName(String name, int x , int y, Government government) {
        if (name.equals("inn")) {
            Inn inn = new Inn (x, y, government, 500, "food processing building", name);
            return inn;
        }
        return null;
    }

    public int improvePopularityBaseRate() {
        return 0;
    }

    public int serveBeerWithRate() {
        return 0;
    }

    public void addPeople(People people) {
        peopleOfInn.add(people);
    }

}
