package view;

import model.Game;
import model.map.GameMap;
import model.user.User;
import view.enums.commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run() {
        String input ;
        Matcher matcher ;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            if (input.matches("^\\s*logout\\s*$")) {
                System.out.println("user logged out");
                break;
            }
            else if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.PROFILE_MENU)) != null) {
                System.out.println("entered profile menu");
               int flag = ProfileMenu.run();
                if (flag == 1) {
                    System.out.println("logged out");
                    break;
                }
            }
            else if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME)) != null) {
                letStartGame();
                EnvironmentMenu.run(scanner);
            } else System.out.println("invalid command!");

        }
    }
    private static void letStartGame(){
        Game.getPlayersInGame();
        int counter=0;
        Game.getPlayersInGame().add(Game.getCurrentUser());
        counter++;
        Scanner scanner=new Scanner(System.in);
        while (true){
            String add=scanner.nextLine();
            Matcher matcher;
            if((matcher = MainMenuCommands.getMatcher(add, MainMenuCommands.ADD_PLAYER)) != null){
                String username=matcher.group("username");
                if(getUserByName(username)!=null) {
                    if(counter==8){
                        System.out.println("you have maximum of players in your game");
                    }
                    Game.getPlayersInGame().add(getUserByName(username));
                    counter++;
                }
                else
                    System.out.println("we dont have this user");

            } else if (add.matches("^\\s*make\\s+map\\s*$")){
                GameMap gameMap = new GameMap();
                Game.setMapInGame(gameMap);
                break;
            }
            else {
                System.out.println("invalid command");

            }
        }
    }
    private static User getUserByName(String username){
        for (User user:Game.getPlayers()){
            if(user.getUsername().equals(username))
                return user;

        }
        return null;
    }
}
