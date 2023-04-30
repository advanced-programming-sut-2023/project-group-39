package control;

import model.Game;
import model.government.Government;
import model.government.resource.Resource;
import view.TradeMenu;
import view.enums.messages.TradeMenuMessage;

import java.util.HashMap;

public class TradeControl {
    public static TradeMenuMessage trade(String itemName, int amount, int price, String message) {
        Government government = Game.getCurrentUser().getUserGovernment();

        if (amount <= 0 || price < 0)
            return TradeMenuMessage.INVALID_AMOUNT_OR_PRICE;

        else if (!government.getResources().containsKey(itemName))
            return TradeMenuMessage.ITEM_NOT_EXIST;

        HashMap<Resource, Integer> tradeListValue = new HashMap<>();
        tradeListValue.put(Resource.valueOf(itemName), amount);
        TradeMenu.tradeList.put(government, tradeListValue);
        return TradeMenuMessage.SUCCESS;
    }

    public static String showTradeList() {
        StringBuilder result = new StringBuilder();

        if (TradeMenu.tradeList.isEmpty())
            return String.valueOf(TradeMenuMessage.EMPTY_TRADE_LIST);

        result.append("Trade List:");
        for (Government government : TradeMenu.tradeList.keySet())
            result.append("\nID: ")
                    .append(government.getUser().getUsername())
                    .append("\t|\tResource: ")
                    .append(TradeMenu.tradeList.get(government).keySet())
                    .append("\t|\tAmount: ")
                    .append(TradeMenu.tradeList.get(government).values());

        return result.toString();
    }

    public static TradeMenuMessage acceptTrade(String id, String message) {
        return null;
    }
    public static String showTradeHistory() {
        return null;
    }

    private static boolean validIdForAccept (String id) {
        return false;
    }
}
