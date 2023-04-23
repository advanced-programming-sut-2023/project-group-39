package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

public class MineBuilding extends Building{
    //change Group by GroupOfBuilding.Mine
    private int rate;
    private Resource resource;

    public MineBuilding(int x, int y, Government government, int hp, String name, int rate, Resource resource) {
        super(x, y, government, hp, "industry", name);
        this.rate = rate;
        this.resource = resource;
    }

    public static MineBuilding makeMineBuildingByName(String name , int x, int y, Government government) {
        if (name.equals("quarry") ) {
            MineBuilding mineBuilding = new MineBuilding(x, y, government, 1000, name, 20, Resource.STONE);
            return mineBuilding;
        }
        if (name.equals("iron mine") ) {
            MineBuilding mineBuilding = new MineBuilding(x, y, government, 1100, name, 15, Resource.IRON);
            return mineBuilding;
        }
        if (name.equals("pitch rig") ) {
            MineBuilding mineBuilding = new MineBuilding(x, y, government, 800, name, 30, Resource.PITCH);
            return mineBuilding;
        }
        return null;
    }

    private static boolean isAppropriateGround(String ground) {
        return false;
    }

    public int makeResourceWithRate() {
        //need to time to handle it
        return 0;
    }

}
