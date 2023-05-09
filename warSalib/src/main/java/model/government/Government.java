package model.government;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Game;
import model.government.building.Building;
import model.government.people.People;
import model.government.people.engineer.Engineer;
import model.government.popularityfactor.Fear;
import model.government.popularityfactor.Food;
import model.government.popularityfactor.Religion;
import model.government.popularityfactor.Tax;
import model.government.request.Request;
import model.government.resource.Resource;
import model.user.User;
import view.TradeMenu;

public class Government {
    private int wealth;
    private int popularity;
    private int population;
    private User user;
    private HashMap<Food, Integer> foods;

    private HashMap<Resource,Integer> resourcesHashmap;

    private int foodRate;

    private int taxRate;

    private ArrayList<Food> governmentFoods;
    Tax tax;
    Religion religion;
    //for trade to other governments
    private ArrayList<Request> requests;
    private ArrayList<People> people;
    private ArrayList<Building> buildings;
    public static HashMap<Resource, Integer> resources;
    private LinkedHashMap<User, HashMap<Resource, Integer>> tradeHistory;
    private Fear fear;

    private ArrayList<Engineer> engineers=new ArrayList<>();

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
        governmentFoods = new ArrayList<>();
        tax = new Tax();
        fear = new Fear();
    }

//    public HashMap<Resource, Integer> getResources() { return resources; }

    public static void addToResources(Resource resource, int number) {
        if (resources.containsKey(resource)) resources.put(resource, resources.get(resource) + number);
        else resources.put(resource, 1);
    }

    public static void removeFromResources(Resource resource, int number) {
        if (resources.get(resource) < number) System.out.println("there are not enough resources in storehouse");
        else if (resources.get(resource) == number) resources.remove(resource);
        else resources.put(resource, resources.get(resource) - number);
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
    public int numberOfResource(Resource resource){
        for (Resource resource1:getResources().keySet()){
            if(resource1.equals(resource)){
                return getResources().get(resource1);
            }

        }
        return 0;
    }

    public void addToTradeHistory(User user, Resource resource, int number) {
        HashMap<Resource, Integer> value = new HashMap<>();
        value.put(resource, number);
        TradeMenu.tradeList.put(user.getUserGovernment(), value);
    }

    public LinkedHashMap<User, HashMap<Resource, Integer>> getTradeHistory() { return tradeHistory; }

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

    public ArrayList<Building> getBuildings() {
        return buildings;
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

    public ArrayList<Food> getGovernmentFoods() {
        return governmentFoods;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }


    public HashMap<Resource, Integer> getResourcesHashmap() {
        return resourcesHashmap;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public ArrayList<People> getPeople() {
        return people;
    }

    public ArrayList<Engineer> getEngineers() {
        return engineers;
    }
}
