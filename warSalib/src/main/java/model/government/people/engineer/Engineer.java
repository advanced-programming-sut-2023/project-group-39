package model.government.people.engineer;

import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.user.User;

public class Engineer extends People {
    public Engineer(int xLocation, int yLocation, JobsName jobsName, User ownerPerson) {
        super(xLocation, yLocation, jobsName, ownerPerson);
    }
}
