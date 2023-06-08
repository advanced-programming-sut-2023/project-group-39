package model.government.building.group;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildingImages {
    private static final ArrayList<Image> militaryBuilding = new ArrayList<>();
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




    public static ArrayList<Image> getMilitaryBuilding (){
        if (militaryBuilding.size() == 0) {
            militaryBuilding.add(keep);
            militaryBuilding.add(armourer);
            militaryBuilding.add(gateHouse);
            militaryBuilding.add(dogCage);
            militaryBuilding.add(drawBridge);
            militaryBuilding.add(engineerGuild);
            militaryBuilding.add(fletcher);
            militaryBuilding.add(mercenary);
            militaryBuilding.add(poleturnner);
            militaryBuilding.add(stable);
        }
        return militaryBuilding;
    }
}

