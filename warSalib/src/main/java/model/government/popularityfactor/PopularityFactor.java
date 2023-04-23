package model.government.popularityfactor;

abstract public class PopularityFactor {
    static int rate;
    //TODO : check is work static or not
    public static int getRate() {
        return rate;
    }

    public static void setRate(int newRate) {
        rate = newRate;
    }
}
