package main.java.view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GovernmentMenuCommands {
    ;

    private String regex;

    GovernmentMenuCommands(String regex) { this.regex = regex; }

    public static Matcher getMatcher(String command, GovernmentMenuCommands governmentMenuCommands) {
        Matcher matcher = Pattern.compile(governmentMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
