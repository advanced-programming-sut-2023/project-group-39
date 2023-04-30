package model.government.people.units;

import model.wartool.wartoolenum;

public enum UnitsName {
    ARCHER("archer", 60, 25, 25,  UnitsType.ARCHER, false),

    CROSSBOWMEN("crossbowmen", 20, 25,  100, UnitsType.ARCHER, false),

    SPEARMAN("spearman", 40, 50, 15,  UnitsType.ARMY, false),

    PIKEMAN("pikeman", 20, 50, 75,  UnitsType.COMBAT, true),

    MACEMAN("maceman", 40, 75, 50, UnitsType.ARMY, false),

    SWORDSMEN("swordsmen", 20, 85, 85,  UnitsType.COMBAT, true),

    KNIGHT("knight", 80, 85, 80,  UnitsType.COMBAT, false),

    TUNNELER("tunneler", 60, 50, 10, UnitsType.ARMY, false),

    LADDERMAN("ladderman", 60, 0, 10,  UnitsType.ARMY, false),

    BLACKMONK("blackmonk", 20, 50, 50,  UnitsType.COMBAT, true),

    //arab people
    ARCHERBOW("archerbow", 60, 25, 25, UnitsType.ARCHER, false),

    SLAVES("slaves", 60, 10, 10,  UnitsType.COMBAT, false),

    SLINGERS("slingers", 60, 25, 10,  UnitsType.ARCHER, false),

    ASSASSINS("assassin", 40, 50, 50,  UnitsType.ARMY, false),

    HORSEARCHERS("horsearchers", 80, 25, 50,  UnitsType.ARCHER, false),

    ARABIANSWORDSSMEN("arabianswordsmen", 80, 75, 75,  UnitsType.COMBAT, false),

    FIRETHOWERS("firethowers", 80, 75, 25,  UnitsType.ARCHER, false);


    private String name;
    private int speed;
    private int attackingPower;
    private int defensingPower;
    private UnitsType unitsType;

    private boolean hasArmour;


    UnitsName(String name, int speed, int attackingPower, int defensingPower,  UnitsType unitsType, boolean hasArmour) {
        this.name = name;
        this.speed = speed;
        this.attackingPower = attackingPower;
        this.defensingPower = defensingPower;
        this.unitsType = unitsType;
        this.hasArmour = hasArmour;
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

    public int getDefensingPower() {
        return defensingPower;
    }

    public UnitsType getUnitsType() {
        return unitsType;
    }
}
