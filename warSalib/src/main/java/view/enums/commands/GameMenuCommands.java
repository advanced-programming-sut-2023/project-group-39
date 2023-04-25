package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SELECTUNIT("select\\s+unit\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()|\\-t\\s+(?<type>[\\S]+)()){3}\\3\\5\\7$"),

    MOVEUNIT("move\\s+unit\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}\\3\\5$"),

    PATROLUNIT("patrol\\s+unit\\s+(\\-x1\\s+(?<x1>[\\d]+)\\s*()|\\-y1\\s+(?<y1>[\\d]+)\\s*()|\\-x2\\s+(?<x2>[\\d]+)\\s*()|\\-y2\\s+(?<y2>[\\d]+)\\s*()){4}\\3\\5\\7\\9$"),

    SETMOODE("set\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()|\\-s\\s+(?<moode>[\\S]+)\\s*()){3}\\3\\5\\7$"),

    ATTACK("attack\\s+-e\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)()){2}\\3\\5$"),

    AIRATTACK("attack\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}{2}\\3\\5$"),

    POUROIL("pour\\s+oil\\s+-d\\s+(?<directin>[\\S]+)\\s*"),

    DIGTUNNEL("dig\\s+tunnel\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}\\3\\5$"),

    BUILDEQUIPMENT("build\\s+-q\\s+(?<equipment>.+)"),

    DISBANDUNIT("disband\\s+unit"),

    MAKEGATE("make\\s+gate\\s+(?<direction>.+)"),

    MAKEWALL("make\\s+wall(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()|\\-width\\s+(?<width>[\\d]+)\\s*()|\\-height\\s+(?<height>[\\d]+)\\s*()){4}\\3\\5\\7\\9$"),

   MAKETOWER("make\\s+(?<type>[\\S]+)\\s+tower"),

    MAKETALE("make\\s+tale(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}\\3\\5$"),

    MAKEKILLERTALE("make\\s+killer\\s+tale\\s+(\\-x\\s+(?<x>[\\d]+)()|\\-y\\s+(?<y>[\\d]+)\\s*())");

   // MAKE

    private String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, GameMenuCommands gameMenuCommands) {
        Matcher matcher = Pattern.compile(gameMenuCommands.regex).matcher(command);
        if (matcher.find())
            return matcher;
        return null;
    }
    }
