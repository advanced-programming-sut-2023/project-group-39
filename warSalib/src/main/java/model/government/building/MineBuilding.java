package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;
import model.map.type.Type;

import java.util.HashMap;

public class MineBuilding extends Building{
    //change Group by GroupOfBuilding.Mine
    private int rate;
    private Resource resource;

    public MineBuilding(int x, int y, Government government, int hp, String name, int rate, Resource resource) {
        super(x, y, government, hp, "industry", name, hp);
        this.rate = rate;
        this.resource = resource;
    }

    public static MineBuilding makeMineBuildingByName(String name , int x, int y, Government government) {
        if (name.equals("quarry") ) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource)) {
                MineBuilding mineBuilding = new MineBuilding(x, y, government, 1000, name, 20, Resource.STONE);
                //mineBuilding.setWorkerDataBase();
                return mineBuilding;
            }
        }
        if (name.equals("iron mine") ) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource)) {
                MineBuilding mineBuilding = new MineBuilding(x, y, government, 1100, name, 15, Resource.IRON);
                //mineBuilding.setWorkerDataBase();
                return mineBuilding;
            }
        }
        if (name.equals("pitch rig") ) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource)) {
                MineBuilding mineBuilding = new MineBuilding(x, y, government, 800, name, 30, Resource.PITCH);
               // mineBuilding.setWorkerDataBase();
                return mineBuilding;
            }
        }
        return null;
    }


    public int makeResourceWithRate() {
        //need to time to handle it
        return 0;
    }

}
