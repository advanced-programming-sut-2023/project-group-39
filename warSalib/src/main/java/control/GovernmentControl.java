package main.java.control;

import main.java.model.government.popularityfactor.Fear;
import main.java.model.government.popularityfactor.Food;
import main.java.model.government.popularityfactor.Religion;
import main.java.model.government.popularityfactor.Tax;
import main.java.model.government.request.Request;
import main.java.view.enums.messages.GovernmentMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class GovernmentControl {
    public static GovernmentMenuMessage changePopulation() { return null; }

    public static GovernmentMenuMessage addToResources(Request request) { return null; }

    public static GovernmentMenuMessage removeFromResources(Request request) { return null; }

    public static GovernmentMenuMessage changeWorkersActivities(int fearRate) { return null; }

    public static GovernmentMenuMessage changeSoldiersMorality(int fearRate) { return null; }

    public static GovernmentMenuMessage rateFood(int rate) { return null; }

    public static GovernmentMenuMessage rateTax(int rate) { return null; }

    public static GovernmentMenuMessage rateFear(int rate) { return null; }

    public static GovernmentMenuMessage addToFoods(Food food, int numberOfFoods) { return null; }

    public static GovernmentMenuMessage removeFromFoods(Food food, int numberOfFoods) { return null; }
}
