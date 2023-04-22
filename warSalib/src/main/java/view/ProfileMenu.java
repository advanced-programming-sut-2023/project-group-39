package view;

import control.ProfileControl;
import model.user.User;
import view.enums.commands.ProfileMenuCommands;
import view.enums.messages.ProfileMenuMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private User currentUser;
    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            Matcher matcher;
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_USERNAME)) != null)
                changeUsername(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_NICKNAME)) != null)
                changeNickname(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)) != null)
                changePassword(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_EMAIL)) != null)
                changeEmail(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_SLOGAN)) != null)
                changeSlogan(matcher);
            else if (input.matches(String.valueOf(ProfileMenuCommands.REMOVE_SLOGAN)))
                removeSlogan();
            else if (input.matches(String.valueOf(ProfileMenuCommands.DISPLAY_HIGHSCORE)))
                displayHighScore();
            else if (input.matches(String.valueOf(ProfileMenuCommands.DISPLAY_RANK)))
                displayRank();
            else if (input.matches(String.valueOf(ProfileMenuCommands.DISPLAY_SLOGAN)))
                displaySlogan();
            else if (input.matches(String.valueOf(ProfileMenuCommands.DISPLAY_PROFILE)))
                displayProfile();
            else if (input.matches("^\\s*back\\s*$"))
                break;
            else System.out.println("invalid command!");
        }

    }

    private static void changeUsername(Matcher matcher) {
        String username = matcher.group("username");
        ProfileMenuMessage message = ProfileControl.changeUsername(username);
        switch (message) {
            case INVALID_USERNAME_FORMAT:
                System.out.println("invalid username format");
                break;
            case USERNAME_EXISTS:
                System.out.println("username already exists");
                break;
            case SUCCESS:
                System.out.println("username changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }

    private static void changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");
        ProfileMenuMessage message = ProfileControl.changeNickname(nickname);
        switch (message) {
            case INVALID_NICKNAME_FORMAT:
                System.out.println("invalid nickname format");
                break;
            case SUCCESS:
                System.out.println("nickname changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }

    private static void changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        ProfileMenuMessage message = ProfileControl.changePassword(newPassword, oldPassword);
        switch (message) {
            case WRONG_PASSWORD:
                System.out.println("username and password didn't match");
                break;
            case INVALID_PASSWORD_FORMAT:
                System.out.println("invalid password format");
                break;
            case WEAK_PASSWORD:
                System.out.println("password is weak");
                break;
            case SUCCESS:
                System.out.println("password changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
    private static void changeEmail(Matcher matcher) {
        String email = matcher.group("email");
        ProfileMenuMessage message = ProfileControl.changeEmail(email);
        switch (message) {
            case EMAIL_EXISTS:
                System.out.println("email already exists");
                break;
            case INVALID_EMAIL_FORMAT:
                System.out.println("invalid email format");
                break;
            case SUCCESS:
                System.out.println("email changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
    private static void changeSlogan(Matcher matcher) {
        String slogan = matcher.group("slogan");
        ProfileMenuMessage message = ProfileControl.changeSlogan(slogan);
        switch (message) {
            case EMPTY_SLOGAN:
                System.out.println("please set down a slogan");
                break;
            case SUCCESS:
                System.out.println("slogan changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

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
