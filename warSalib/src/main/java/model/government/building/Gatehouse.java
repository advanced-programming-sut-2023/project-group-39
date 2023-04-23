package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.popularityfactor.Tax;

import java.util.ArrayList;

public class Gatehouse extends Building {
    private int people;

    private ArrayList<People> peopleLiveIn;

    public Gatehouse(int x, int y, Government government, int hp, String name, int people) {
        super(x, y, government, hp, "castle building", name);
        this.people = people;
        peopleLiveIn = new ArrayList<>();
    }

    public static Gatehouse makeGatehouseByName(String name, int x, int y, Government government) {
        if (name.equals("small stone gatehouse")) {
            Gatehouse smallGatehouse = new Gatehouse(x, y, government, 800, name, 8);
            return smallGatehouse;
        }
        else if (name.equals("big stone gatehouse")) {
            Gatehouse bigGatehouse = new Gatehouse(x, y, government, 1200,  name, 10);
            return bigGatehouse;
        }
        return null;
    }

    public void changeTaxRate(int newRate) {
        Tax.setRate(newRate);
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
}
