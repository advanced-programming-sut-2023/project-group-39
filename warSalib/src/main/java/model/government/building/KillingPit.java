package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class KillingPit extends Building{
    private final int damage = 300;

    public KillingPit(int x, int y, Government government, int hp, String type, String name) {
        super(x, y, government, hp, type, name);
    }

    public static KillingPit makeKillingPitByName(String name, int x ,int y, Government government) {
        if (name.equals("killing pit")) {
            KillingPit killingPit = new KillingPit(x, y , government , 600 , "castle building" , name);
            return killingPit;
        }
        return null;
    }

    public int getDamage () {
        return damage;
    }

    public boolean IsVisibleBuilding() {
        return false;
    }


}
