package model.government.people.units;

import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.user.User;
import model.wartool.wartoolenum;

public class Units extends People {
    protected UnitsName unitsName;
    protected int hitPoint;
    protected boolean hasHorse;

    protected int patrolFromX;

    protected int patrolFromY;

    protected int patrolToX;

    protected int patrolToY;
    protected State state;

    protected int efficientAttackingPower;


    public Units(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        this.jobsName=null;
        this.unitsName=unitsName;
        this.xLocation=xLocation;
        this.yLocation=yLocation;
        this.hitPoint=500;
        this.state=State.STANDING;
        this.hasHorse=false;
        this.ownerPerson=ownerPerson;
        efficientAttackingPower=unitsName.getAttackingPower();
        this.patrolFromX=-1;
        this.patrolFromY=-1;
        this.patrolToX=-1;
        this.patrolToY=-1;

    }


    public int getHitPoint() {
        return hitPoint;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public UnitsName getUnitsName() {
        return unitsName;
    }
    public void changeHitPoint(int x){
        hitPoint+=x;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void changeEfficientAttackingPower(int x){
        efficientAttackingPower+=x;
    }

    public int getEfficientAttackingPower() {
        return efficientAttackingPower;
    }

    public void setEfficientAttackingPower(int efficientAttackingPower) {
        this.efficientAttackingPower = efficientAttackingPower;
    }

    public void setPatrolFromX(int patrolFromX) {
        this.patrolFromX = patrolFromX;
    }

    public void setPatrolFromY(int patrolFromY) {
        this.patrolFromY = patrolFromY;
    }

    public void setPatrolToX(int patrolToX) {
        this.patrolToX = patrolToX;
    }

    public void setPatrolToY(int patrolToY) {
        this.patrolToY = patrolToY;
    }

    public int getPatrolFromX() {
        return patrolFromX;
    }

    public int getPatrolFromY() {
        return patrolFromY;
    }

    public int getPatrolToX() {
        return patrolToX;
    }

    public int getPatrolToY() {
        return patrolToY;
    }
}
