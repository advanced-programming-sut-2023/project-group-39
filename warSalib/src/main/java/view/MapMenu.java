package view;

import control.MapControl;
import view.enums.commands.MapMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    public static void run(String input, Scanner scanner) {
        while (true) {
            if (MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_MAP) != null) {
                showMap(input);
            } else if (MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_DETAILS) != null) {
                showDetail(input);
            } else if (input.matches("\\s*back\\s*")) {
                break;
            } else System.out.println("invalid command!");
            input = scanner.nextLine();
        }
    }

    private static void showMap(String input) {
        Matcher matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MAP_CHECK_X);
        int x = Integer.parseInt(matcher.group("x"));
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MAP_CHECK_Y);
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(MapControl.showMap(x, y));
    }

    private static void moveMap(Matcher matcher) {

    }

    private static void showDetail(String command) {
        Matcher matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.MAP_CHECK_X);
        int x = Integer.parseInt(matcher.group("x"));
        matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.MAP_CHECK_Y);
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(MapControl.showDetails(x, y));
    }
}
