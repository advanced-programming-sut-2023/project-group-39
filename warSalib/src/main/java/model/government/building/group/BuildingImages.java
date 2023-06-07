package model.government.building.group;

import javafx.scene.image.Image;
import model.government.building.Building;

import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildingImages {
    private static final ArrayList<Image> militaryBuilding = new ArrayList<>();

    private static final ArrayList<Image> buildBuilding = new ArrayList<>();
    private final static Image armourer = new Image(BuildingImages.class.getResource("/images/aumourer.png").toExternalForm());
    private final static Image gateHouse = new Image(BuildingImages.class.getResource("/images/gateHouse.png").toExternalForm());
    private final static Image dogCage = new Image(BuildingImages.class.getResource("/images/dogcage.png").toExternalForm());
    private final static Image drawBridge = new Image(BuildingImages.class.getResource("/images/drawbridge.png").toExternalForm());
    private final static Image engineerGuild =  new Image(BuildingImages.class.getResource("/images/engineer_guild.png").toExternalForm());
    private final static Image fletcher =  new Image(BuildingImages.class.getResource("/images/fletcher.png").toExternalForm());
    private final static Image hunter =  new Image(BuildingImages.class.getResource("/images/hunter.png").toExternalForm());
    private final static Image keep =  new Image(BuildingImages.class.getResource("/images/keep1.png").toExternalForm());
    private final static Image mercenary =  new Image(BuildingImages.class.getResource("/images/mercenary.png").toExternalForm());

    private final static Image poleturnner =  new Image(BuildingImages.class.getResource("/images/poleturnner.png").toExternalForm());
    private final static Image stable =  new Image(BuildingImages.class.getResource("/images/stables.png").toExternalForm());
    private final static Image squareTower = new Image(BuildingImages.class.getResource("/images/squareTower.png").toExternalForm());
    private final static Image lookoutTower = new Image(BuildingImages.class.getResource("/images/lookoutTower.png").toExternalForm());
    private final static Image circularTower = new Image(BuildingImages.class.getResource("/images/circularTower.png").toExternalForm());
    private final static Image defensiveTower = new Image(BuildingImages.class.getResource("/images/defensiveTower.png").toExternalForm());
    private final static Image perimeterTower = new Image(BuildingImages.class.getResource("/images/premTower.png").toExternalForm());
    private final static Image blackSmith = new Image(BuildingImages.class.getResource("/images/blackSmith.png").toExternalForm());
    private final static Image barrack = new Image(BuildingImages.class.getResource("/images/barracks3.png").toExternalForm());
    private final static Image killingPit = new Image(BuildingImages.class.getResource("/images/kilintpit.png").toExternalForm());
    private final static Image tent = new Image(BuildingImages.class.getResource("/images/tent.png").toExternalForm());
    private final static Image pitchDitch = new Image(BuildingImages.class.getResource("/images/pitchDich.png").toExternalForm());
    private final static Image armoury = new Image(BuildingImages.class.getResource("/images/armoury.png").toExternalForm());

    private final static Image inn = new Image(BuildingImages.class.getResource("/images/inn.png").toExternalForm());

    private final static Image hovel = new Image(BuildingImages.class.getResource("/images/hovel.gif").toExternalForm());



    public static ArrayList<Image> getMilitaryBuilding (){
        if (militaryBuilding.size() == 0) {
            militaryBuilding.add(keep);
            militaryBuilding.add(armourer);
            //militaryBuilding.add(gateHouse);
            militaryBuilding.add(dogCage);
            //militaryBuilding.add(drawBridge);
            militaryBuilding.add(blackSmith);
            militaryBuilding.add(barrack);
            militaryBuilding.add(killingPit);
            militaryBuilding.add(tent);
            militaryBuilding.add(pitchDitch);
            militaryBuilding.add(armoury);
            militaryBuilding.add(engineerGuild);
            militaryBuilding.add(fletcher);
            militaryBuilding.add(mercenary);
            militaryBuilding.add(poleturnner);
            militaryBuilding.add(stable);
//            militaryBuilding.add(lookoutTower);
//            militaryBuilding.add(circularTower);
//            militaryBuilding.add(defensiveTower);
//            militaryBuilding.add(squareTower);
        }
        return militaryBuilding;
    }
    public static ArrayList<Image> getBuildBuilding(){
        if (buildBuilding.size() == 0){
            buildBuilding.add(gateHouse);
            buildBuilding.add(drawBridge);
            buildBuilding.add(lookoutTower);
            buildBuilding.add(circularTower);
            buildBuilding.add(defensiveTower);
            buildBuilding.add(squareTower);
            buildBuilding.add(inn);
            buildBuilding.add(hovel);
            buildBuilding.add(perimeterTower);
        }
        return buildBuilding;
    }
}

