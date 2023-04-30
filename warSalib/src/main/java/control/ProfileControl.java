package control;

import model.Game;
import model.user.User;
import view.GameMenu;
import view.enums.messages.LoginMenuMessage;
import view.enums.messages.ProfileMenuMessage;

import java.util.ArrayList;

public class ProfileControl {
    public static ProfileMenuMessage changeUsername(String username) {
        for (User player : Game.getPlayers())
            if (player.getUsername().equals(username))
                return ProfileMenuMessage.USERNAME_EXISTS;

        if (LoginSignupControl.checkUsername(username).equals(LoginMenuMessage.INVALIDUSERNAME))
            return ProfileMenuMessage.INVALID_USERNAME_FORMAT;

        return ProfileMenuMessage.SUCCESS;
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
