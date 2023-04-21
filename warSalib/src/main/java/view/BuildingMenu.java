package view;

import control.BuildingControl;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

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
            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.CREATE_UNIT)) != null) {
                createUnit(matcher);
            }
            else System.out.println("invalid command!");
        }
    }

    private static void dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if (type.startsWith("\"")) {
            type = type.substring(1, type.length() - 1);
        }
        BuildingMessage message = BuildingControl.dropBuilding(x, y, type);
    }

    private static void selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        BuildingMessage message = BuildingControl.selectBuilding(x, y);
    }

    private static void createUnit(Matcher matcher) {
        int count = Integer.parseInt(matcher.group("count"));
        String type = matcher.group("type");
        if (type.startsWith("\"")) {
            type = type.substring(1, type.length() - 1);
        }
        BuildingMessage message = BuildingControl.createUnit(type, count);
    }

    private static void repair() {
        BuildingMessage message = BuildingControl.repair();
    }
}
