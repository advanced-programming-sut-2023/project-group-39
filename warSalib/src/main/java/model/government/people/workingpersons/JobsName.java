package model.government.people.workingpersons;

import model.government.building.Building;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;
import model.wartool.wartoolenum;

public enum JobsName {
    TUNNELER("tunneler",null,null),
    SHOPPER("shopper",GroupOfBuilding.MARKET.getGroup()[0],null),
    DRINKSERVER("drinkserver",GroupOfBuilding.INN.getGroup()[0],null),
    WOODCUTTER("woodcutter",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[2],Resource.WOOD),
    POLETURNER("poleturner",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[3],Resource.IRON),
    FLETCHER("fletcher",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[2],Resource.WOOD),
    BLACKSMITH("blacksmith",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[1],Resource.IRON),
    ARMOUR("armour",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[0],Resource.IRON),
    BUTCHER("butcher",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[8],Resource.COW),
    BREWER("brewer",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[6],Resource.HOP),
    APPLEFARMER("applefarmer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[0],null),
    MILLER("miller",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[4],Resource.WHEAT),
    PRIEST("priest",GroupOfBuilding.CHURCH.getGroup()[0],null),
    DIARYPRODUCER("diaryproducer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[7],Resource.COW),
    HOPFARMER("hopfarmer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[9],null),
    WHEATFARMER("wheatfarmer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[4],null),
    BAKER("baker",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[5],Resource.FLOUR),
    UNEMPLOYED("unemployed",GroupOfBuilding.HOVEL.getGroup()[0],null);

    private String jobsName;
    private String workingPlace;
    private Resource resource;
    JobsName(String jobsName,String workingPlace,Resource resource){
        this.jobsName=jobsName;
        this.workingPlace=workingPlace;
        this.resource=resource;
    }

    public Resource getResource() {
        return resource;
    }

    public String getJobsName() {
        return jobsName;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }
}
