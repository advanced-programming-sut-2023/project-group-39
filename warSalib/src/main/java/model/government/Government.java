package model.government;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Game;
import model.government.building.Building;
import model.government.building.StockPileBuilding;
import model.government.people.People;
import model.government.popularityfactor.Fear;
import model.government.popularityfactor.Food;
import model.government.popularityfactor.Religion;
import model.government.popularityfactor.Tax;
import model.government.request.Request;
import model.government.resource.Resource;
import model.user.User;
import view.TradeMenu;

public class Government {
    private float wealth;
    private int popularity;
    private int population;

    private int populationCapacity = 0;

    private int fearEffect;
    private int foodEffect;
    private int religionEffect;
    private int taxEffect;
    private User user;

    private ArrayList<People> unWorkedPeople;
    private HashMap<Food, Integer> foods;

    private ArrayList<StockPileBuilding> stockPileBuildings;

    private int foodRate;

    private int taxRate;

    private ArrayList<Food> governmentFoods;
    Tax tax;
    Religion religion;
    //for trade to other governments
    private ArrayList<Request> requests;
    private ArrayList<People> people;
    private ArrayList<Building> buildings;
    private HashMap<Resource, Integer> resources;
    public static LinkedHashMap<User, HashMap<Resource, Integer>> tradeHistory;
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
        stockPileBuildings = new ArrayList<>();
        governmentFoods = new ArrayList<>();
        unWorkedPeople = new ArrayList<>();
        tax = new Tax();
        fear = new Fear();
    }

//    public HashMap<Resource, Integer> getResources() { return resources; }

    public void addToResources(Resource resource) {
        if (resources.containsKey(resource)) resources.put(resource, resources.get(resource) + 1);
        else resources.put(resource, 1);
    }

    public void removeFromResources(Resource resource, int number) {
        if (resources.get(resource) < number) System.out.println("there are not enough resources in storehouse");
        else if (resources.get(resource) == number) resources.remove(resource);
        else resources.put(resource, resources.get(resource) - number);
    }
    public float getWealth() { return wealth; }

    public void setWealth(float wealth) { this.wealth = wealth; }

    public boolean hasEnoughResources(HashMap<Resource, Integer> resources) {
        for (Resource resource : resources.keySet()) {
            if (getResources().get(resource) < resources.get(resource))
                return false;
        }
        return true;
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

    public void changePopularityByFoods(HashMap<Integer, Food> foods) {

    }

    public void changePopularityByFear(Fear fear) {

    }

    public void changePopularityByTax(Tax tax) {

    }

    public void addToPeople (People people1) {
        people.add(people1);
    }

    public ArrayList<Food> getGovernmentFoods() {
        return governmentFoods;
    }

    public ArrayList<People> getUnWorkedPeople() {
        return unWorkedPeople;
    }

    public void removeUnWorkedPeople(People people1) {
        Game.getMapInGame().getMap()[people1.getyLocation()][people1.getxLocation()].removePeople(people1);
        unWorkedPeople.remove(people1);
        people.remove(people1);
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public void addBuilding (Building building) {
        buildings.add(building);
    }

    public ArrayList<StockPileBuilding> getStockPileBuildings() {
        return stockPileBuildings;
    }

    public void addStockPile (StockPileBuilding stockPileBuilding) {
        stockPileBuildings.add(stockPileBuilding);
    }

    public void removeResourceFromStockPile (Resource resource, int count) {
        for (StockPileBuilding stockPileBuilding: stockPileBuildings) {
             if (stockPileBuilding.getType().equals("stock pile") &&
                     resource.getTypeOfResource().equals(Resource.TypeOfResource.INDUSTRY))
                 count = stockPileBuilding.useResource(resource, count);
             else if (stockPileBuilding.getType().equals("food stock pile") &&
                     resource.getTypeOfResource().equals(Resource.TypeOfResource.FOOD))
                 count = stockPileBuilding.useResource(resource, count);
             else if (stockPileBuilding.getType().equals("armoury") &&
                     resource.getTypeOfResource().equals(Resource.TypeOfResource.WEAPON))
                 count = stockPileBuilding.useResource(resource, count);
             if (count == 0) break;
        }
    }

    public int getPopulationCapacity() {
        return populationCapacity;
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }
    public void setPopulationCapacity(int populationCapacity) {
        this.populationCapacity = populationCapacity;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }
}
