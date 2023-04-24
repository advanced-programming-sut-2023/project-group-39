package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.Priest;

import java.util.ArrayList;

public class Church extends Building{
    private final int addToPopularity = 2;
    private ArrayList<People> people;

    public Church(int x, int y, Government government, int hp, String name) {
        super(x, y, government, hp, "town building", name);
        improvePopularity(government);
    }

    public static Church makeChurchByName(String name, int x, int y, Government government) {
        if (name.equals("church")) {
            Church church = new Church(x, y, government, 600, name);
            return church;
        }
        if (name.equals("cathedral")) {
            Church cathedral = new Church(x, y ,government, 1000, name);
            //TODO : MAKE PRIEST
            return cathedral;
        }
        return null;
    }
    private void improvePopularity(Government government) {
        government.setPopularity(government.getPopularity() + 2);
    }

    public void changeNormalToFightingMonk(People people) {

    }
    public void makePriest(People people){

    }
}
