package model.government.resource;

public enum Resource {
    STONE(TypeOfResource.INDUSTRY), IRON(TypeOfResource.INDUSTRY), PITCH(TypeOfResource.INDUSTRY), WHEAT(TypeOfResource.FOOD),
    FLOUR(TypeOfResource.FOOD), BREAD(TypeOfResource.FOOD), HOP(TypeOfResource.FOOD), BEAR(TypeOfResource.FOOD),
    ARMOUR(TypeOfResource.WEAPON), SWORD(TypeOfResource.WEAPON), ARCHER(TypeOfResource.WEAPON), SPEAR(TypeOfResource.WEAPON),
    WOOD(TypeOfResource.INDUSTRY), OIL(TypeOfResource.INDUSTRY), HORSE(TypeOfResource.INDUSTRY), APPLE(TypeOfResource.FOOD),
    CHEESE(TypeOfResource.FOOD), MEAT(TypeOfResource.FOOD), GOLD(TypeOfResource.INDUSTRY);

    private enum TypeOfResource {
        INDUSTRY, WEAPON, FOOD
    }

    TypeOfResource typeOfResource;

    Resource(TypeOfResource typeOfResource) {
        this.typeOfResource = typeOfResource;
    }
}