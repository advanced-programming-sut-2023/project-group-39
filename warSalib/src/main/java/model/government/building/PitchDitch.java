package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class PitchDitch extends Building{
    private boolean isOnFire;

    public PitchDitch(int x, int y, Government government, int hp, String type, String name, boolean isOnFire) {
        super(x, y, government, hp, type, name);
        this.isOnFire = isOnFire;
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
