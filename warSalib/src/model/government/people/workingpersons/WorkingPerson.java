package model.government.people.workingpersons;

import model.government.people.People;

public class WorkingPerson extends People {
    protected String jobname;

    public WorkingPerson(String moode, int aptitude) {
        super(moode, aptitude);
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobname() {
        return jobname;
    }
}
