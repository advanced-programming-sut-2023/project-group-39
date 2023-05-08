package control;

import model.Game;
import model.government.popularityfactor.Fear;
import model.government.popularityfactor.Food;
import model.government.popularityfactor.Religion;
import model.government.popularityfactor.Tax;
import model.government.request.Request;
import model.government.resource.Resource;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.GovernmentMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GovernmentControl {
    public static GovernmentMenuMessage changePopulation() { return null; }

    public static GovernmentMenuMessage addToResources(Resource resource) { return null; }

    public static GovernmentMenuMessage removeFromResources(Resource resource) { return null; }

    public static GovernmentMenuMessage changeWorkersActivities(int fearRate) { return null; }

    public static GovernmentMenuMessage changeSoldiersMorality(int fearRate) { return null; }

    public static GovernmentMenuMessage rateFood(int rate) { return null; }

    public static GovernmentMenuMessage rateTax(int rate) { return null; }

    public static GovernmentMenuMessage rateFear(int rate) { return null; }

    public static GovernmentMenuMessage addToFoods(Food food, int numberOfFoods) {
        if(food==null){
            return GovernmentMenuMessage.INVALIDFOODNAME;
        }
        int value=0;
       for(Map.Entry<Resource,Integer> foodResources:Game.getCurrentUser().getUserGovernment().getResources().entrySet()){
           if(food.getFoodname().equals(foodResources.getKey())){
               value=foodResources.getValue();
               break;
           }

       }
       if(numberOfFoods>value){
           return GovernmentMenuMessage.NOT_ENOUGH_INVENTORY;
       }
       for(Map.Entry<Food,Integer> foods:Game.getCurrentUser().getUserGovernment().getFoods().entrySet()){   //is it okay?
           if(food.getFoodname().equals(foods.getKey().getFoodname())){
              Game.getCurrentUser().getUserGovernment().getFoods().replace(foods.getKey(),foods.getValue(),foods.getValue()+numberOfFoods);
              return GovernmentMenuMessage.SUCCESS;
           }
       }




        return null;
    }
    public static GovernmentMenuMessage showFoodList(){
        return null;
    }

    public static GovernmentMenuMessage removeFromFoods(Food food, int numberOfFoods) { return null; }
}
