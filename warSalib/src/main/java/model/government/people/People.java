package model.government.people;

import model.government.people.workingpersons.JobsName;
import model.user.User;

public class People {
    protected int xLocation;
    protected int yLocation;
    protected JobsName jobsName;

    protected User ownerPerson;
    public People(int xLocation,int yLocation,JobsName jobsName,User ownerPerson)
    {   this.xLocation=xLocation;
        this.yLocation=yLocation;
        this.jobsName=jobsName;
        this.ownerPerson=ownerPerson;
    }

    public People() {
    }

    public int getyLocation() {
        return yLocation;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setJobsName(JobsName jobsName) {
        this.jobsName = jobsName;
    }

    public User getOwnerPerson() {
        return ownerPerson;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }
}
