package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MAP("^\\s*show\\s+map\\s+(\\-x\\s+\\-?\\d+\\s+\\-y\\s+\\-?\\d+\\s*|\\-y\\s+\\-?\\d+\\s+\\-x\\s+\\-?\\d+\\s*)$"),
    MAP_CHECK_X("^.+\\-x\\s+(?<x>\\-?\\d+).*$"),
    MAP_CHECK_Y("^.+\\-y\\s+(?<y>\\-?\\d+).*$");

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
