package model.government.building.group;

import java.util.ArrayList;

public enum GroupOfBuilding {
    CHURCH("church" , "cathedral") ,
    CONVERTED_BUILDING("armourer" , "blacksmith" , "fletcher" , "pole turner" , "mill" , "bakery" , "brewery") ,
    DRAWBRIDGE("draw bridge") ,
    GATEHOUSE("small stone gatehouse" , "big stone gatehouse") ,
    HOVEL("hovel") ,
    INN("inn") ,
    KILLING_PIT("killing pit") ,
    MAKING_FORCE_BUILDING("barrack" , "engineer guild" , "mercenary post") ,
    MARKET("market") ,
    MINE_BUILDING("quarry" , "pitch rig" , "iron mine") ,
    OX_TETHER("ox tether") ,
    PITCH_DITCH("pitch ditch") ,
    PRODUCTIVE_BUILDING("apple garden" , "oil smelter" , "woodcutter" , "barley field" , "wheat field" , "hunting post" , "stable") ,
    STOCK_PILE_BUILDING("armoury" ,"stock pile" , "food stock pile") ,
    TOWER("lookout tower" , "perimeter tower" , "circular tower" , "square tower" , "defensive tower") ,
    WALL("great wall" , "small wall");

    private String []group;

    GroupOfBuilding(String ... group) {
        this.group = group;
    }
}
