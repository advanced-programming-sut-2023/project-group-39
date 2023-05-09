package model.government;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    private float wealth;
    private int popularity;
    private int population;
    private int populationCapacity;
    private int fearEffect;
    private int foodEffect;
    private int religionEffect;
    private int taxEffect;
    private float efficiency;
    private User user;
    private HashMap<Food, Integer> foods;

    private int foodRate;

    private int taxRate;

    private int fearRate;

    Tax tax;
    Religion religion;
    //for trade to other governments
    private ArrayList<Request> requests;
    private ArrayList<People> people;
    private ArrayList<Building> buildings;
    private HashMap<Resource, Integer> resources;
    private LinkedHashMap<User, HashMap<Resource, Integer>> tradeHistory;
    private Fear fear;

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
        tradeHistory = new LinkedHashMap<>();
        tax = new Tax();
        fear = new Fear();
        this.foodRate = -2;
        this.taxRate = 0;
        this.fearRate = 0;
        this.efficiency = 1;
    }

//    public HashMap<Resource, Integer> getResources() { return resources; }

    public void addToResources(Resource resource, int number) {
        if (resources.containsKey(resource)) resources.put(resource, resources.get(resource) + number);
        else resources.put(resource, 1);
    }

    public void removeFromResources(Resource resource, int number) {
        if (resources.get(resource) < number) System.out.println("there are not enough resources in storehouse");
        else if (resources.get(resource) == number) resources.remove(resource);
        else resources.put(resource, resources.get(resource) - number);
    }
    public float getWealth() { return wealth; }

    public void setWealth(float wealth) { this.wealth = wealth; }

    public float getEfficiency() { return efficiency; }

    public void setEfficiency(float efficiency) { this.efficiency = efficiency; }

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

    public HashMap<Food, Integer> getFoods() {
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

    public static void makeChangesCausedByFoodRate(HashMap<Integer, Food> foods) {

    }

    public static void makeChangesCausedByFearRate(Fear fear) {

    }

    public static void makeChangesCausedByTaxRate(Tax tax) {

    }

    public int getFoodRate() { return foodRate; }

    public int getTaxRate() { return taxRate; }

    public HashMap<Resource, Integer> getResources() { return resources; }

    public void setFoodRate(int foodRate) { this.foodRate = foodRate; }

    public void setTaxRate(int taxRate) { this.taxRate = taxRate; }

    public int getFearRate() { return fearRate; }

    public void setFearRate(int fearRate) { this.fearRate = fearRate; }

    public int getFearEffect() { return fearEffect; }

    public int getFoodEffect() { return foodEffect; }

    public int getReligionEffect() { return religionEffect; }

    public int getTaxEffect() { return taxEffect; }

    public void setFearEffect(int fearEffect) { this.fearEffect = fearEffect; }

    public void setFoodEffect(int foodEffect) { this.foodEffect = foodEffect; }

    public void setReligionEffect(int religionEffect) { this.religionEffect = religionEffect; }

    public void setTaxEffect(int taxEffect) { this.taxEffect = taxEffect; }
}
