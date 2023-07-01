package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;

    private static ArrayList<User> usersInGame=new ArrayList<>();

    public static void initUsers() throws IOException {
        String json = null;
        String usernameRegex = "\\\"username\\\":\\\"(?<username>[^\\\"]+)\\\"";
        String passwordRegex = "\\\"password\\\":\\\"(?<password>[^\\\"]+)\\\"";
        String emailRegex = "\\\"email\\\":\\\"(?<email>[^\\\"]+)\\\"";
        String secAnswerRegex = "\\\"securityQuestionAnswer\\\":\\\"(?<senAns>[^\\\"]+)\\\"";
        String sloganRegex = "\\\"slogan\\\":\\\"(?<slogan>[^\\\"]+)\\\"";
        String scoreRegex = "\\\"score\\\":\\\"(?<score>[^\\\"]+)\\\"";
        String chooseImageRegex = "\\\"chooseImageAddress\\\":\\\"(?<choose>[^\\\"]+)\\\"";
        String nicknameRegex = "\\\"nickname\\\":\\\"(?<nickname>[^\\\"]+)\\\"";
        // add avatar image address;
        json = new String(Files.readAllBytes(Paths.get("users.json")));
        String[] datas = json.split("\\}\\,");
        for (int j = 0; j < datas.length; j++) {
            String username = null;
            String password = null;
            String email = null;
            String choose = null;
            String score = null;
            String secAnswer = null;
            String slogan = null;
            String nickname = null;
            Matcher usernameMatcher = Pattern.compile(usernameRegex).matcher(datas[j]);
            Matcher emailMatcher = Pattern.compile(emailRegex).matcher(datas[j]);
            Matcher passwordMatcher = Pattern.compile(passwordRegex).matcher(datas[j]);
            Matcher sloganMatcher = Pattern.compile(sloganRegex).matcher(datas[j]);
            Matcher scoreMatcher = Pattern.compile(scoreRegex).matcher(datas[j]);
            Matcher chooseMatcher = Pattern.compile(chooseImageRegex).matcher(datas[j]);
            Matcher secAnswerMatcher = Pattern.compile(secAnswerRegex).matcher(datas[j]);
            Matcher nicknameMatcher = Pattern.compile(nicknameRegex).matcher(datas[j]);
            if (usernameMatcher.find()) {
                username = usernameMatcher.group("username");
            }
            if (passwordMatcher.find()) {
                password = passwordMatcher.group("password");
            }
            if (emailMatcher.find()) {
                email = emailMatcher.group("email");
            }
            if (sloganMatcher.find()) {
                slogan = sloganMatcher.group("slogan");
            }
            if (secAnswerMatcher.find()) {
                secAnswer = secAnswerMatcher.group("senAns");
            }
            if (scoreMatcher.find()) {
                score = scoreMatcher.group("score");
            }
            if (chooseMatcher.find()) {
                choose = chooseMatcher.group("choose");
            }
            if (nicknameMatcher.find()) {
                nickname = nicknameMatcher.group("nickname");
            }
            User user = new User(username, password, email, nickname, slogan, secAnswer);
            users.add(user);

        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Database.loggedInUser = loggedInUser;
    }

    public static Message hasUser(String username, String password) {
        //User user1= new User("ardalan","Ardal123!","ardali@gmail.com", "ardali","","khar");
        //users.add(user1);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
            }
            if (user.getPassword().equals(password))
                return Message.SUCCESS;
            else return Message.WRONG_PASSWORD;
        }

        return Message.WRONG_USERNAME;
    }

    public static String hasCorrectSecurityQuestion(String username, String securityAnswer) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getSecurityQuestionAnswer().equals(securityAnswer)) {
                    return "correct";
                } else {
                    return "invalidAnswer";
                }
            }
        }
        return "invalidUsername";
    }

    public static User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static ArrayList<User> getUsersInGame() {
        return usersInGame;
    }

    public static synchronized void addUsersInGame(User user) {
        usersInGame.add(user);
    }
    public static synchronized void removeUsersInGame(User user) {
        usersInGame.remove(user);
    }
}
