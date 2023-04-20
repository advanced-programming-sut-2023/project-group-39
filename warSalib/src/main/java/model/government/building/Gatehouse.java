package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;
import main.java.model.government.people.People;

public class Gatehouse extends Building{
    private People[] people;

    public Gatehouse(int x, int y, Government government, int hp, GroupOfBuilding group, People[] people) {
        super(x, y, government, hp, group);
        this.people = people;
    }

    public static Gatehouse makeGatehouseByName(String Name) {
        return null;
    }

    public static void changeTaxRate() {

    }

    public static void addPeople(People people) {

    }
}
