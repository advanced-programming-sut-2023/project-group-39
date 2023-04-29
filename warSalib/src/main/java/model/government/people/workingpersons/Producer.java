package model.government.people.workingpersons;

import model.crops.Crops;
import model.government.resource.Resource;

public class Producer extends WorkingPerson {
    private Resource finalCrop;
    private Resource firstCrop;

    public Producer(JobsName jobsName) {
        super(jobsName);
        if (jobsName.getJobsName().equals("woodcutter")) {
            firstCrop = Resource.WOOD;
            finalCrop = Resource.WOOD;
        } else if (jobsName.getJobsName().equals("butcher")) {
            firstCrop = Resource.COW;
            finalCrop = Resource.MEAT;
        } else if (jobsName.getJobsName().equals("applefarmer")) {
            firstCrop = null;
            finalCrop = Resource.APPLE;
        } else if (jobsName.getJobsName().equals("diaryproducer")) {
            firstCrop = Resource.COW;
            finalCrop = Resource.CHEESE;
        } else if (jobsName.getJobsName().equals("hopfarmer")) {
            firstCrop = null;
            finalCrop = Resource.HOP;
        } else if (jobsName.getJobsName().equals("wheatfarmer")) {
            firstCrop = null;
            finalCrop = Resource.WHEAT;

        } else if (jobsName.getJobsName().equals("baker")) {
            firstCrop = Resource.FLOUR;
            finalCrop = Resource.BREAD;
        }
    }

    public Resource getFinalCrop() {
        return finalCrop;
    }

    public Resource getFirstCrop() {
        return firstCrop;
    }
}
