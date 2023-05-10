package control;

import model.Game;
import model.government.building.Market;
import model.government.resource.Resource;
import view.enums.messages.StoreMenuMessage;

public class StoreControl {
    public static String showPriceList() {
        Market market = (Market) Game.getSelectedBuilding();
        String priceList = "******price list of this market******";
        if (market.getResources().isEmpty())
            return "has nothing resources";
        for (Resource resource : market.getResources().keySet()) {
            priceList +=  "\n" + resource.toString() + "   " + market.getResources().get(resource);
        }
        return priceList;
    }
    public static StoreMenuMessage buyFromStore(String itemName, int amount) {
        return null;
    }

    public static StoreMenuMessage sellFromStore(String itemName, int amount) {
        return null;
    }
}
