package main.java.view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    ;

    private String regex;

    MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, MapMenuCommands mapMenuCommands) {
        Matcher matcher = Pattern.compile(mapMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
