package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;

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
