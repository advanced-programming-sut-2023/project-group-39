package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands {
    ;

    private String regex;

    StoreMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, StoreMenuCommands storeMenuCommands) {
        Matcher matcher = Pattern.compile(storeMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
