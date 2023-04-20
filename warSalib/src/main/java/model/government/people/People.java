package model.government.people;

public class People {
    protected String mode;
    protected int aptitude;

    public People() {
    }

    public String getMode() {
        return mode;
    }

    public int getAptitude() {
        return aptitude;
    }

    public void setAptitude(int aptitude) {
        this.aptitude = aptitude;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public People(String mode, int aptitude) {
        this.mode = mode;
        this.aptitude = aptitude;
    }
}
