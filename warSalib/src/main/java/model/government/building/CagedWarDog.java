package model.government.building;

import model.animal.Dog;
import model.government.Government;
import model.government.resource.Resource;

import java.util.HashMap;

public class CagedWarDog extends Building {
    private HashMap<Dog, Integer> dogs;
    private boolean isOpen = false;

    public CagedWarDog(int x, int y, Government government, int hp, String type, String name, HashMap<Resource, Integer>
                       resource) {
        super(x, y, government, hp, type, name, 500, resource);
        Dog dog = new Dog();
        dogs = new HashMap<>();
        dogs.put(dog, 5);
    }

    public static CagedWarDog makeCagedWarDogByName(String name, int x, int y, Government government) {
        if (name.equals("caged war dog")) {
            HashMap<Resource, Integer> resource = new HashMap<>();
            resource.put(Resource.WOOD, 10);
            resource.put(Resource.COIN, 100);
            if (government.hasEnoughResources(resource)) {
                CagedWarDog cagedWarDog = new CagedWarDog(x, y, government, 500, "castle building", name,
                        resource);
                return cagedWarDog;
            }
        }
        return null;
    }

    public HashMap<Dog, Integer> getDogs() {
        return dogs;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void openDoor() {

    }
}
