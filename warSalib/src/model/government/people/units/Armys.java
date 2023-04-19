package model.government.people.units;

public class Armys extends Units{
    private SpecialWorks specialWorks;

    public Armys(int speed, int hitPoint, int defensingPower, int attackingPower, UnitsName unitsname, UnitsType unitType, int xLocation, int ylocation, model.wartool.wartool wartool) {
        super(speed, hitPoint, defensingPower, attackingPower, unitsname, unitType, xLocation, ylocation, wartool);
    }
}
