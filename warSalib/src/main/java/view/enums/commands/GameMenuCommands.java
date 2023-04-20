package main.java.view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    ;


    private String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, GameMenuCommands gameMenuCommands) {
        Matcher matcher = Pattern.compile(gameMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
