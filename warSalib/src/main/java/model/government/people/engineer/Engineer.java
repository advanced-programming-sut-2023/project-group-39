package model.government.people.engineer;

import model.government.people.People;
import model.government.people.units.State;

public class Engineer extends People {
    private State state;
    public Engineer(State state) {
        this.state=state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
