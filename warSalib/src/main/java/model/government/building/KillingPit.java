package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;

public class KillingPit extends Building{
    private int damage;
    public KillingPit(int x, int y, Government government, int hp, GroupOfBuilding group, int damage) {
        super(x, y, government, hp, group);
        this.damage = damage;
    }
    public static KillingPit makeKillingPitByName(String Name) {
        return null;
    }

    public int getDamage () {
        return damage;
    }

    public boolean IsVisibleBuilding() {
        return false;
    }


}
