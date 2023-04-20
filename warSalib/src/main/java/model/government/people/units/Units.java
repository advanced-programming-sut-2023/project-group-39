package model.government.people.units;

import model.wartool.wartool;

public class Units {
    protected UnitsName name;
    protected UnitsType type;
    protected int hitPoint;
    protected int defensingPower;
    protected int attackingPower;
    protected int speed;
    protected int xLocation;
    protected int yLocation;
    protected wartool wartool;
    protected boolean hasArmor;

    public Units(int speed, int hitPoint, int defensingPower, int attackingPower, UnitsName unitsname, UnitsType unitType, int xLocation, int yLocation, wartool
            wartool) {
        this.speed = speed;
        this.yLocation = yLocation;
        this.xLocation = xLocation;
        this.hitPoint = hitPoint;
        this.name = unitsname;
        this.type = unitType;
        this.defensingPower = defensingPower;
        this.attackingPower = attackingPower;
        this.wartool = wartool;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getAttackingPower() {
        return attackingPower;
    }

    public int getDefensingPower() {
        return defensingPower;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void setAttackingPower(int attackingPower) {
        this.attackingPower = attackingPower;
    }

    public void setDefensingPower(int defensingPower) {
        this.defensingPower = defensingPower;
    }

    public int getSpeed() {
        return speed;
    }

    public model.wartool.wartool getWartool() {
        return wartool;
    }
}
