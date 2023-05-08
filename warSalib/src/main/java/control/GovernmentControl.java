package control;

import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.units.Units;
import model.government.popularityfactor.Food;
import model.government.popularityfactor.PopularityFactor;
import model.government.resource.Resource;
import view.GovernmentMenu;
import view.enums.messages.GovernmentMenuMessage;

import java.util.HashMap;
import java.util.Map;

public class GovernmentControl {
    private static Government government = Game.getCurrentUser().getUserGovernment();
    public static String showPopularityFactors() {
        StringBuilder result = new StringBuilder();
        result.append("Popularity factors:\n")
                .append("foods:\n\trate: ")
                .append(government.getFoodRate())
                .append("\n\teffect: ");
        int size = government.getFoods().size();
        int difference = size - 1;
        int foodEffect = difference;
        int foodRate = government.getFoodRate();
        if (foodRate == -2) foodEffect -= 8;
        else if (foodRate == -1) foodEffect -= 4;
        else if (foodRate == 0) ;
        else if (foodRate == 1) foodEffect += 4;
        else if (foodRate == 2) foodEffect += 8;
        result.append(foodEffect)
                .append("tax:\n\trate: ")
                .append(government.getTaxRate())
                .append("\n\teffect: ");
        int taxEffect = 0;
        int taxRate = government.getTaxRate();
        if (taxRate == -3) taxEffect += 7;
        else if (taxRate == -2) taxEffect += 5;
        else if (taxRate == -1) taxEffect += 3;
        else if (taxRate == 0) taxEffect++;
        else if (taxRate == 1) taxEffect += 2;
        else if (taxRate == 2) taxEffect += 4;
        else if (taxRate == 3) taxEffect += 6;
        else if (taxRate == 4) taxEffect += 8;
        else if (taxRate == 5) taxEffect += 12;
        else if (taxRate == 6) taxEffect += 16;
        else if (taxRate == 7) taxEffect += 20;
        else if (taxRate == 8) taxEffect += 24;
        result.append(taxEffect)
                .append("fear:\n\trate: ")
                .append(government.getFearRate())
                .append("\n\teffect: ");
        int fearEffect = government.getFearRate();
        result.append(fearEffect)
                .append("religion:\n\teffect: ");
        // handle effect of religion in popularity
        //--------------------------------------------------------------------------------------------//
        int religionEffect = government.getPopularity() - foodEffect - taxEffect - fearEffect;
        result.append(religionEffect);
        //--------------------------------------------------------------------------------------------//
        return result.toString();
    }

    public static String showPopularity() {
        return String.valueOf(Game.getCurrentUser().getUserGovernment().getPopularity());
    }

    public static GovernmentMenuMessage changePopulation() {
        return null;
    }

    public static void addToResources(Resource resource, int number) {
        HashMap<Resource, Integer> resources = government.getResources();
        if (resources.containsKey(resource)) resources.put(resource, resources.get(resource) + number);
        else resources.put(resource, 1);
        System.out.println("successfully added " + number + " " + resource + " to resources");
    }

    public static GovernmentMenuMessage removeFromResources(Resource resource, int number) {
        HashMap<Resource, Integer> resources = government.getResources();
        if (resources.get(resource) < number)
            return GovernmentMenuMessage.NOT_ENOUGH_INVENTORY;
        else if (resources.get(resource) == number) resources.remove(resource);
        else resources.put(resource, resources.get(resource) - number);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage changeWorkersActivities(int fearRate) {
        return null;
    }

    public static void changeSoldiersMorality(int fearRate) {
        for (int i = 0; i < 200; i++)
            for (int j = 0; j < 200; j++)
                for (People people:Game.getMapInGame().getMap()[i][j].getPeopleOnTile())
                    if (people instanceof Units)
                        people.changeEfficientAttackingPower(-5 * fearRate);
    }

    public static GovernmentMenuMessage rateFood(int rate) {
        if (!validateRateNumber("food", rate))
            return GovernmentMenuMessage.INVALID_RATE;
        government.setFoodRate(rate);
        makeChangesByFoodRate(rate, government);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage rateTax(int rate) {
        if (!validateRateNumber("tax", rate))
            return GovernmentMenuMessage.INVALID_RATE;
        government.setTaxRate(rate);
        makeChangesByTaxRate(rate, government);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage rateFear(int rate) {
        if (!validateRateNumber("fear", rate))
            return GovernmentMenuMessage.INVALID_RATE;
        government.setFearRate(rate);
        makeChangesByFearRate(rate, government);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static void makeChangesByFoodRate(int rate, Government government) {


    }

    public static void makeChangesByTaxRate(int rate, Government government) {

    }

    public static void makeChangesByFearRate(int rate, Government government) {

    }

    public static GovernmentMenuMessage showFoodList() {
        if (government.getFoods().keySet().size() == 0)
            return GovernmentMenuMessage.EMPTY_FOOD_LIST;
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage addToFoods(Food food, int numberOfFoods) {
        if (food == null) {
            return GovernmentMenuMessage.INVALID_FOOD_NAME;
        }
        int value = 0;
        for (Map.Entry<Resource, Integer> foodResources : Game.getCurrentUser().getUserGovernment().getResources().entrySet()) {
            if (food.getFoodName().equals(foodResources.getKey())) {
                value = foodResources.getValue();
                break;
            }

        }
        if (numberOfFoods > value) {
            return GovernmentMenuMessage.NOT_ENOUGH_INVENTORY;
        }
        for (Map.Entry<Food, Integer> foods : Game.getCurrentUser().getUserGovernment().getFoods().entrySet()) {   //is it okay?
            if (food.getFoodName().equals(foods.getKey().getFoodName())) {
                Game.getCurrentUser().getUserGovernment().getFoods().replace(foods.getKey(), foods.getValue(), foods.getValue() + numberOfFoods);
                return GovernmentMenuMessage.SUCCESS;
            }
        }


        return null;
    }

    public static GovernmentMenuMessage removeFromFoods(Food food, int numberOfFoods) {
        HashMap<Food, Integer> foods = government.getFoods();
        if (foods.get(food) < numberOfFoods)
            return GovernmentMenuMessage.NOT_ENOUGH_FOOD;
        else if (foods.get(food) == numberOfFoods) foods.remove(food);
        else foods.put(food, foods.get(food) - numberOfFoods);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static boolean validateRateNumber(String type, int rate) {
        if (type.equals("food"))
            if (rate > 2 || rate < -2) return false;
        else if (type.equals("tax"))
            if (rate > 8 || rate < -3) return false;
        else if (type.equals("fear"))
            if (rate > 5 || rate < -5) return false;
        return true;
    }
}
