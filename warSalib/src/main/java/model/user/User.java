package model.user;

import model.government.Government;

import java.util.HashMap;

public class User {
    private String username;
    private String nickname;
    private String password;
    private String email;
    private int rank;
    private int score;
    private String slogan;
    private HashMap<String, String> securityQuestion ;
    private Government UserGovernment;

    public User(String username, String nickname, String password, String email, String slogan) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.slogan = slogan;
        securityQuestion = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap<String, String> getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(HashMap<String, String> securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setUserGovernment(Government userGovernment) {
        UserGovernment = userGovernment;
    }

    public Government getUserGovernment() {
        return UserGovernment;
    }
}
