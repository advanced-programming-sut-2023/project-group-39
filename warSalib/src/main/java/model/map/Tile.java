package model.map;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.government.Government;
import model.government.building.Building;
import model.government.people.People;
import model.government.resource.Resource;
import model.map.rock.Rock;
import model.map.tree.Tree;
import model.map.type.Type;
import model.wartool.BatteringRam;
import model.wartool.CataPult;
import model.wartool.FieryStoneThrower;
import model.wartool.FixedCatapult;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class Tile extends StackPane {
    private Type type;
    private Rock rock;

    private Image image;

    private Government government;
    private Tree tree;
    private Building building;
    private ArrayList <People> peopleOnTile;
    private Resource resource;

    private boolean hasTunnel;

    private boolean hasKillerTale;


    private boolean hasStair;

    private boolean hasOilTale;

    private ArrayList<CataPult> cataPults=new ArrayList<>();

    private ArrayList<BatteringRam> batteringRams=new ArrayList<>();

    private ArrayList<FixedCatapult> fixedCatapults=new ArrayList<>();

    private ArrayList<FieryStoneThrower> fieryStoneThrowers=new ArrayList<>();

    public boolean isHasTunnel() {
        return hasTunnel;
    }

    public Tile() {
        this.type = Type.GROUND;
        peopleOnTile = new ArrayList<>();
        this.hasTunnel=false;
        cataPults=new ArrayList<>();
        batteringRams=new ArrayList<>();
        fieryStoneThrowers=new ArrayList<>();
        fixedCatapults=new ArrayList<>();
        image = this.type.getImage();
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
        this.setMinWidth(Game.getTileSize());
        this.setMinHeight(Game.getTileSize());
    }

    public Type getType() {
        return type;
    }

    public Rock getRock() {
        return rock;
    }

    public Tree getTree() {
        return tree;
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList<People> getPeopleOnTile() {
        return peopleOnTile;
    }

    public Resource getResource() {
        return resource;
    }

    public void removePeople(People people) {
        peopleOnTile.remove(people);
    }

    public void addPeople(People people) {
        peopleOnTile.add(people);
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public void setType(Type type) {
        this.type = type;
        image = type.getImage();
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
    }

    public void setRock(Rock rock) {
        this.rock = rock;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public void clearAllPeopleOnTile() {
        peopleOnTile.clear();
    }
    public void setHasTunnel(boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }

    public boolean isHasStair() {
        return hasStair;
    }

    public void setHasStair(boolean hasStair) {
        this.hasStair = hasStair;
    }

    public boolean isHasKillerTale() {
        return hasKillerTale;
    }

    public void setHasKillerTale(boolean hasKillerTale) {
        this.hasKillerTale = hasKillerTale;
    }

    public void setHasOilTale(boolean hasOilTale) {
        this.hasOilTale = hasOilTale;
    }

    public boolean isHasOilTale() {
        return hasOilTale;
    }

    public ArrayList<CataPult> getCataPults() {
        return cataPults;
    }

    public ArrayList<BatteringRam> getBatteringRams() {
        return batteringRams;
    }

    public ArrayList<FixedCatapult> getFixedCatapults() {
        return fixedCatapults;
    }

    public ArrayList<FieryStoneThrower> getFieryStoneThrowers() {
        return fieryStoneThrowers;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
