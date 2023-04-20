package main.java.model.government.people.workingpersons;
import main.java.model.crops.Crops;
public class Producer extends WorkingPerson{
    private Crops finalCrop;
    private Crops firstCrop;

    public Producer(String moode, int aptitude) {
        super(moode, aptitude);
    }

    public void setFinalCrop(Crops finalCrop) {
        this.finalCrop = finalCrop;
    }

    public void setFirstCrop(Crops firstCrop) {
        this.firstCrop = firstCrop;
    }

    public Crops getFinalCrop() {
        return finalCrop;
    }

    public Crops getFirstCrop() {
        return firstCrop;
    }
}
