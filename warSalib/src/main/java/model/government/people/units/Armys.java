package model.government.people.units;

import model.user.User;

import java.util.ArrayList;

public class Armys extends Units {
    public ArrayList<SpecialWorks> specialWorks = new ArrayList<>();

    public Armys(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName,ownerPerson);
        if (unitsName.getName().equals("sper man")) {
            specialWorks.add(SpecialWorks.CANDIGMOAT);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        } else if (unitsName.getName().equals("mace man")) {
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        }
        else if(unitsName.getName().equals("tunneler")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
        }
        else if(unitsName.getName().equals("ladder man")){
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
