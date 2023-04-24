package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class DrawBridge extends Building{
    private int upOrDown;

    private int decreasingSpeed;

    public DrawBridge(int x, int y, Government government, int upOrDown) {
        super(x, y, government, 600, "castle building", "draw bridge");
        this.upOrDown = upOrDown;
    }

    public static DrawBridge makeDrawBridgeByName(String name, int x, int y, Government government) {
        if (name.equals("draw bridge")) {
            DrawBridge drawBridge = new DrawBridge(x, y, government, 0);
            return drawBridge;
        }
        return null;
    }
    public int getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(int upOrDown) {
        this.upOrDown = upOrDown;
    }

    public int getDecreasingSpeed() {
        return decreasingSpeed;
    }

}
