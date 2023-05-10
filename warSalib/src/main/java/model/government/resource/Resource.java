package model.government.resource;

public enum Resource {
    STONE(TypeOfResource.INDUSTRY , 15), IRON(TypeOfResource.INDUSTRY, 20),
    PITCH(TypeOfResource.INDUSTRY, 7), WHEAT(TypeOfResource.FOOD, 5),
    FLOUR(TypeOfResource.FOOD , 3), BREAD(TypeOfResource.FOOD, 8),
    HOP(TypeOfResource.FOOD, 4), BEAR(TypeOfResource.FOOD, 30),
    ARMOUR(TypeOfResource.WEAPON, 90), SWORD(TypeOfResource.WEAPON, 70),
    ARCHER(TypeOfResource.WEAPON, 90), SPEAR(TypeOfResource.WEAPON, 50),
    WOOD(TypeOfResource.INDUSTRY, 3), OIL(TypeOfResource.INDUSTRY, 15),
    HORSE(TypeOfResource.INDUSTRY, 100), APPLE(TypeOfResource.FOOD, 10),
    CHEESE(TypeOfResource.FOOD, 12), MEAT(TypeOfResource.FOOD, 20), GOLD(TypeOfResource.INDUSTRY, 30),
    COIN(TypeOfResource.INDUSTRY, 1), COW(TypeOfResource.ANIMAL, 70);
    public enum TypeOfResource {
        INDUSTRY, WEAPON, FOOD,ANIMAL
    }

    TypeOfResource typeOfResource;

    private int cost;

    Resource(TypeOfResource typeOfResource, int cost) {
        this.typeOfResource = typeOfResource;
        this.cost = cost;
    }

    public TypeOfResource getTypeOfResource() {
        return typeOfResource;
    }
}