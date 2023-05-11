package view;

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

            } else System.out.println("invalid command!");

        }
    }
}
