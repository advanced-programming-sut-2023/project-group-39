package model.government.building;

import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Inn extends Building{
    private final Resource resource = Resource.HOP;
    private int rate;
    private int popularityRate;
    private String wineUsage;
    //resource just hop change resource to hop in constructor

    ArrayList<People> peopleOfInn;

    public Inn(int x, int y, Government government, int hp, String type, String name,
               HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, 500, resource);
    }

    public static Inn makeInnByName(String name, int x , int y, Government government) {
        if (name.equals("inn")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.GOLD , 100);
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource)) {
                Inn inn = new Inn(x, y, government, 500, "food processing building", name, resource);
                inn.setWorkerDataBase(JobsName.DRINK_SERVER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.DRINK_SERVER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return inn;
            }
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
