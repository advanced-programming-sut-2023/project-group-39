package view;

import control.GovernmentControl;
import model.Game;
import model.government.popularityfactor.Food;
import model.government.resource.Resource;
import view.enums.commands.GovernmentMenuCommands;
import view.enums.messages.GovernmentMenuMessage;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GovernmentMenu {
    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            Matcher matcher;
            if (input.matches(String.valueOf(GovernmentMenuCommands.SHOW_POPULARITY_FACTORS)))
                showPopularityFactors();
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.ADD_FOOD)) != null)
                addFood(matcher);
            else if (input.matches(String.valueOf(GovernmentMenuCommands.SHOW_POPULARITY)))
                showPopularity();
            else if (input.matches(String.valueOf(GovernmentMenuCommands.SHOW_FOOD_LIST)))
                showFoodList();
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.CHANGE_FOOD_RATE)) != null)
                rateFood(matcher);
            else if (input.matches(String.valueOf(GovernmentMenuCommands.SHOW_FOOD_RATE)))
                showFoodRate();
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.CHANGE_TAX_RATE)) != null)
                rateTax(matcher);
            else if (input.matches(String.valueOf(GovernmentMenuCommands.SHOW_TAX_RATE)))
                showTaxRate();
            else if ((matcher = GovernmentMenuCommands.getMatcher(input, GovernmentMenuCommands.CHANGE_FEAR_RATE)) != null)
                rateFear(matcher);
            else if (input.matches("^\\s*back\\s*$"))
                break;
            else System.out.println("invalid command!");
        }

    }

    private static void showPopularityFactors() {
        System.out.println("***------------Popularity Factors------------***");
        System.out.println("food");
        System.out.println("tax");
        System.out.println("fear");
        System.out.println("religion");
    }

    private static void showPopularity() {
        System.out.println("***------------Popularity------------***" + Game.getCurrentUser().getUserGovernment().getPopulation());
    }

    private static void addFood(Matcher matcher) {
        String food = matcher.group("foodName");
        int amount = Integer.parseInt(matcher.group("amount"));
        Food addingFood = new Food();
        if (food.equals("meat")) {
            for (Food food1 : Game.getCurrentUser().getUserGovernment().getFoods().keySet()) {
                if (food1.getFoodName().equals(Resource.MEAT)) {
                    addingFood = food1;
                    break;
                }
            }

        } else if (food.equals("apple")) {
            for (Food food1 : Game.getCurrentUser().getUserGovernment().getFoods().keySet()) {
                if (food1.getFoodName().equals(Resource.APPLE)) {
                    addingFood = food1;
                }
                break;
            }
        } else if (food.equals("bread")) {
            for (Food food1 : Game.getCurrentUser().getUserGovernment().getFoods().keySet()) {
                if (food1.getFoodName().equals(Resource.BREAD)) {
                    addingFood = food1;
                    break;
                }
            }
        } else if (food.equals("cheese")) {
            for (Food food1 : Game.getCurrentUser().getUserGovernment().getFoods().keySet()) {
                if (food1.getFoodName().equals(Resource.CHEESE)) {
                    addingFood = food1;
                    break;
                }
            }
        }
        GovernmentMenuMessage message = GovernmentControl.addToFoods(addingFood, amount);
        switch (message) {
            case INVALID_RATE:
                System.out.println("amount of add is invalid");
                break;
            case NOT_ENOUGH_INVENTORY:
                System.out.println("amount of adding is more than your inventory");
                break;
            case INVALID_FOOD_NAME:
                System.out.println("food name is invalid");
                break;
            case SUCCESS:
                System.out.println("food added successfully");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void showFoodList() {
        GovernmentMenuMessage message = GovernmentControl.showFoodList();
        switch (message) {
            case EMPTY_FOOD_LIST:
                System.out.println("empty,there are no foods in your list");
                break;
            case SUCCESS:
                for (Map.Entry<Food, Integer> foods : Game.getCurrentUser().getUserGovernment().getFoods().entrySet()) {
                    System.out.println(foods.getKey().getFoodName() + "  :  " + foods.getValue());

                }
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }
    }

    private static void rateFood(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        GovernmentMenuMessage message = GovernmentControl.rateFood(rate);
        switch (message) {
            case INVALID_RATE:
                System.out.println("invalid rate");
                break;
            case SUCCESS:
                System.out.println("food rate has been successfully changed to " + rate);
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }

    private static void showFoodRate() {
        System.out.println("***------------Food Rate------------***" + Game.getCurrentUser().getUserGovernment().getFoodRate());
    }

    private static void rateTax(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        GovernmentMenuMessage message = GovernmentControl.rateTax(rate);
        switch (message) {
            case INVALID_RATE:
                System.out.println("invalid rate");
                break;
            case SUCCESS:
                System.out.println("tax rate has been successfully changed to " + rate);
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }

    private static void showTaxRate() {
        System.out.println("***------------Tax Rate------------***" + Game.getCurrentUser().getUserGovernment().getTaxRate());
    }

    private static void rateFear(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        GovernmentMenuMessage message = GovernmentControl.rateFear(rate);
        switch (message) {
            case INVALID_RATE:
                System.out.println("invalid rate");
                break;
            case SUCCESS:
                System.out.println("fear rate has been successfully change to " + rate);
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
}
