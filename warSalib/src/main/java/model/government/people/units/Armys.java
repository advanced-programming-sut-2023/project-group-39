package model.government.people.units;

import java.util.ArrayList;

public class Armys extends Units {
    public ArrayList<SpecialWorks> specialWorks = new ArrayList<>();

    public Armys(int xLocation, int yLocation, UnitsName unitsName) {
        super(xLocation, yLocation, unitsName);
        if (unitsName.getName().equals("sperman")) {
            specialWorks.add(SpecialWorks.CANDIGMOAT);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        } else if (unitsName.getName().equals("maceman")) {
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        }
        else if(unitsName.getName().equals("tunneler")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
        }
        else if(unitsName.getName().equals("ladderman")){
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        }
        else if(unitsName.getName().equals("assassin")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        }
    }

    public ArrayList<SpecialWorks> getSpecialWorks() {
        return specialWorks;
    }
}
