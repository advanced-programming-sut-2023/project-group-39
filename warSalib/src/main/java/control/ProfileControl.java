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

        Game.getCurrentUser().setUsername(username);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeNickname(String nickname) {
        if (nickname.equals(""))
            return ProfileMenuMessage.INVALID_NICKNAME_FORMAT;

        Game.getCurrentUser().setNickname(nickname);
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

        Game.getCurrentUser().setPassword(newPassword);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeEmail(String email) {
        for (User player : Game.getPlayers())
            if (player.getEmail().equals(email))
                return ProfileMenuMessage.EMAIL_EXISTS;

        if (!LoginSignupControl.validateEmail(email).equals(LoginMenuMessage.SUCCESS))
            return ProfileMenuMessage.INVALID_EMAIL_FORMAT;

        Game.getCurrentUser().setEmail(email);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeSlogan(String slogan) {
        if (slogan.equals(""))
            return ProfileMenuMessage.EMPTY_SLOGAN;

        Game.getCurrentUser().setSlogan(slogan);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage removeSlogan() {
        if (Game.getCurrentUser().getSlogan().equals(""))
            return ProfileMenuMessage.EMPTY_SLOGAN;

        Game.getCurrentUser().setSlogan("");
        return ProfileMenuMessage.SUCCESS;
    }

    public static String displayHighScore() {
        return String.valueOf(Game.getCurrentUser().getScore()); //need to check this function
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
