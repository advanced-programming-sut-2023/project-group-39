package model.government.people.workingpersons;
import model.wartool.warTools;

public class makeWarTool extends WorkingPerson{
    private warTools WorkingPersonWarTool;

    public makeWarTool(String moode, int aptitude) {
        super(moode, aptitude);
    }

    public void setWorkingPersonWarTool(warTools workingPersonWarTool) {
        WorkingPersonWarTool = workingPersonWarTool;
    }

    public warTools getWorkingPersonWarTool() {
        return WorkingPersonWarTool;
    }
}
