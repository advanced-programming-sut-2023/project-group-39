package control;

import model.Game;
import model.user.User;
import view.GameMenu;
import view.LoginSignupMenu;
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
        if (nickname.equals(""))
            return ProfileMenuMessage.INVALID_NICKNAME_FORMAT;

        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePassword(String newPassword, String oldPassword) {
        if (!Game.getCurrentUser().getPassword().equals(oldPassword))
            return ProfileMenuMessage.WRONG_PASSWORD;

        else if (oldPassword.equals(newPassword))
            return ProfileMenuMessage.SAME_PASSWORD;

        else if (LoginSignupControl.validatePassword(newPassword) == null)
            return ProfileMenuMessage.INVALID_PASSWORD_FORMAT;

        else if (!LoginSignupControl.validatePassword(newPassword).equals(LoginMenuMessage.STRONGPASSWORD))
            return ProfileMenuMessage.WEAK_PASSWORD;

        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeEmail(String email) {
        for (User player : Game.getPlayers())
            if (player.getEmail().equals(email))
                return ProfileMenuMessage.EMAIL_EXISTS;

        if (!LoginSignupControl.validateEmail(email).equals(LoginMenuMessage.SUCCESS))
            return ProfileMenuMessage.INVALID_EMAIL_FORMAT;

        return ProfileMenuMessage.SUCCESS;
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
