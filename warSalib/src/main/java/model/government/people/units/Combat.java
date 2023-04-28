package model.government.people.units;

import model.wartool.wartoolenum;

public class Combat extends Units{
    private wartoolenum wartool;
    public Combat(int xLocation, int yLocation, UnitsName unitsName) {
        super(xLocation, yLocation, unitsName);
        if(unitsName.getName().equals("pikeman")){
            this.wartool=wartoolenum.SPEAR;
        }
        else if(unitsName.getName().equals("swordsmen")){
            this.wartool=null;
        }
        else if(unitsName.getName().equals("knight")){
            this.hasHorse=true;
        }
        else if(unitsName.getName().equals("blackmonk")){
            this.wartool=wartoolenum.STICK;
        }
        else if(unitsName.getName().equals("slaves")){
            this.wartool=wartoolenum.TORCH;
        }
        else if(unitsName.getName().equals("arabianswordsmen")){
            this.wartool=null;
        }
    }

    public wartoolenum getWartool() {
        return wartool;
    }
}
