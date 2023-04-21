package view;

import view.enums.commands.BuildingCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingMenu {
    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            Matcher matcher;
            if (input.matches("^\\s*repair\\s*$")) {
                repair();
            } else if (input.matches("^\\s*back\\s*$")) {
                break;
            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.DROP_BUILDING)) != null) {
                dropBuilding(matcher);
            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.SELECT_BUILDING)) != null) {
                selectBuilding(matcher);
            }
            else System.out.println("invalid command!");
        }
    }

    private static void dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        System.out.println("x is " + x + "y is " + y + "type is " + type);
    }

    private static void selectBuilding(Matcher matcher) {
    }

    private static void createUnit(Matcher matcher) {

    }

    private static void repair() {

    }
}
