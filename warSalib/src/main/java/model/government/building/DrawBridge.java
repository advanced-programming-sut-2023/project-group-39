package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;

public class DrawBridge extends Building{
    private int upOrDown;

    private int decreasingSpeed;

    public DrawBridge(int x, int y, Government government, int hp, String type, String name, int upOrDown) {
        super(x, y, government, hp, type, name);
        this.upOrDown = upOrDown;
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
