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
            } else if (MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_MAP) != null) {
                moveMap(input);
            } else if (input.matches("\\s*back\\s*")) {
                System.out.println("back to game menu");
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
        String result;
        if (!(result = MapControl.showMap(x, y)) .equals(""))
            System.out.println(result);
        else
        System.out.println("you enter wrong x and y");
    }

    private static void moveMap(String input) {
        int up = 0, down = 0, left = 0, right = 0;
        Matcher matcher;
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_UP);
        if (matcher != null) {
            if (matcher.group("up") != null)
                up = Integer.parseInt(matcher.group("up"));
            else up = 1;
        }
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_DOWN);
        if (matcher != null) {
            if (matcher.group("down") != null)
                down = Integer.parseInt(matcher.group("down"));
            else down = 1;
        }
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_LEFT);
        if (matcher != null) {
            if (matcher.group("left") != null)
                left = Integer.parseInt(matcher.group("left"));
            else left = 1;
        }
        matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.MOVE_RIGHT);
        if (matcher != null) {
            if (matcher.group("right") != null)
                right = Integer.parseInt(matcher.group("right"));
            else right = 1;
        }
        System.out.println(MapControl.moveMap(up, down, right, left));
    }

    private static void showDetail(String command) {
        Matcher matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.MAP_CHECK_X);
        int x = Integer.parseInt(matcher.group("x"));
        matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.MAP_CHECK_Y);
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(MapControl.showDetails(x, y));
    }
}
