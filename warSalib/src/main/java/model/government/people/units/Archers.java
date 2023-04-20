package main.java.model.government.people.units;

public class Archers extends Units{
    private int precision;
    private int fillMagazineSpeed;
    private boolean canArrowWhenRun;
    private int board;
    private int deadly;

    public Archers(int speed, int hitpoint, int deffensingPower, int attackingPower, UnitsName unitsname, UnitsType unittype, int xLocation, int ylocation, main.java.model.wartool.wartool wartool) {
        super(speed, hitpoint, deffensingPower, attackingPower, unitsname, unittype, xLocation, ylocation, wartool);
    }

    public int getFillMagazineSpeed() {
        return fillMagazineSpeed;
    }

    public int getPrecision() {
        return precision;
    }


    public void setFillMagazineSpeed(int fillMagazineSpeed) {
        this.fillMagazineSpeed = fillMagazineSpeed;
    }


    public void setCanArrowWhenRun(boolean canArrowWhenRun) {
        this.canArrowWhenRun = canArrowWhenRun;
    }
    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
