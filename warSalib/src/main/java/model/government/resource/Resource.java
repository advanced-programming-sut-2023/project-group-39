package model.government.resource;

public enum Resource {
    STONE(TypeOfResource.INDUSTRY), IRON(TypeOfResource.INDUSTRY), PITCH(TypeOfResource.INDUSTRY), WHEAT(TypeOfResource.FOOD),
    FLOUR(TypeOfResource.FOOD), BREAD(TypeOfResource.FOOD), HOP(TypeOfResource.FOOD), BEAR(TypeOfResource.FOOD),
    ARMOUR(TypeOfResource.WEAPON), SWORD(TypeOfResource.WEAPON), ARCHER(TypeOfResource.WEAPON), SPEAR(TypeOfResource.WEAPON),
    WOOD(TypeOfResource.INDUSTRY), OIL(TypeOfResource.INDUSTRY), HORSE(TypeOfResource.INDUSTRY), APPLE(TypeOfResource.FOOD),
    CHEESE(TypeOfResource.FOOD), MEAT(TypeOfResource.FOOD), GOLD(TypeOfResource.INDUSTRY), COIN(TypeOfResource.INDUSTRY),
    COW(TypeOfResource.ANIMAL);
    public enum TypeOfResource {
        INDUSTRY, WEAPON, FOOD,ANIMAL
    }

    TypeOfResource typeOfResource;

    Resource(TypeOfResource typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public TypeOfResource getTypeOfResource() {
        return typeOfResource;
    }
}