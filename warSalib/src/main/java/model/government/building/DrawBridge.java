package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class DrawBridge extends Building{
    private int upOrDown;

    private double decreasingSpeed = 0.7;

    public DrawBridge(int x, int y, Government government, int upOrDown) {
        super(x, y, government, 600, "castle building", "draw bridge");
        this.upOrDown = upOrDown;
    }

    public static DrawBridge makeDrawBridgeByName(String name, int x, int y, Government government) {
        if (name.equals("draw bridge")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource)) {
                DrawBridge drawBridge = new DrawBridge(x, y, government, 0);
                return drawBridge;
            }
        }
        return null;
    }
    public int getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(int upOrDown) {
        this.upOrDown = upOrDown;
    }

    public double getDecreasingSpeed() {
        return decreasingSpeed;
    }

}
