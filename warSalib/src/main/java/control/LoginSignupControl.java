package main.java.control;

import main.java.view.enums.messages.LoginMenuMessage;

public class LoginSignupControl {
    public static LoginMenuMessage loginUser(String username, String password) {
        return null;
    }

    public static LoginMenuMessage logoutUser() {
        return null;
    }

    public static LoginMenuMessage createUser(String username, String nickname, String email, String password, String slogan) {
        return null;
    }

    public static LoginMenuMessage pickQuestion(int number, String answer, String confirmAnswer) {
        return null;
    }

    private static Boolean validateUsername(String username) {
        return false;
    }

    private static Boolean validatePassword(String password) {
        return false;
    }

    public static LoginMenuMessage implementCaptcha() {
        return null;
    }

    private static Boolean validateEmail(String email) {
        return false;
    }

    private static String findRandomPassword() {
        return null;
    }

    private static String findRandomSlogan() {
        return null;
    }
}
