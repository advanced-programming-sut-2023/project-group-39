package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    TRADE( "^\\s*trade\\s+-t\\s+(?<resourceType>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+-t\\s+(?<resourceType>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-m\\s+(?<message>.+)\\s+-p\\s+(?<price>\\S+)$" +
            "|^\\s*trade\\s+-t\\s+(?<resourceType>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+-t\\s+(?<resourceType>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-m\\s+(?<message>.+)\\s+-a\\s+(?<resourceAmount>\\S+)$" +
            "|^\\s*trade\\s+-t\\s+(?<resourceType>\\S+)\\s+-m\\s+(?<message>.+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-p\\s+(?<price>\\S+)$" +
            "|^\\s*trade\\s+-t\\s+(?<resourceType>\\S+)\\s+-m\\s+(?<message>.+)\\s+-p\\s+(?<price>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)$" +
            "|^\\s*trade\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-m\\s+(?<message>.+)\\s+-p\\s+(?<price>\\S+)$" +
            "|^\\s*trade\\s+-p\\s+(?<price>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+-p\\s+(?<price>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-m\\s+(?<message>.+)\\s+-a\\s+(?<resourceAmount>\\S+)$" +
            "|^\\s*trade\\s+-m\\s+(?<message>.+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-p\\s+(?<price>\\S+)$" +
            "|^\\s*trade\\s+-m\\s+(?<message>.+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)$" +
            "|^\\s*trade\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-m\\s+(?<message>.+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-p\\s+(?<price>\\S+)$" +
            "|^\\s*trade\\s+-p\\s+(?<price>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+-p\\s+(?<price>\\S+)\\s+-m\\s+(?<message>.+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)$" +
            "|^\\s*trade\\s+-m\\s+(?<message>.+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-p\\s+(?<price>\\S+)$" +
            "|^\\s*trade\\s+-m\\s+(?<message>.+)\\s+-p\\s+(?<price>\\S+)\\s+-t\\s+(?<resourceType>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)$" +
            "|^\\s*trade\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-m\\s+(?<message>.+)\\s+-t\\s+(?<resourceType>\\S+)$" +
            "|^\\s*trade\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-m\\s+(?<message>.+)\\s+-p\\s+(?<price>\\S+)\\s+-t\\s+(?<resourceType>\\S+)$" +
            "|^\\s*trade\\s+-p\\s+(?<price>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-m\\s+(?<message>.+)\\s+-t\\s+(?<resourceType>\\S+)$" +
            "|^\\s*trade\\s+-p\\s+(?<price>\\S+)\\s+-m\\s+(?<message>.+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-t\\s+(?<resourceType>\\S+)$" +
            "|^\\s*trade\\s+-m\\s+(?<message>.+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-p\\s+(?<price>\\S+)\\s+-t\\s+(?<resourceType>\\S+)$" +
            "|^\\s*trade\\s+-m\\s+(?<message>.+)\\s+-p\\s+(?<price>\\S+)\\s+-a\\s+(?<resourceAmount>\\S+)\\s+-t\\s+(?<resourceType>\\S+)$"),
    SHOW_TRADE_LIST("^\\s*trade\\s+list\\s*$"),
    ACCEPT_TRADE("^\\s*trade\\s+accept\\s+-i\\s+(?<id>\\S+)\\s+-m\\s+(?<message>.+)$" +
            "|^\\s*trade\\s+accept\\s+-m(?<message>.+)\\s+-i\\s+(?<id>\\S+)\\s*$"),
    SHOW_TRADE_HISTORY("^\\s*trade\\s+history\\s*");

    private String regex;

    TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, TradeMenuCommands tradeMenuCommands) {
        Matcher matcher = Pattern.compile(tradeMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
