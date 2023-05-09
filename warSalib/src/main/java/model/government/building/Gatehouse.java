package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.popularityfactor.Tax;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Gatehouse extends Building {
    private int people;

    private String direction;

    private ArrayList<People> peopleLiveIn;

    private boolean openGate;

    private boolean hasFlag;

    public Gatehouse(int x, int y, Government government, int hp, String name, int people,String direction) {
        super(x, y, government, hp, "castle building", name);
        this.people = people;
        peopleLiveIn = new ArrayList<>();
        this.direction=direction;
        this.openGate=false;
        this.hasFlag=false;
    }

    public static Gatehouse makeGatehouseByName(String name, int x, int y, Government government,String direction) {
        if (name.equals("small stone gatehouse")) {
            Gatehouse smallGatehouse = new Gatehouse(x, y, government, 800, name, 8,direction);
            return smallGatehouse;
        } else if (name.equals("big stone gatehouse")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.STONE, 20);
            if (government.hasEnoughResources(resource)) {
                Gatehouse bigGatehouse = new Gatehouse(x, y, government, 1200, name, 10,direction);
                return bigGatehouse;
            } else {
                System.out.println("we dont have enough stone to build it");
                return null;

            }
        }
        System.out.println("invalid name");
        return null;
    }

    public void changeTaxRate(int newRate) {
        getGovernment().setTaxRate(newRate);
    }

    public boolean addPeople(People newePeople) {
        if (people < peopleLiveIn.size()) {
            peopleLiveIn.add(newePeople);
            return true;
        }
        return false;
    }

    public int getPeople() {
        return people;
    }

    public ArrayList<People> getPeopleLiveIn() {
        return peopleLiveIn;
    }

    public void setOpenGate(boolean openGate) {
        this.openGate = openGate;
    }

    public boolean isOpenGate() {
        return openGate;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public boolean isHasFlag() {
        return hasFlag;
    }
}
