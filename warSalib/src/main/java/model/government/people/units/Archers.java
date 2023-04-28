package model.government.people.units;

import model.wartool.wartoolenum;

public class Archers extends Units{
    private int precision;
    private int arrowRadius;
    private int fatality;
    private wartoolenum wartoolenum;
    private boolean arrowInMove;
    public Archers(int xLocation, int yLocation, UnitsName unitsName) {
        super(xLocation, yLocation, unitsName);
        if(unitsName.getName().equals("archer")){
            this.precision=60;
            this.arrowRadius=60;
            this.fatality=50;
            this.wartoolenum= model.wartool.wartoolenum.ARROW;
            this.arrowInMove=false;
        }
        else if(unitsName.getName().equals("crossbowmen")){
            this.precision=80;
            this.arrowRadius=40;
            this.fatality=70;
            this.wartoolenum= model.wartool.wartoolenum.ARROW;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
        else if(unitsName.getName().equals("archerbow")){
            this.precision=60;
            this.arrowRadius=60;
            this.fatality=50;
            this.wartoolenum= model.wartool.wartoolenum.ARROW;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
        else if(unitsName.getName().equals("slingers")){
            this.precision=60;
            this.arrowRadius=40;
            this.fatality=70;
            this.wartoolenum= model.wartool.wartoolenum.STONE;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
        else if(unitsName.getName().equals("horsearchers")){
            this.precision=60;
            this.arrowRadius=60;
            this.fatality=50;
            this.wartoolenum= model.wartool.wartoolenum.ARROW;
            this.arrowInMove=true;
            this.hasHorse=true;
        }
        else if(unitsName.getName().equals("firethowers")){
            this.precision=80;
            this.arrowRadius=60;
            this.fatality=80;
            this.wartoolenum= model.wartool.wartoolenum.FIRECRACKER;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
    }

    public int getPrecision() {
        return precision;
    }

    public int getArrowRadius() {
        return arrowRadius;
    }

    public int getFatality() {
        return fatality;
    }

}
