package model.government.people.units;

import model.user.User;
import model.wartool.wartoolenum;

public class Combat extends Units{
    private wartoolenum wartool;
    public Combat(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName, ownerPerson);
        if(unitsName.getName().equals("pike man")){
            this.wartool=wartoolenum.SPEAR;
        }
        else if(unitsName.getName().equals("swordsmen")){
            this.wartool=null;
        }
        else if(unitsName.getName().equals("knight")){
            this.hasHorse=true;
        }
        else if(unitsName.getName().equals("black monk")){
            this.wartool=wartoolenum.STICK;
        }
        else if(unitsName.getName().equals("slaves")){
            this.wartool=wartoolenum.TORCH;
        }
        else if(unitsName.getName().equals("arabian swordsmen")){
            this.wartool=null;
        }
    }

    public wartoolenum getWartool() {
        return wartool;
    }
}
