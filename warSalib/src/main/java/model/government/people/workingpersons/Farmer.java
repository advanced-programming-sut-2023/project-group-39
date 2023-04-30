package model.government.people.workingpersons;
import model.crops.Crops;
import model.user.User;

public class Farmer extends WorkingPerson {
    private Crops crop;


    public Farmer(int xLocation, int yLocation, JobsName jobsName, User ownerPerson) {
        super(xLocation,yLocation,jobsName,ownerPerson);
        if (jobsName.getJobsName().equals("applefarmer")) {
            crop = Crops.APPLE;
        } else if (jobsName.getJobsName().equals("hopfarmer")) {
            crop = Crops.HOP;
        } else if (jobsName.getJobsName().equals("wheatfarmer")) {
            crop = Crops.WHEAT;
        }
    }
}
