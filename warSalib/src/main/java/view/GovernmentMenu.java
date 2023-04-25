package view;

import control.GovernmentControl;
import view.enums.commands.GovernmentMenuCommands;
import view.enums.messages.GovernmentMenuMessage;

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
    }

    private static void showPopularity() {
        System.out.println("***------------Popularity------------***");
    }

    private static void showFoodList() {
        GovernmentMenuMessage message = GovernmentControl.showFoodList();
        switch (message) {
            case EMPTY_FOOD_LIST:
                System.out.println("empty   ***     there are no foods in your list     ***     empty");
                break;
            case SUCCESS:
                System.out.println("***------------Food List------------***");
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
        System.out.println("***------------Food Rate------------***");
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
        System.out.println("***------------Tax Rate------------***");
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
