package control;

import model.Game;
import model.government.people.People;
import model.government.people.units.State;
import model.government.people.units.Units;
import model.map.Tile;
import view.GameMenu;
import view.enums.messages.GameMenuMessage;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameControl {
    public static ArrayList<Units> currentUnits;

    public static GameMenuMessage selectUnit(int x, int y, String name) {
        if (x >= 200 || y >= 200 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
        for (People people : tile.getPeopleOnTile()) {
            if (people instanceof Units) {
                if (((Units) people).getUnitsName().getName().equals(name) && people.getOwnerPerson().equals(Game.getCurrentUser())) {
                    currentUnits.add((Units) people);
                }
            }
        }
        if(currentUnits.size()!=0){
            return GameMenuMessage.SUCCESS;
        }
        else {
            return GameMenuMessage.WITHOUTUNIT;
        }



    }


    public static GameMenuMessage moveUnit(int x, int y) {
        int v = 200 * 200;
            if (x >= 200 || y >= 200 || x < 0 || y < 0) {
                return GameMenuMessage.WRONG_AMOUNT;
            }
            if(!Game.getMapInGame().getMap()[x][y].getType().getPermeability()){
                return GameMenuMessage.SEA_HIGHHEIGHT;

            }
        ArrayList<ArrayList<Integer>> tilesNeighbors = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            tilesNeighbors.add(new ArrayList<Integer>());
        }
        addNeighbors(tilesNeighbors);
        GameMenuMessage message=printShortestDistance(tilesNeighbors,(200* currentUnits.get(0).getxLocation())+ currentUnits.get(0).getyLocation() ,(200*x)+y , v);
        return message;
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

        return null;
    }

    public static GameMenuMessage printShortestDistance(ArrayList<ArrayList<Integer>> neighborTiles, int tile1, int tile2, int v) {
        int counter=0;
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
        int i=0;
        System.out.println("Shortest path length is: " + dist[tile2]);
        System.out.println("Path is ::");
        for (i = path.size() - 1; i >= 0; i--) {
            System.out.print("x:  "+path.get(i)/200+"y:    " +path.get(i)%200);
            for (int u=0;u<currentUnits.size()-1;u++) {
                currentUnits.get(u).setxLocation(path.get(i) / 200);
                currentUnits.get(u).setyLocation(path.get(i) % 200);
            }
            counter++;
            if(counter==currentUnits.get(0).getUnitsName().getSpeed()/20)
                break;
        }
        if(path.size()>counter){
            return GameMenuMessage.BIGGERTHANSPEED;
        }
        else{
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
        int previousX= currentUnits.get(0).getxLocation();
        int previousY=currentUnits.get(0).getyLocation();
        if (x >= 200 || y >= 200 || x < 0 || y < 0) {
            return GameMenuMessage.WRONG_AMOUNT;
        }
        GameMenuMessage message=moveUnit(x,y);
        if(message.equals(GameMenuMessage.BIGGERTHANSPEED)){
            moveUnit(previousX,previousY);
            return GameMenuMessage.BIGGERTHANSPEED;
        }
        Tile tile = Game.getMapInGame().getMap()[x][y];
       // showEnemys(tile);


        return null;

    }

    public static GameMenuMessage airAttack(int x, int y) {
        return null;

    }

    public static GameMenuMessage pourOil(String direction) {
        return null;

    }

    public static GameMenuMessage specialMoveUnit(int x, int y) {
        return null;
    }

    public static GameMenuMessage digTunnel(int x, int y) {
        return null;

    }

    public static GameMenuMessage build(String equipmentName) {
        return null;
    }

    public static GameMenuMessage disbandUnit() {
        return null;
    }

    public static GameMenuMessage makeGate(String direction) {    //faghat ye ghale dare har hokoomat?
        return null;

    }

    public static GameMenuMessage makeWall(int x, int y, int width) {
        return null;

    }

    public static GameMenuMessage makeSmallTower() {
        return null;

    }

    public static GameMenuMessage makeTower(String type) {
        return null;

    }


    public static GameMenuMessage makeKillerTale(int x, int y) {
        return null;

    }

    public static GameMenuMessage makeOilTale(int x, int y) {
        return null;

    }

    public static GameMenuMessage makeStair(int x, int y) {
        return null;

    }

    public static GameMenuMessage diggingDitch(int x, int y) {
        return null;

    }

    public static GameMenuMessage removeDitch(int x, int y) {
        return null;
    }

    public static GameMenuMessage stopDitch(int x, int y) {
        return null;
    }

    private static GameMenuMessage burningOil() {

        return null;
    }

    public static GameMenuMessage captureGate() {

        return null;
    }

    public static GameMenuMessage openGate() {
        return null;

    }

    public static GameMenuMessage makeProtection() {
        return null;

    }

    public static GameMenuMessage batteringRam() {
        return null;

    }

    public static GameMenuMessage makeCatapult() {
        return null;

    }

    public static GameMenuMessage stoneTower(int x, int y) {
        return null;
    }

    public static GameMenuMessage fillingDitch(int x, int y) {
        return null;
    }
    private static void fight(Units unit1,Units unit2){
    }
    public void showEnemys(Tile tile){
        GameMenu.showEnemys(tile);
    }

}
