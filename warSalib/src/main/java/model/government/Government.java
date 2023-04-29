package model.government;

import java.util.ArrayList;
import java.util.HashMap;

import model.government.building.Building;
import model.government.people.People;
import model.government.popularityfactor.Fear;
import model.government.popularityfactor.Food;
import model.government.popularityfactor.Religion;
import model.government.popularityfactor.Tax;
import model.government.request.Request;
import model.government.resource.Resource;
import model.user.User;

public class Government {
    private int wealth;
    private int popularity;
    private int population;
    private User user;
    private HashMap<Integer, Food> foods;
    Tax tax;
    Religion religion;
    //for trade to other governments
    ArrayList<Request> requests;
    ArrayList<People> people;
    ArrayList<Building> buildings;
    private HashMap<Resource , Integer> resources;
    Fear fear;

    public Government(int popularity, int population, User user) {
        this.wealth = 0;
        this.popularity = popularity;
        this.population = population;
        this.user = user;
        foods = new HashMap<>();
        religion = new Religion();
        people = new ArrayList<>();
        buildings = new ArrayList<>();
        resources = new HashMap<>();
        tax = new Tax();
        fear = new Fear();
    }

    public int getWealth() { return wealth; }

    public void setWealth(int wealth) { this.wealth = wealth; }

    public boolean hasEnoughResources(HashMap<Resource, Integer> resources) {
        for (Resource resource : resources.keySet()) {
            if (getResources().get(resource) < resources.get(resource))
                return false;
        }
        return true;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public User getUser() {
        return user;
    }

    public HashMap<Integer, Food> getFoods() {
        return foods;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) { this.tax = tax; }

    public Religion getReligions() {
        return religion;
    }

    public Fear getFear() {
        return fear;
    }

    public void setFear(Fear fear) { this.fear = fear; }

    public void addToRequests(Request request) {
        requests.add(request);
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public static void changePopularityByFoods(HashMap<Integer, Food> foods) {

    }

    public static void changePopularityByFear(Fear fear) {

    }

    public static void changePopularityByTax(Tax tax) {

    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

}
