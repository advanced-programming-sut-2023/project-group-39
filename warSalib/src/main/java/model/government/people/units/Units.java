package model.government.people.units;

import model.wartool.wartoolenum;

public class Units {
    protected UnitsName unitsName;
    protected int xLocation;
    protected int yLocation;
    protected int hitPoint;
    protected boolean hasHorse;

    protected State state;


    public Units(int xLocation,int yLocation,UnitsName unitsName) {
        this.unitsName=unitsName;
        this.xLocation=xLocation;
        this.yLocation=yLocation;
        this.hitPoint=100;
        this.state=State.STANDING;
        this.hasHorse=false;

    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public UnitsName getUnitsName() {
        return unitsName;
    }
    public void changeHitPoint(int x){
        hitPoint+=x;
    }

    public void setState(State state) {
        this.state = state;
    }
}
