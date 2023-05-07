package model.government.people.engineer;

import model.government.people.People;
import model.government.people.units.State;

import java.util.ArrayList;

public class Engineer extends People {
    private State state;

    private boolean hasWork;
    public Engineer(State state) {
        this.state=state;
    }


    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setHasWork(boolean hasWork) {
        this.hasWork = hasWork;
    }

    public boolean isHasWork() {
        return hasWork;
    }

}
