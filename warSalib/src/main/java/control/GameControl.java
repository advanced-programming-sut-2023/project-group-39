package control;

import model.Game;
import model.government.Government;
import model.government.building.*;
import model.government.people.People;
import model.government.people.units.*;
import model.government.resource.Resource;
import model.map.Tile;
import model.wartool.*;
import view.enums.messages.GameMenuMessage;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameControl {


    private static int counterTurn = 0;


    public static ArrayList<Units> currentUnits;

    public static GameMenuMessage selectUnit(int x, int y, String name) {
        if (x >= 200 || y >= 200 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
        for (People people : tile.getPeopleOnTile()) {
            if (people instanceof Units) {
                if (((Units) people).getUnitsName().getName().equals(name) && people.getOwnerPerson().equals(Game.getTurnedUserForGame())) {
                    currentUnits.add((Units) people);
                }
            }
        }
        if (currentUnits.size() != 0) {
            return GameMenuMessage.SUCCESS;
        } else {
            return GameMenuMessage.WITHOUTUNIT;
        }


    }


    public static GameMenuMessage moveUnit(int x, int y) {
        int v = 200 * 200;
        if (x >= 200 || y >= 200 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        if (!Game.getMapInGame().getMap()[x][y].getType().getPermeability()) {
            return GameMenuMessage.SEA_HIGHHEIGHT;

        }
        ArrayList<ArrayList<Integer>> tilesNeighbors = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            tilesNeighbors.add(new ArrayList<Integer>());
        }
        addNeighbors(tilesNeighbors);
        GameMenuMessage message = printShortestDistance(tilesNeighbors, (200 * currentUnits.get(0).getxLocation()) + currentUnits.get(0).getyLocation(), (200 * x) + y, v);
        return message;
    }

    public static void SpecialMoveUnit(Units units, int x, int y) {
        int v = 200 * 200;
        ArrayList<ArrayList<Integer>> tilesNeighbors = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            tilesNeighbors.add(new ArrayList<Integer>());
        }
        addNeighbors(tilesNeighbors);
        GameMenuMessage message = printShortestDistance(tilesNeighbors, (200 * units.getxLocation()) + units.getyLocation(), (200 * x) + y, v);
    }

    private static void addNeighbors(ArrayList<ArrayList<Integer>> tileNeighbors) {
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                if ((x + 1) < 200 && (Game.getMapInGame().getMap()[x + 1][y].getRock() == null && Game.getMapInGame().getMap()[x + 1][y].getType().getPermeability())) {
                    tileNeighbors.get((x * 200) + y).add(((x + 1) * 200) + y);
                }
                if ((x - 1) >= 0 && (Game.getMapInGame().getMap()[x - 1][y].getRock() == null && Game.getMapInGame().getMap()[x - 1][y].getType().getPermeability())) {
                    tileNeighbors.get((x * 200) + y).add(((x - 1) * 200) + y);
                }
                if ((y + 1) < 200 && (Game.getMapInGame().getMap()[x][y + 1].getRock() == null && Game.getMapInGame().getMap()[x][y + 1].getType().getPermeability())) {
                    tileNeighbors.get((x * 200) + y).add(((x) * 200) + y + 1);
                }
                if ((y - 1) >= 0 && (Game.getMapInGame().getMap()[x][y - 1].getRock() == null && Game.getMapInGame().getMap()[x][y - 1].getType().getPermeability())) {
                    tileNeighbors.get((x * 200) + y).add(((x + 1) * 200) + y - 1);
                }

            }
        }
    }

    public static GameMenuMessage patrolUnit(int x1, int y1, int x2, int y2) {
        for (Units unit : currentUnits) {
            unit.setPatrolFromX(x1);
            unit.setPatrolFromY(y1);
            unit.setPatrolToX(x2);
            unit.setPatrolToY(y2);
        }
        return null;
    }

    public static GameMenuMessage stopPatrol(int x, int y) {
        int hasUnitFlag = 0;
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        Tile tile = Game.getMapInGame().getMap()[x][y];
        for (People people : tile.getPeopleOnTile()) {
            if (people instanceof Units) {
                if (((Units) people).getPatrolToX() != -1 && ((Units) people).getPatrolToY() != -1) {
                    ((Units) people).setPatrolToX(-1);
                    ((Units) people).setPatrolToY(-1);
                    hasUnitFlag = 1;

                }
            }
        }
        if (hasUnitFlag == 1)
            return GameMenuMessage.SUCCESS;
        else
            return GameMenuMessage.PROBLEM;
    }

    public static GameMenuMessage printShortestDistance(ArrayList<ArrayList<Integer>> neighborTiles, int tile1, int tile2, int v) {
        int counter = 0;
        int pred[] = new int[v];
        int dist[] = new int[v];
        if (BFS(neighborTiles, tile1, tile2, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                    "are not connected");
            return GameMenuMessage.PROBLEM;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = tile2;
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        int i = 0;
        System.out.println("Shortest path length is: " + dist[tile2]);
        System.out.println("Path is ::");
        for (i = path.size() - 1; i >= 0; i--) {
            System.out.print("x:  " + path.get(i) / 200 + "y:    " + path.get(i) % 200);
            for (Units units : currentUnits) {
                units.setxLocation(path.get(i) / 200);
                units.setyLocation(path.get(i) % 200);
            }
            counter++;
            if (counter == currentUnits.get(0).getUnitsName().getSpeed() / 20)
                break;
        }
        if (path.size() > counter) {
            return GameMenuMessage.BIGGERTHANSPEED;
        } else {
            return GameMenuMessage.SUCCESS;

        }
    }

    public static GameMenuMessage printShortestDistance(Units unit, ArrayList<ArrayList<Integer>> neighborTiles, int tile1, int tile2, int v) {
        int counter = 0;
        int pred[] = new int[v];
        int dist[] = new int[v];
        if (BFS(neighborTiles, tile1, tile2, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                    "are not connected");
            return GameMenuMessage.PROBLEM;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = tile2;
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        int i = 0;
        System.out.println("Shortest path length is: " + dist[tile2]);
        System.out.println("Path is ::");
        for (i = path.size() - 1; i >= 0; i--) {
            System.out.print("x:  " + path.get(i) / 200 + "y:    " + path.get(i) % 200);
            unit.setxLocation(path.get(i) / 200);
            unit.setyLocation(path.get(i) % 200);
            counter++;
            if (counter == unit.getUnitsName().getSpeed() / 20)
                break;
        }
        if (path.size() > counter) {
            return GameMenuMessage.BIGGERTHANSPEED;
        } else {
            return GameMenuMessage.SUCCESS;

        }
    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> tileNeighbors, int tile1,
                               int tile2, int v, int pred[], int dist[]) {

        LinkedList<Integer> queue = new LinkedList<Integer>();

        boolean visited[] = new boolean[v];


        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[tile1] = true;
        dist[tile1] = 0;
        queue.add(tile1);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < tileNeighbors.get(u).size(); i++) {
                if (visited[tileNeighbors.get(u).get(i)] == false) {
                    visited[tileNeighbors.get(u).get(i)] = true;
                    dist[tileNeighbors.get(u).get(i)] = dist[u] + 1;
                    pred[tileNeighbors.get(u).get(i)] = u;
                    queue.add(tileNeighbors.get(u).get(i));

                    // stopping condition (when we find
                    // our destination)
                    if (tileNeighbors.get(u).get(i) == tile2)
                        return true;
                }
            }
        }
        return false;
    }

    public static GameMenuMessage setMode(int x, int y, State state) {
        if (x >= 200 || y >= 200 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
        for (People people : tile.getPeopleOnTile()) {
            if (people instanceof Units && people.getOwnerPerson().equals(Game.getCurrentUser())) {
                ((Units) people).setState(state);
            }
        }
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage attack(int x, int y) {
        GameMenuMessage message = null;
        int previousX = currentUnits.get(0).getxLocation();
        int previousY = currentUnits.get(0).getyLocation();
        if (x >= 200 || y >= 200 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        message = moveUnit(x, y);
        if (message.equals(GameMenuMessage.BIGGERTHANSPEED)) {
            moveUnit(previousX, previousY);
            return GameMenuMessage.BIGGERTHANSPEED;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
        attackToTile(tile);
        return GameMenuMessage.SUCCESS;

    }

    public static GameMenuMessage airAttack(int x, int y) {
        if (x < 0 || x >= 200 || y <= 0 || y >= 200) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        int xDistance = x - currentUnits.get(0).getxLocation();
        int yDistance = y - currentUnits.get(0).getyLocation();
        double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        int dis = (int) distance;
        if (currentUnits.get(0) instanceof Archers) {
            if ((((Archers) currentUnits.get(0)).getArrowRadius() / 20) < dis) {
                return GameMenuMessage.PROBLEM;
            } else {
                if ((((Archers) currentUnits.get(0)).getInventories().size() < 1||Game.getTurnedUserForGame().getUserGovernment().numberOfResource(((Archers) currentUnits.get(0)).getWartool())<currentUnits.size())) {
                    return GameMenuMessage.NOTENOUGHRESOURCE;
                }
                Tile tile = Game.getMapInGame().getMap()[x][y];
                for (People people : tile.getPeopleOnTile()) {
                    if (people instanceof Units && !people.getOwnerPerson().equals(currentUnits.get(0).getOwnerPerson())) {
                        double efficiently = ((Archers) currentUnits.get(0)).getFatality() * ((Archers) currentUnits.get(0)).getPrecision() / 100;
                        int eff = (int) efficiently;
                        ((Units) people).changeHitPoint(-1 * eff);
                        for (Units units : currentUnits) {
                                if(units instanceof Archers){
                                    Resource resource=((Archers) units).getInventories().get(0);
                                    ((Archers) units).getInventories().remove(resource);
                                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(resource,1);

                                }
                        }


                    }
                    return GameMenuMessage.SUCCESS;
                }
            }
        } else {
            return GameMenuMessage.INVALIDUNIT;
        }
        return null;
    }

    public static GameMenuMessage pourOil(String direction) {  //TODO what i shall do with oil
        return null;

    }

    public static GameMenuMessage specialMoveUnit(int x, int y) {
        return null;
    }

    public static GameMenuMessage digTunnel(int x, int y) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        if (currentUnits.get(0).getUnitsName().getName().equals("spearman")) {
            Tile tile = Game.getMapInGame().getMap()[x][y];
            if (tile.getBuilding().getName().equals("lookout tower") || tile.getBuilding().getName().equals("perimeter tower") || tile.getBuilding().getName().equals("square tower") || tile.getBuilding().getName().equals("circle tower")) {
                return GameMenuMessage.CANT_DIG;

            }
            tile.setHasTunnel(true);
            return GameMenuMessage.SUCCESS;
        } else {
            return GameMenuMessage.INVALIDUNIT;
        }
    }


    public static GameMenuMessage disbandUnit() {
        for (Building building : currentUnits.get(0).getOwnerPerson().getUserGovernment().getBuildings()) {
            if (building instanceof Hovel) {
                Tile tile = Game.getMapInGame().getMap()[building.getX()][building.getY()];
                while (currentUnits.get(0).getxLocation() != building.getX() && currentUnits.get(0).getyLocation() != building.getX()) {
                    moveUnit(building.getX(), building.getY());


                }
            }
            return GameMenuMessage.SUCCESS;
        }
        return null;
    }

    public static GameMenuMessage makeGate(String name, String direction, int x, int y) {//faghat ye ghale dare har hokoomat?
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        Tile tile = Game.getMapInGame().getMap()[x][y];
        Building building;
        if (tile.getBuilding() != null)
            return GameMenuMessage.HAS_BUILDING;
        if (direction.equals("forward") || direction.equals("backward")) {
            if ((building = (Gatehouse.makeGatehouseByName(name, x, y, Game.getTurnedUserForGame().getUserGovernment(), 1))) != null) {
                tile.setBuilding(building);

                if (name.equals("big stone gatehouse")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 20);
                    return GameMenuMessage.SUCCESS;
                }
            }
            return null;
        } else {
            return GameMenuMessage.INVALIDDIRECTION;

        }
    }

    public static GameMenuMessage makeWall(int x, int y, String type) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        if (type.equals("small wall") || type.equals("great wall")) {
            Tile tile = Game.getMapInGame().getMap()[x][y];
            if (tile.getBuilding() != null)
                return GameMenuMessage.HAS_BUILDING;
            Building building;
            if ((building = Wall.makeWallByName(type, x, y, Game.getTurnedUserForGame().getUserGovernment(), 1)) != null) {
                tile.setBuilding(building);
                if (type.equals("small wall")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 2);
                    return GameMenuMessage.SUCCESS;
                } else {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 4);
                    return GameMenuMessage.SUCCESS;

                }
            }
            return null;
        } else {
            return GameMenuMessage.INVALID_TYPE;
        }

    }

    public static GameMenuMessage makeSmallTower() {
        return null;

    }

    public static GameMenuMessage makeTower(int x, int y, String type) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        if (type.equals("lookout tower") || type.equals("perimeter tower") || type.equals("defensive tower") || type.equals("square tower") || type.equals("circle tower")) {
            Tile tile = Game.getMapInGame().getMap()[x][y];
            if (tile.getBuilding() != null)
                return GameMenuMessage.HAS_BUILDING;
            Building building;
            if ((building = Tower.makeTowerByName(type, x, y, Game.getTurnedUserForGame().getUserGovernment(), 1)) != null) {
                tile.setBuilding(building);
                if (building.getName().equals("lookout tower")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 10);
                    return GameMenuMessage.SUCCESS;
                }
                if (building.getName().equals("perimeter tower")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 10);
                    return GameMenuMessage.SUCCESS;
                }
                if (building.getName().equals("defensive tower")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 15);
                    return GameMenuMessage.SUCCESS;
                }
                if (building.getName().equals("square tower")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 35);
                    return GameMenuMessage.SUCCESS;
                }
                if (building.getName().equals("circle tower")) {
                    Game.getTurnedUserForGame().getUserGovernment().removeFromResources(Resource.STONE, 40);
                    return GameMenuMessage.SUCCESS;
                }


            } else {
                return null;
            }
        } else {
            return GameMenuMessage.INVALID_TYPE;

        }
        return null;

    }

    public static GameMenuMessage makeKillerTale(int x, int y) {    //TODO should make something for visibility of owner of tale
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        Tile tile = Game.getMapInGame().getMap()[x][y];
        if (tile.isHasKillerTale()) {
            return GameMenuMessage.INVALIDPOSITION;
        }
        tile.setHasStair(true);
        return GameMenuMessage.SUCCESS;

    }

    public static GameMenuMessage makeOilTale(int x, int y) {  //TODO we should do something for fire arrow
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        Tile tile = Game.getMapInGame().getMap()[x][y];
        if (tile.isHasKillerTale() || tile.isHasOilTale()) {
            return GameMenuMessage.INVALIDPOSITION;
        }
        tile.setHasOilTale(true);
        return GameMenuMessage.SUCCESS;

    }

    public static GameMenuMessage makeStair(int x, int y) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        Tile tile = Game.getMapInGame().getMap()[x][y];
        if (tile.getBuilding().getName().equals("great wall") || tile.getBuilding().getName().equals("small wall") || tile.getBuilding().getName().equals("small stone gatehouse") || tile.getBuilding().getName().equals("big stone gatehouse")) {
            tile.setHasStair(true);
            return GameMenuMessage.SUCCESS;
        } else {
            return GameMenuMessage.INVALIDPOSITION;
        }
    }


    private static GameMenuMessage burningOil() {   //TODO burning oil should be completed

        return null;
    }

    public static GameMenuMessage captureGate() {
        ArrayList<Tile> neighbors = new ArrayList<>();
        getNeighbors(currentUnits.get(0).getxLocation(), currentUnits.get(0).getyLocation(), neighbors);
        for (Tile tile : neighbors) {
            if (tile.getBuilding() instanceof Gatehouse) {
                Gatehouse gatehouse = (Gatehouse) tile.getBuilding();
                if (gatehouse.getGovernment() != currentUnits.get(0).getOwnerPerson().getUserGovernment()) {
                    if (currentUnits.get(0).getUnitsName().getName().equals("spearman") || currentUnits.get(0).getUnitsName().getName().equals("maceman")) {
                        moveUnit(gatehouse.getX(), gatehouse.getY());

                        gatehouse.setOpenGate(true);
                        gatehouse.setHasFlag(true);
                        return GameMenuMessage.SUCCESS;
                    }
                }
            }
        }
        neighbors.clear();
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                Building building = Game.getMapInGame().getMap()[x][y].getBuilding();
                if (building instanceof Gatehouse && building.getGovernment().getUser() !=  Game.getTurnedUserForGame()) {
                    getNeighbors(x, y, neighbors);
                    for (Tile tile : neighbors) {
                        Building building1 = tile.getBuilding();
                        if (building1 instanceof Tower && building1.getGovernment().getUser() ==  Game.getTurnedUserForGame()) {
                            ((Gatehouse) building).setOpenGate(true);
                            ((Gatehouse) building).setHasFlag(true);
                            return GameMenuMessage.SUCCESS;

                        }
                    }
                }
            }
        }
        return GameMenuMessage.PROBLEM;

    }


    public static GameMenuMessage makeProtection(int x, int y, String unitsName) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        Tile tile = Game.getMapInGame().getMap()[x][y];
        Combat combat = null;
        for (People people : tile.getPeopleOnTile()) {
            if (people instanceof Combat) {
                if (((Units) people).getUnitsName().getName().equals(unitsName)) {
                    combat = (Combat) people;
                    break;

                }
            }
            return GameMenuMessage.INVALIDUNIT;
        }
        for (Engineer engineer :  Game.getTurnedUserForGame().getUserGovernment().getEngineers()) {
            if (!engineer.isHasWork()) {
                if ( Game.getTurnedUserForGame().getUserGovernment().numberOfResource(Resource.IRON) >= 2) {
                    engineer.setHasWork(true);
                    combat.setPortableProtection(wartoolenum.PORTABLE_PROTECTION);
                } else {
                    return GameMenuMessage.NOTENOUGHRESOURCE;
                }

            }
        }
        return GameMenuMessage.PROBLEM;
    }

    public static GameMenuMessage makeBatteringRam(int x, int y) {    //TODO make features of batteringRam in game
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        int counterUnemployed = 0;
        ArrayList<Engineer> engineersToMakeBatteringRam = new ArrayList<>();
        for (Engineer engineer :  Game.getTurnedUserForGame().getUserGovernment().getEngineers()) {
            if (!engineer.isHasWork()) {
                counterUnemployed++;
                engineersToMakeBatteringRam.add(engineer);
            }
        }
        if (counterUnemployed < 4)
            return GameMenuMessage.PROBLEM;
        if (( Game.getTurnedUserForGame().getUserGovernment().numberOfResource(Resource.STONE)) < 10)
            return GameMenuMessage.NOTENOUGHRESOURCE;
        for (Engineer engineer : engineersToMakeBatteringRam) {
            engineer.setHasWork(true);
        }
        BatteringRam batteringRam = new BatteringRam( Game.getTurnedUserForGame().getUserGovernment(), x, y);
        return GameMenuMessage.SUCCESS;


    }

    public static GameMenuMessage makeCatapult(int x, int y) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        ArrayList<Engineer> unEmployedEngineers = new ArrayList<>();
        unEmployedEngineers(unEmployedEngineers,  Game.getTurnedUserForGame().getUserGovernment());
        if (unEmployedEngineers.size() < 2)
            return GameMenuMessage.PROBLEM;
        if ( Game.getTurnedUserForGame().getUserGovernment().numberOfResource(Resource.STONE) < 10)
            return GameMenuMessage.NOTENOUGHRESOURCE;
        int counter = 0;
        for (Engineer engineer : unEmployedEngineers) {
            engineer.setHasWork(true);
            counter++;
            if (counter == 2)
                break;
            ;

        }
        CataPult cataPult = new CataPult(x, y);
        return GameMenuMessage.SUCCESS;

    }

    public static GameMenuMessage makeFixedCatapult(int x, int y) {
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        ArrayList<Engineer> unEmployedEngineers = new ArrayList<>();
        unEmployedEngineers(unEmployedEngineers,  Game.getTurnedUserForGame().getUserGovernment());
        if (unEmployedEngineers.size() < 3)
            return GameMenuMessage.PROBLEM;
        if ( Game.getTurnedUserForGame().getUserGovernment().numberOfResource(Resource.STONE) < 20)
            return GameMenuMessage.NOTENOUGHRESOURCE;
        int counter = 0;
        for (Engineer engineer : unEmployedEngineers) {
            engineer.setHasWork(true);
            counter++;
            if (counter == 3)
                break;

        }
        FixedCatapult fixedCatapult = new FixedCatapult(x, y);
        return GameMenuMessage.SUCCESS;

    }

    public static GameMenuMessage stoneTower(int x, int y) {
        return null;
    }

    public static GameMenuMessage fillingDitch(int x, int y) {
        return null;
    }

    private static void fight(Units unit1, Units unit2) {
        float unit1ChangeHitPoint = unit2.getUnitsName().getAttackingPower() * unit2.getEfficientAttackingPower() / 500;
        float unit2ChangeHitPoint = unit1.getUnitsName().getAttackingPower() * unit1.getEfficientAttackingPower() / 500;

        unit1.changeHitPoint((int) (Math.floor(-1 * unit1ChangeHitPoint * (100 - unit1.getUnitsName().getDefensingPower())) / 100));
        unit2.changeHitPoint((int) (Math.floor(-1 * unit2ChangeHitPoint * (100 - unit2.getUnitsName().getDefensingPower())) / 100));
        unit1.changeEfficientAttackingPower(-5);
        unit2.changeEfficientAttackingPower(-5);
    }

    private static void attackToTile(Tile tile) {
        ArrayList<Units> deathUnits = new ArrayList<>();
        for (Units unit : currentUnits) {
            for (People enemyUnit : tile.getPeopleOnTile()) {
                if (enemyUnit instanceof Units && !enemyUnit.getOwnerPerson().equals( Game.getTurnedUserForGame())) {
                    fight(unit, (Units) enemyUnit);
                    if (unit.getHitPoint() < 0) {
                        deathUnits.add(unit);
                    }
                    if (((Units) enemyUnit).getHitPoint() < 0) {
                        deathUnits.add((Units) enemyUnit);
                    }
                }
            }

        }
        for (Units deathUnit : deathUnits) {
            deathUnit.getOwnerPerson().getUserGovernment().getPeople().remove(deathUnit);
            System.out.println("Unit " + deathUnit.getUnitsName().getName() + "for owner: " + deathUnit.getOwnerPerson().getUsername() + " died");
        }
    }

    public static boolean invalidLocation(int x, int y) {
        if (x < 0 || x >= 200 || y <= 0 || y >= 200)
            return false;
        return true;
    }

    public static void getNeighbors(int x, int y, ArrayList<Tile> neighbors) {
        if (x + 1 < 200)
            neighbors.add(Game.getMapInGame().getMap()[x + 1][y]);
        if (x - 1 >= 0)
            neighbors.add(Game.getMapInGame().getMap()[x - 1][y]);
        if (y + 1 < 200)
            neighbors.add(Game.getMapInGame().getMap()[x][y + 1]);
        if (y - 1 >= 0)
            neighbors.add(Game.getMapInGame().getMap()[x][y - 1]);
    }

    public static GameMenuMessage makeSiegeTower(int x, int y) {  //TODO make features of siege tower in game
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        int unEmployedEngineers = 0;
        ArrayList<Engineer> engineersToSiegeTower = new ArrayList<>();
        for (Engineer engineer :  Game.getTurnedUserForGame().getUserGovernment().getEngineers()) {
            if (!engineer.isHasWork()) {
                unEmployedEngineers++;
                engineersToSiegeTower.add(engineer);
            }
        }
        if (unEmployedEngineers < 4)
            return GameMenuMessage.PROBLEM;
        if (( Game.getTurnedUserForGame().getUserGovernment().numberOfResource(Resource.STONE)) < 30)
            return GameMenuMessage.NOTENOUGHRESOURCE;
        for (Engineer engineer : engineersToSiegeTower) {
            engineer.setHasWork(true);
        }
        SiegeTower siegeTower = new SiegeTower(x, y);
        return GameMenuMessage.SUCCESS;


    }

    private static void unEmployedEngineers(ArrayList<Engineer> engineers, Government government) {
        int counter = 0;
        for (Engineer engineer : government.getEngineers()) {
            if (!engineer.isHasWork()) {
                engineers.add(engineer);
                counter++;
            }
        }

    }

    public static GameMenuMessage makeFieryStoneThrower(int x, int y) {    //TODO make some features for playing with it in game
        if (!invalidLocation(x, y))
            return GameMenuMessage.WRONG_AMOUNT;
        int unEmployedEngineers = 0;
        ArrayList<Engineer> engineersToFieryStone = new ArrayList<>();
        for (Engineer engineer :  Game.getTurnedUserForGame().getUserGovernment().getEngineers()) {
            if (!engineer.isHasWork()) {
                unEmployedEngineers++;
                engineersToFieryStone.add(engineer);
            }
        }
        if (unEmployedEngineers < 4)
            return GameMenuMessage.PROBLEM;
        if (( Game.getTurnedUserForGame().getUserGovernment().numberOfResource(Resource.STONE)) < 25)
            return GameMenuMessage.NOTENOUGHRESOURCE;
        for (Engineer engineer : engineersToFieryStone) {
            engineer.setHasWork(true);
        }
        FieryStoneThrower fieryStoneThrower = new FieryStoneThrower(x, y);
        return GameMenuMessage.SUCCESS;


    }

    public static GameMenuMessage nextTurn() {//TODO should be completed !!!!a lot of work we have to do!
        changeFoodsInventory();
        if (Game.getPlayers().indexOf( Game.getTurnedUserForGame()) == Game.getPlayers().size() - 1) {
            counterTurn++;
            Game.setTurnedUserForGame(Game.getGameStarter());
            for (int i = 0; i < 200; i++) {
                for (int j = 0; j < 200; j++) {
                    for (People people : Game.getMapInGame().getMap()[i][j].getPeopleOnTile()) {
                        if (people instanceof Units) {
                            if (people.getToGoX() != people.getxLocation() || people.getToGoY() != people.getyLocation()) {
                                SpecialMoveUnit((Units) people, people.getToGoX(), people.getToGoY());
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < 200; i++) {
                for (int j = 0; j < 200; j++) {
                    for (People people : Game.getMapInGame().getMap()[i][j].getPeopleOnTile()) {
                        if (people instanceof Units) {
                            if (((Units) people).getPatrolToX() != -1 || ((Units) people).getPatrolToY() != -1) {
                                SpecialMoveUnit((Units) people, ((Units) people).getPatrolToX(), ((Units) people).getPatrolToY());
                                if (people.getxLocation() == ((Units) people).getPatrolToX() && people.getyLocation() == ((Units) people).getPatrolToY()) {
                                    ((Units) people).setPatrolToX(((Units) people).getPatrolFromX());
                                    ((Units) people).setPatrolToY(((Units) people).getPatrolFromY());
                                }
                            }


                        }
                    }
                }
            }
            return GameMenuMessage.NEXT_TURN;
        } else {
            Game.setTurnedUserForGame(Game.getPlayers().get(Game.getPlayers().indexOf(Game.getTurnedUserForGame()) + 1));
            currentUnits.clear();
            return GameMenuMessage.NEXT_PLAYER;
        }
    }

    private static void changeFoodsInventory() {
        Government government=Game.getTurnedUserForGame().getUserGovernment();
        if(government.getFoodRate()==-2){

        }
        else if(government.getFoodRate()==0){
            for (Resource resource:government.getFoods().keySet()){
                government.getFoods().put(resource, (government.getFoods().get(resource) - government.getPopulation()));

            }
        }
    }

}
