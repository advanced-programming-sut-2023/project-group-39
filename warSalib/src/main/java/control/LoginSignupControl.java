package control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.security.AnyTypePermission;
import model.Game;
import model.user.User;
import view.enums.commands.LoginMenuCommands;
import view.enums.messages.LoginMenuMessage;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

import com.thoughtworks.xstream.XStream;


public class LoginSignupControl {
    public static LoginMenuMessage loginUser(String username, String password,int loggedInflag) {
        for(User user:Game.getPlayers()){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    if(loggedInflag==1){
                        user.setLoggedIn(true);
                    }
                    Game.setCurrentUser(user);
                    return LoginMenuMessage.SUCCESS;

                }
                else {
                    return LoginMenuMessage.INCORRECTPASSWORD;
                }

            }
        }
        return LoginMenuMessage.USERNOTFOUND;

    }

    public static LoginMenuMessage logoutUser() {
        return null;
    }


    public static LoginMenuMessage pickQuestion(int number, String answer, String confirmAnswer) {
        return null;
    }


    public static LoginMenuMessage validatePassword(String password) {
        Matcher matcher;
        if ((matcher = LoginMenuCommands.getMatcher(password, LoginMenuCommands.STRONGPASSWORD)) != null) {
            return LoginMenuMessage.STRONGPASSWORD;
        }
        Matcher matcher1;
        if ((matcher1 = LoginMenuCommands.getMatcher(password, LoginMenuCommands.HASUPPERCASE)) == null)
            return LoginMenuMessage.WITHOUTUPPERCASE;
        else if ((matcher1 = LoginMenuCommands.getMatcher(password, LoginMenuCommands.HASLOWERCASE)) == null)
            return LoginMenuMessage.WITHOUTLOWERCASE;
        else if ((matcher1 = LoginMenuCommands.getMatcher(password, LoginMenuCommands.HASNUMBER)) == null)
            return LoginMenuMessage.WITHOUTNUMBER;
        else if ((matcher1 = LoginMenuCommands.getMatcher(password, LoginMenuCommands.HASSPECIALCHARACTER)) == null)
            return LoginMenuMessage.WITHOUTSPECIALCHARACTER;

        return LoginMenuMessage.LOW_LENGTH_PASS;
    }
    public static  LoginMenuMessage createUser(String username,String password,String emailAddress,String nickname,String slogan,String securityAnswer)  {
      //  XStream xStream=new XStream();
        User user=new User(username,password,emailAddress,nickname,slogan,securityAnswer);
        Game.getPlayers().add(user);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(Game.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // String xml=xStream.toXML(Game.getPlayers());
       // try {
         //   FileWriter writer=new FileWriter("users.xml");
         //   writer.write(xml);
         //   writer.close();

       // } catch (IOException e) {
         //   e.printStackTrace();
      // }
        return LoginMenuMessage.SUCCESS;
    }


    public static LoginMenuMessage implementCaptcha() {
        return null;
    }

    public static LoginMenuMessage validateEmail(String email) {
        Matcher matcher;
        if((matcher=LoginMenuCommands.getMatcher(email,LoginMenuCommands.VALIDEMAIL))==null){
            return LoginMenuMessage.INVALIDEMAILFORMAT;
        }
        for (User user:Game.getPlayers()){
            if(user.getEmail().toLowerCase().equals(email.toLowerCase())){
                return LoginMenuMessage.DUPLICATEEMAIL;
            }
        }
        return LoginMenuMessage.SUCCESS;
    }

    public static String findRandomPassword() {
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        LCR.setNumberOfCharacters(1);
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        UCR.setNumberOfCharacters(1);
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        DR.setNumberOfCharacters(1);
        CharacterRule SR = new CharacterRule(EnglishCharacterData.Special);
        SR.setNumberOfCharacters(1);
        PasswordGenerator passGen = new PasswordGenerator();
        Random random = new Random();
        int low = 6;
        int high = 20;
        int result = random.nextInt(high - low) + low;
        String password = passGen.generatePassword(result, SR, LCR, UCR, DR);
        return password;
    }


    public static LoginMenuMessage checkUsername(String username) {
        Matcher matcher;
        if ((matcher = LoginMenuCommands.getMatcher(username, LoginMenuCommands.VALIDUSERNAME)) == null) {
            return LoginMenuMessage.INVALIDUSERNAME;
        }
        for (User user : Game.getPlayers()) {
            if (user.getUsername().equals(username)) {
                return LoginMenuMessage.SAMEUSERNAME;
            }
        }
        return LoginMenuMessage.SUCCESS;
    }
    public static void randomSlogan(ArrayList<String> slogans){
        slogans.add("Adorn your Life with Game.");
        slogans.add("A New Perspective for Gamers.");
        slogans.add("Gaming is fun");
        slogans.add("Live your passion");
        slogans.add("Game on, boy!");
        slogans.add("More than just a game.");
        slogans.add("Take your game to the next level.");
        slogans.add("Hold different, Play different.");
        slogans.add("ife is too short to play a bad game.");
        slogans.add("Live, play the game, and lead.");
    }
    public static LoginMenuMessage forgotPassword(String username){
        for (User user:Game.getPlayers()){
            if(user.getUsername().equals(username)){
                return LoginMenuMessage.SECURITYQUESTION;
            }
        }
        return LoginMenuMessage.INVALIDUSERNAME;
    }
    public  static LoginMenuMessage checkSecurityAnswer(String username,String answer){
        System.out.println(answer);
        for(User user:Game.getPlayers()){
            if(user.getUsername().equals(username)){
                System.out.println(user.getSecurityQuestionAnswer());
                if(user.getSecurityQuestionAnswer().equals(answer)){
                    return LoginMenuMessage.SUCCESS;
                }
                return LoginMenuMessage.INVALIDANSWER;
            }
        }
        return null;
    }
    public static LoginMenuMessage makeNewPassword(String username,String password){
        for(User user:Game.getPlayers()){
            if(user.getUsername().equals(username)){
                user.setPassword(password);
                return LoginMenuMessage.SUCCESS;
            }
        }
        return null;
    }
    public static void readUsersData() throws IOException, ClassNotFoundException {
        String json=null;
        json=new String(Files.readAllBytes(Paths.get("users.json")));
        ArrayList<User> data=new Gson().fromJson(json,new TypeToken<ArrayList<User>>(){}.getType());
        if(data!=null)
            Game.setPlayers(data);
    }
}
