package view;

import control.EnvironmentControl;
import view.enums.commands.EnvironmentMenuCommands;
import view.enums.messages.EnvironmentMenuMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EnvironmentMenu {
    public static void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("\s*back\s*"))
                break;
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.SET_TEXTURE)) != null)
                setTexture(matcher);
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.SET_TEXTURE_RECTANGLE)) != null)
                setTextureRectangle(matcher);
            else System.out.println("invalid");
        }
    }

    public static void chooseMap() {

    }

    private static void setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        EnvironmentMenuMessage message = EnvironmentControl.setTexture(x, y, type);
    }

    private static void setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x"));
        int y1 = Integer.parseInt(matcher.group("y"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        EnvironmentMenuMessage message = EnvironmentControl.setTextureWithRectangle(x1, y1, x2, y2, type);
    }

    private static void clearTile(Matcher matcher) {

    }

    private static void dropRock(Matcher matcher) {

    }

    private static void dropTree(Matcher matcher) {

    }

    private static void dropBuilding(Matcher matcher) {

    }

    private static void dropUnit(Matcher matcher) {

    }

    private static void chooseColor(Matcher matcher) {

    }

    private static void setKeep(Matcher matcher) {

    }

    private static String getTypeWithoutDoubleQuotation(String type) {
        if (type.startsWith("\"")) {
            return type.substring(1, type.length() - 1);
        }
        return type;
    }
}
