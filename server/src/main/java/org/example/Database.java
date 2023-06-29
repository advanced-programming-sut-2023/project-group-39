package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;
    public static void initUsers() throws IOException {
        String json=null;
        json=new String(Files.readAllBytes(Paths.get("users.json")));
        ArrayList<User> data=new Gson().fromJson(json,new TypeToken<ArrayList<User>>(){}.getType());
        users = data;
        users.get(0).getNickname();
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
    public static boolean hasUser(String username, String password) {
        for (User user : users) {
            if (user.getPassword().equals(password) && user.getUsername().equals(username))
                return true;
        }
        return false;
    }
}
