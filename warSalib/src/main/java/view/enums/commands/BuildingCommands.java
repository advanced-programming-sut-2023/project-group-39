package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingCommands {
    DROP_BUILDING("^\\s*dropbuilding\\s+(-x\\s+(?<x>\\-?\\d+)\\s*()|-type\\s+(?<type>\\w+|\".*\")\\s*()|-y\\s+(?<y>\\-?\\d+)\\s*()){3}\\3\\5\\7$"),
    SELECT_BUILDING("^select\\s+building\\s+(-x\\s+(?<x>\\-?\\d+)\\s*()|-y\\s+(?<y>\\-?\\d+)\\s*()){2}\\3\\5$"),
    //change type into t
    CREATE_UNIT("^createunit\\s+(-type\\s+(?<type>\\w+|\\\".*\\s+.*\\\")\\s*()|-count\\s+(?<count>\\-?\\d+)\\s*()){2}\\3\\5$");
    private String regex;

    BuildingCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, BuildingCommands buildingCommands) {
        Matcher matcher = Pattern.compile(buildingCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
