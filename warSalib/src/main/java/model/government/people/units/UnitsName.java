package model.government.people.units;

import model.wartool.wartoolenum;

public enum UnitsName {
    ARCHER("archer", 75, 25, 25,  UnitsType.ARCHER, false),

    CROSSBOWMEN("crossbowmen", 20, 25,  100, UnitsType.ARCHER, false),

    SPEARMAN("spear man", 60, 50, 15,  UnitsType.ARMY, false),

    PIKE_MAN("pike man", 20, 50, 75,  UnitsType.COMBAT, true),

    MACE_MAN("mace man", 50, 75, 50, UnitsType.ARMY, false),

    SWORDSMEN("swords men", 10, 85, 85,  UnitsType.COMBAT, true),

    KNIGHT("knight", 85, 85, 80,  UnitsType.COMBAT, false),

    TUNNELER("tunneler", 75, 50, 10, UnitsType.ARMY, false),

    LADDERMAN("ladder man", 75, 0, 10,  UnitsType.ARMY, false),

    BLACKMONK("black monk", 25, 50, 50,  UnitsType.COMBAT, true),

    //arab people
    ARCHER_BOW("archer bow", 75, 25, 25, UnitsType.ARCHER, false),

    SLAVES("slaves", 75, 10, 10,  UnitsType.COMBAT, false),

    SLINGERS("slingers", 75, 25, 10,  UnitsType.ARCHER, false),

    ASSASSINS("assassin", 50, 50, 50,  UnitsType.ARMY, false),

    HORSE_ARCHERS("horse archers", 85, 25, 50,  UnitsType.ARCHER, false),

    ARABIAN_SWORDSMEN("arabian swordsmen", 85, 75, 75,  UnitsType.COMBAT, false),

    FIRE_TOWERS("fire towers", 85, 75, 25,  UnitsType.ARCHER, false);


    private String name;
    private int speed;
    private int attackingPower;
    private int defensingPower;
    private UnitsType unitsType;

    private boolean hasArmour;

    private int cost;

    UnitsName(String name, int speed, int attackingPower, int defensingPower,  UnitsType unitsType, boolean hasArmour, int cost) {
        this.name = name;
        this.speed = speed;
        this.attackingPower = attackingPower;
        this.defensingPower = defensingPower;
        this.unitsType = unitsType;
        this.hasArmour = hasArmour;
        this.cost = cost;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackingPower() {
        return attackingPower;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getDefensingPower() {
        return defensingPower;
    }

    public UnitsType getUnitsType() {
        return unitsType;
    }
}
