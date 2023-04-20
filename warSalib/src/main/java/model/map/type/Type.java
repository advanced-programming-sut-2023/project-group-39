package main.java.model.map.type;

public enum Type {
    ;

    enum Area {
        BLUEAREA,BROWNAREA;
    }

    private Area area;
    private int permeability;

    Type(Area area, int permeability) {
        this.area = area;
        this.permeability = permeability;
    }

    public Area getArea() {
        return area;
    }

    public int getPermeability() {
        return permeability;
    }
}
