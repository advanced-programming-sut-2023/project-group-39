package model.government.people.units;

import model.wartool.wartoolenum;

public enum UnitsName {
    ARCHER("archer", 75, 25, 25,  UnitsType.ARCHER, false),

    CROSSBOWMEN("crossbowmen", 20, 25,  100, UnitsType.ARCHER, false),

    SPEARMAN("spearman", 60, 50, 15,  UnitsType.ARMY, false),

    PIKEMAN("pikeman", 20, 50, 75,  UnitsType.COMBAT, true),

    MACEMAN("maceman", 50, 75, 50, UnitsType.ARMY, false),

    SWORDSMEN("swordsmen", 10, 85, 85,  UnitsType.COMBAT, true),

    KNIGHT("knight", 85, 85, 80,  UnitsType.COMBAT, false),

    TUNNELER("tunneler", 75, 50, 10, UnitsType.ARMY, false),

    LADDERMAN("ladderman", 75, 0, 10,  UnitsType.ARMY, false),

    BLACKMONK("blackmonk", 25, 50, 50,  UnitsType.COMBAT, true),

    ARCHERBOW("archerbow", 75, 25, 25, UnitsType.ARCHER, false),

    SLAVES("slaves", 75, 10, 10,  UnitsType.COMBAT, false),

    SLINGERS("slingers", 75, 25, 10,  UnitsType.ARCHER, false),

    ASSASSINS("assassin", 50, 50, 50,  UnitsType.ARMY, false),

    HORSEARCHERS("horsearchers", 85, 25, 50,  UnitsType.ARCHER, false),

    ARABIANSWORDSSMEN("arabianswordsmen", 85, 75, 75,  UnitsType.COMBAT, false),

    FIRETHOWERS("firethowers", 85, 75, 25,  UnitsType.ARCHER, false);


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
