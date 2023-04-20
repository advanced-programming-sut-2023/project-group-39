package main.java.view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnvironmentMenuCommands {
    ;

    private String regex;

    EnvironmentMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, EnvironmentMenuCommands environmentMenuCommands) {
        Matcher matcher = Pattern.compile(environmentMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
