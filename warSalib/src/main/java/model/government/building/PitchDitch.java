package main.java.model.government.building;

import main.java.model.government.Government;
import main.java.model.government.building.group.GroupOfBuilding;

public class PitchDitch extends Building{
    private boolean isOnFire;
    public PitchDitch(int x, int y, Government government, int hp, GroupOfBuilding group) {
        super(x, y, government, hp, group);
    }

    public static PitchDitch makePitchDitchByName(String Name) {
        return null;
    }

    public boolean isOnFire() {
        return isOnFire;
    }

    public void setOnFire(boolean onFire) {
        isOnFire = onFire;
    }

    public int damage() {
        return 0;
    }
}
