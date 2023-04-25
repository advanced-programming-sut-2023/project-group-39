package model.map.type;

public enum Type {
    GROUND(Area.BROWNAREA, true), GROUND_WITH_GRAVEL(Area.BROWNAREA, true)

    ,SLATE(Area.BROWNAREA, true), STONE(Area.BROWNAREA, false),
    IRON_GROUND(Area.BROWNAREA, true), GRASS(Area.BROWNAREA, true),
    GRASSLAND(Area.BROWNAREA, true), DENSE_GRASSLAND(Area.BROWNAREA, true) ,
    OIL(Area.BLUEAREA, true), PLAIN(Area.BLUEAREA, true),
    SHALLOW_WATER(Area.BLUEAREA, true), SEA(Area.BLUEAREA, false)
    ,BEACH(Area.BLUEAREA, true), RIVER(Area.BLUEAREA, false),
    BIG_POND(Area.BLUEAREA, false), SMALL_POND(Area.BLUEAREA, false);

    enum Area {
        BLUEAREA,BROWNAREA;
    }

    private Area area;
    private boolean permeability;

    Type(Area area, boolean permeability) {
        this.area = area;
        this.permeability = permeability;
    }

    public Area getArea() {
        return area;
    }

    public boolean getPermeability() {
        return permeability;
    }
}
