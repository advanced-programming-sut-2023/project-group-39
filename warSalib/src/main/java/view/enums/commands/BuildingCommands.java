package main.java.view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingCommands {
 ;
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
