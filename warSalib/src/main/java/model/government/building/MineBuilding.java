package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

public class MineBuilding extends Building{
    //change Group by GroupOfBuilding.Mine
    private int rate;
    private Resource resource;
    private MineBuilding(int x , int y , Government government, int hp, String type, int rate, Resource resource) {
        super(x,y,government,hp, type);
        this.rate=rate;
        this.resource = resource;
    }
    public static MineBuilding makeMineBuildingByName(String Name) {
        return null;
    }

    private static boolean isAppropriateGround(String ground) {
        return false;
    }

    public int makeResourceWithRate() {
        return 0;
    }

}
