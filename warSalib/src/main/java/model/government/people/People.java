package model.government.people;

import model.government.people.workingpersons.JobsName;
import model.user.User;

public class People {
    protected int xLocation;
    protected int yLocation;
    protected JobsName jobsName;

    protected User ownerPerson;

    protected int toGoX;

    protected int toGoY;


    public People(int xLocation,int yLocation,JobsName jobsName,User ownerPerson)
    {   this.xLocation=xLocation;
        this.yLocation=yLocation;
        this.jobsName=jobsName;
        this.ownerPerson=ownerPerson;
        this.toGoX=xLocation;
        this.toGoY=yLocation;
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

    public void setToGoX(int toGoX) {
        this.toGoX = toGoX;
    }

    public void setToGoY(int toGoY) {
        this.toGoY = toGoY;
    }

    public int getToGoX() {
        return toGoX;
    }

    public int getToGoY() {
        return toGoY;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

}
