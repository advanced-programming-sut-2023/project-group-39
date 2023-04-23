package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class KillingPit extends Building{
    private int damage;
    public KillingPit(int x, int y, Government government, int hp, String type, int damage) {
        super(x, y, government, hp, type);
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
