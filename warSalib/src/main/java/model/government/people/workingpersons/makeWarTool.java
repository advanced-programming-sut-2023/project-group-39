package model.government.people.workingpersons;
import model.government.resource.Resource;
import model.user.User;
import model.wartool.warTools;
import model.wartool.wartoolenum;

public class makeWarTool extends WorkingPerson{
    private wartoolenum finalWartool;
    private Resource resource;

    public makeWarTool(int xLocation, int yLocation, JobsName jobsName, User ownerPerson) {
        super(xLocation,yLocation,jobsName,ownerPerson);
        if(jobsName.getJobsName().equals("poleturner")){
            resource=Resource.IRON;
            finalWartool=wartoolenum.SPEAR;
        }
        else if(jobsName.getJobsName().equals("fletcher")){
            resource=Resource.WOOD;
            finalWartool=wartoolenum.BOW;
        }
        else if(jobsName.getJobsName().equals("blacksmith")){
            resource=Resource.IRON;
            finalWartool=wartoolenum.SPEAR;
        }
        else if(jobsName.getJobsName().equals("armour")){
            resource=Resource.IRON;
            finalWartool=wartoolenum.ARMOUR;
        }
    }


}
