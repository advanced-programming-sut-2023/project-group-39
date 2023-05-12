package model.map.type;

public enum Type {
    GROUND(Area.BROWNAREA, true, "G", "ground"),
    GROUND_WITH_GRAVEL(Area.BROWNAREA, true,"Gg", "ground with gravel")

    ,SLATE(Area.BROWNAREA, true, "Sl" , "slate"),

    STONE(Area.BROWNAREA, false ,"St", "stone"),
    IRON_GROUND(Area.BROWNAREA, true, "Ig", "iron ground"),
    GRASS(Area.BROWNAREA, true, "Gr", "grass"),
    GRASSLAND(Area.BROWNAREA, true, "Gl", "grass land"),
    DENSE_GRASSLAND(Area.BROWNAREA, true, "Dg", "dense grass land") ,
    OIL(Area.BLUEAREA, true, "O", "oil"),
    PLAIN(Area.BLUEAREA, true, "P", "plain"),
    SHALLOW_WATER(Area.BLUEAREA, true, "Sw", "shallow water"),
    SEA(Area.BLUEAREA, false, "Se", "sea")

    ,BEACH(Area.BLUEAREA, true, "Be", "beach"),
    RIVER(Area.BLUEAREA, false, "R", "river"),
    BIG_POND(Area.BLUEAREA, false, "Bp", "big pound"),
    SMALL_POND(Area.BLUEAREA, false, "Sp", "small pound");

    public enum Area {
        BLUEAREA,BROWNAREA;
    }

    private Area area;
    private String name;
    private boolean permeability;
    private String ground;

    Type(Area area, boolean permeability , String name, String ground) {
        this.area = area;
        this.permeability = permeability;
        this.name = name;
        this.ground = ground;
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

    public String getGround() {
        return ground;
    }
}
