package model.government.building;

import model.government.Government;

public class SiegeTent extends Building{
    public SiegeTent(int x, int y, Government government, int hp, String type, String name) {
        super(x, y, government, hp, type, name);
    }

    public static SiegeTent makeSiegeTentByName(String name, int x, int y, Government government) {
        if (name.equals("siege tent")) {
            SiegeTent siegeTent = new SiegeTent(x ,y , government, 40, "castle building" , name);
            return siegeTent;
        }
        return null;
    }
}
