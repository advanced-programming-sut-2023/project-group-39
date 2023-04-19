package model.government.people.workingpersons;
import model.crops.Crops;
public class Farmer extends WorkingPerson {
    private Crops crops;

    public Farmer(String moode, int aptitude) {
        super(moode, aptitude);
    }

    public void setCrops(Crops crops) {
        this.crops = crops;
    }

    public Crops getCrops() {
        return crops;
    }
}
