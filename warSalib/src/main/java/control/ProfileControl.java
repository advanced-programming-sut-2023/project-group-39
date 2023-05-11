package control;

import com.google.gson.Gson;
import model.Game;
import model.user.User;
import view.GameMenu;
import view.LoginSignupMenu;
import view.enums.messages.LoginMenuMessage;
import view.enums.messages.ProfileMenuMessage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ProfileControl {
    public static ProfileMenuMessage changeUsername(String username) {
        for (User player : Game.getPlayers())
            if (player.getUsername().equals(username))
                return ProfileMenuMessage.USERNAME_EXISTS;

        if (LoginSignupControl.checkUsername(username).equals(LoginMenuMessage.INVALIDUSERNAME))
            return ProfileMenuMessage.INVALID_USERNAME_FORMAT;

        Game.getCurrentUser().setUsername(username);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(Game.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        else if (newPassword.equals("random"))
            newPassword = LoginSignupControl.findRandomPassword(); // need to check this part

        Game.getCurrentUser().setPassword(newPassword);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(Game.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeEmail(String email) {
        for (User player : Game.getPlayers())
            if (player.getEmail().equals(email))
                return ProfileMenuMessage.EMAIL_EXISTS;

        if (!LoginSignupControl.validateEmail(email).equals(LoginMenuMessage.SUCCESS))
            return ProfileMenuMessage.INVALID_EMAIL_FORMAT;

        Game.getCurrentUser().setEmail(email);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(Game.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeSlogan(String slogan) {
        if (slogan.equals(""))
            return ProfileMenuMessage.EMPTY_SLOGAN;
        else if (slogan.equals("random")) { // need to check this part
            ArrayList<String> slogans = new ArrayList<>();
            LoginSignupControl.randomSlogan(slogans);
            Random random = new Random();
            int result = random.nextInt(9);
            Game.getCurrentUser().setSlogan(slogans.get(result));
            return ProfileMenuMessage.SUCCESS;
        }
        Game.getCurrentUser().setSlogan(slogan);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(Game.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage removeSlogan() {
        if (Game.getCurrentUser().getSlogan().equals(""))
            return ProfileMenuMessage.EMPTY_SLOGAN;

        Game.getCurrentUser().setSlogan("");
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(Game.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static String displayHighScore() {
        return String.valueOf(Game.getCurrentUser().getScore()); //need to check this function
    }

    public static String displayRank() {
        return String.valueOf(Game.getCurrentUser().getRank());
    }

    public static String displaySlogan() {
        if (Game.getCurrentUser().getSlogan().equals(""))
            return "Slogan is empty!";
        return Game.getCurrentUser().getSlogan();
    }

    public static String displayProfile() {
        StringBuilder result = new StringBuilder();
        User user = Game.getCurrentUser();
        result.append("username: ")
                .append(user.getUsername());

        if (!user.getSlogan().equals(""))
            result.append("\nslogan: ")
                    .append(user.getSlogan());

        result.append("\nHighScore: ")
                .append(user.getScore())
                .append("\nrank: ")
                .append(user.getRank());

        return result.toString();
    }

    public static ProfileMenuMessage startGame(ArrayList <String> player) {
        return null;
    }
}
