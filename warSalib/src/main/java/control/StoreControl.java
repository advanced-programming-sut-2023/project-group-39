package control;

import model.Game;
import model.government.building.Market;
import model.government.resource.Resource;
import view.enums.messages.StoreMenuMessage;

import java.util.HashMap;

public class StoreControl {
    public static String showPriceList() {
        Market market = (Market) Game.getSelectedBuilding();
        String priceList = "******price list of this market******";
        if (market.getResources().isEmpty())
            return "has nothing resources";
        for (Resource resource : market.getResources().keySet()) {
            priceList +=  "\n" + "name :" + resource.getName() + "   " + "  number :"  +
                    market.getResources().get(resource) + "  cost : " +resource.getCost();
        }
        return priceList;
    }
    public static StoreMenuMessage buyFromStore(String itemName, int amount) {
        if (!validAmount(amount))
            return StoreMenuMessage.WRONG_AMOUNT;
        Resource resource;
        if ((resource = getResourceByItemName(itemName)) == null)
            return StoreMenuMessage.WRONG_ITEM;
        Market market = (Market) Game.getSelectedBuilding();
        if (market.getGovernment().getWealth() < (float) (resource.getCost() * amount))
            return StoreMenuMessage.DONT_HAVE_BUDGET;
        market.buyResource(resource, amount);
        market.getGovernment().setWealth(market.getGovernment().getWealth() - (resource.getCost() * amount));
        market.getGovernment().addToResources(resource, amount);
        return StoreMenuMessage.SUCCESS;
    }

    private static boolean validAmount (int amount) {
        if (amount > 0)
            return true;
        return false;
    }

    private static Resource getResourceByItemName (String item) {
        for (Resource resource : Resource.values()) {
            if (resource.getName().equals(item))
                return resource;
        }
        return null;
    }

    public static StoreMenuMessage sellFromStore(String itemName, int amount) {
        if (!validAmount(amount))
            return StoreMenuMessage.WRONG_AMOUNT;
        Resource resource;
        if ((resource = getResourceByItemName(itemName)) == null)
            return StoreMenuMessage.WRONG_ITEM;
        Market market = (Market) Game.getSelectedBuilding();
        HashMap <Resource,Integer> resources = new HashMap<>();
        resources.put(resource, amount);
        if (!market.getGovernment().hasEnoughResources(resources))
            return StoreMenuMessage.NOT_ENOUGH_RESOURCE;
        market.sellResource(resource, amount);
        market.getGovernment().setWealth(market.getGovernment().getWealth() + (resource.getCost() * amount));
        return StoreMenuMessage.SUCCESS;
    }
}
