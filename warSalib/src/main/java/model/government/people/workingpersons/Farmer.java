package model.government.people.workingpersons;
import model.crops.Crops;
public class Farmer extends WorkingPerson {
    private Crops crop;


    public Farmer(JobsName jobsName) {
        super(jobsName);
        if (jobsName.getJobsName().equals("applefarmer")) {
            crop = Crops.APPLE;
        } else if (jobsName.getJobsName().equals("hopfarmer")) {
            crop = Crops.HOP;
        } else if (jobsName.getJobsName().equals("wheatfarmer")) {
            crop = Crops.WHEAT;
        }
    }
}
