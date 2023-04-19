package control;

import view.enums.messages.ProfileMenuMessage;

import java.util.ArrayList;

public class ProfileControl {
    public static ProfileMenuMessage changeUsername(String username) {
        return null;
    }

    public static ProfileMenuMessage changeNickname(String nickname) {
        return null;
    }

    public static ProfileMenuMessage changePassword(String newPassword, String oldPassword) {
        return null;
    }

    public static ProfileMenuMessage changeEmail(String email) {
        return null;
    }

    public static ProfileMenuMessage changeSlogan(String slogan) {
        return null;
    }

    private static boolean validateUsername(String username) {
        return false;
    }

    private static boolean validateOldPassword(String oldPassword) {
        return false;
    }
    private static boolean validateNewPassword(String newPassword) {
        return false;
    }

    public static ProfileMenuMessage removeSlogan() {
        return null;
    }

    public static String displayHighScore() {
        return null;
    }

    public static String displayRank() {
        return null;
    }

    public static String displaySlogan() {
        return null;
    }

    public static String displayProfile() {
        return null;
    }

    public static ProfileMenuMessage startGame(ArrayList <String> player) {
        return null;
    }
}
