package model.map.type;

public enum Type {
    GROUND(Area.BROWNAREA, true, "G"), GROUND_WITH_GRAVEL(Area.BROWNAREA, true,"Gg")

    ,SLATE(Area.BROWNAREA, true, "Sl"), STONE(Area.BROWNAREA, false ,"St"),
    IRON_GROUND(Area.BROWNAREA, true, "Ig"), GRASS(Area.BROWNAREA, true, "Gr"),
    GRASSLAND(Area.BROWNAREA, true, "Gl"), DENSE_GRASSLAND(Area.BROWNAREA, true, "Dg") ,
    OIL(Area.BLUEAREA, true, "O"), PLAIN(Area.BLUEAREA, true, "P"),
    SHALLOW_WATER(Area.BLUEAREA, true, "Sw"), SEA(Area.BLUEAREA, false, "S")
    ,BEACH(Area.BLUEAREA, true, "Be"), RIVER(Area.BLUEAREA, false, "R"),
    BIG_POND(Area.BLUEAREA, false, "Bp"), SMALL_POND(Area.BLUEAREA, false, "Sp");

    public enum Area {
        BLUEAREA,BROWNAREA;
    }

    private Area area;
    private String name;
    private boolean permeability;

    Type(Area area, boolean permeability , String name) {
        this.area = area;
        this.permeability = permeability;
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public boolean getPermeability() {
        return permeability;
    }

    public String getName() {
        return name;
    }
}
