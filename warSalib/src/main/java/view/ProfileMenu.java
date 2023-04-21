package view;

import java.util.regex.Matcher;

public class ProfileMenu {
    public static void run() {

    }

    private static void changeUsername(Matcher matcher) {
        String username = matcher.group("username");

    }

    private static void changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");

    }

    private static void changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

    }
    private static void changeEmail(Matcher matcher) {
        String email = matcher.group("email");

    }
    private static void changeSlogan(Matcher matcher) {
        String slogan = matcher.group("slogan");

    }
    private static void removeSlogan() {
        

    }
    private static void displayHighScore() {

    }
    private static void displayRank() {

    }
    private static void displaySlogan() {

    }
    private static void displayProfile() {

    }

    private static void startGame(Matcher matcher) {

    }
}
