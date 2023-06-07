package model.government.people.units;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.user.User;
import view.StartGame;

import java.util.ArrayList;

public class Armys extends Units {
    public ArrayList<SpecialWorks> specialWorks = new ArrayList<>();

    public Armys(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName,ownerPerson);
        if (unitsName.getName().equals("sperman")) {
            specialWorks.add(SpecialWorks.CANDIGMOAT);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
            ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/spearman.png").toExternalForm()));
            this.setFill(humanImage);
        } else if (unitsName.getName().equals("maceman")) {
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
            ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/maceman.png").toExternalForm()));
            this.setFill(humanImage);
        }
        else if(unitsName.getName().equals("tunneler")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
            ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/tunneler.png").toExternalForm()));
            this.setFill(humanImage);
        }
        else if(unitsName.getName().equals("ladderman")){
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
            ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/ladderman.png").toExternalForm()));
            this.setFill(humanImage);
        }
        else if(unitsName.getName().equals("assassin")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
            ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/assasin.png").toExternalForm()));
            this.setFill(humanImage);
        }
    }

    public ArrayList<SpecialWorks> getSpecialWorks() {
        return specialWorks;
    }
}
