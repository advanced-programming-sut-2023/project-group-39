package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class PitchDitch extends Building{
    private boolean isOnFire;

    public PitchDitch(int x, int y, Government government) {
        super(x, y, government, 0, "castle building", "pitch ditch");
        this.isOnFire = false;
    }

    public static PitchDitch makePitchDitchByName(String name, int x , int y, Government government) {
        if (name.equals("pitch ditch")) {
            PitchDitch pitchDitch = new PitchDitch(x, y, government);
            return pitchDitch;
        }
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
