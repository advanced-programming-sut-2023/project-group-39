package view;

import control.LoginSignupControl;
import model.Game;
import view.enums.commands.LoginMenuCommands;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.LoginMenuMessage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginSignupMenu {
    static String username;
    static String password;
    static String confirmPassword;
    static String emailAddress;
    static String nickname;

    static String slogan;

    static int randomPasswordFlag;

    static int randomSloganFlag;
    static int hasSlogan = 0;
    static String suggestedUsername;

    static String answer;

    static String confirmAnswer;

    static int ansNumber = 0;

    static int counterWrongPassword = 0;

    static int flagCreateUser=0;

    public static void run() {
        Scanner scanner = Scan.getScanner();
        while (true) {
            try {
                timeLimit(counterWrongPassword);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.equals("exit"))
                break;
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.USERCREATE)) != null) {
                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.USERNAME)) != null) {
                    if (matcher.group("usernameWithoutSpace") != null) {
                        username = matcher.group("usernameWithoutSpace");
                    } else {
                        username = matcher.group("usernameWithSpace");
                        username = username.trim();
                        username = username.substring(1, username.length() - 1);
                    }
                } else {
                    System.out.println("your username field is empty!");

                }
                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.PASSWORD)) != null) {
                    if (matcher.group("passwordWithoutSpace") != null) {
                        password = matcher.group("passwordWithoutSpace");
                        if (password.equals("random")) {
                            randomPasswordFlag = 1;
                        }
                    } else {
                        password = matcher.group("passwordWithSpace");
                        password = password.trim();
                        password = password.substring(1, password.length() - 1);
                    }
                } else {
                    System.out.println("your password field is empty!");

                }
                if (randomPasswordFlag == 0) {
                    if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.PASSWORD_CONFIRM)) != null) {
                        if (matcher.group("passwordConfirmWithoutSpace") != null) {
                            confirmPassword = matcher.group("passwordConfirmWithoutSpace");
                        } else {
                            confirmPassword = matcher.group("passwordConfirmWithSpace");
                            confirmPassword = confirmPassword.trim();
                            confirmPassword = confirmPassword.substring(1, confirmPassword.length() - 1);
                        }
                    } else {
                        System.out.println("your Confirm password field is empty!");

                    }
                }
                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.EMAIL)) != null) {
                    if (matcher.group("emailAddress") != null) {
                        emailAddress = matcher.group("emailAddress");
                    } else {
                        System.out.println("your email field is empty!");

                    }
                }

                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.NICKNAME)) != null) {
                    if (matcher.group("nicknameWithoutSpace") != null) {
                        nickname = matcher.group("nicknameWithoutSpace");
                    } else   {
                        nickname = matcher.group("nicknameWithSpace");
                        nickname = nickname.trim();
                        nickname = nickname.substring(1, nickname.length() - 1);
                    }
                } else {
                    System.out.println("your nickname field is empty!");

                }
                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.HAVE_SLOGAN)) != null) {
                    hasSlogan=1;
                    if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.SLOGAN)) != null) {
                        if (matcher.group("sloganWithoutSpace") != null) {
                                slogan = matcher.group("sloganWithoutSpace");
                                if(slogan.equals("random"))
                                    randomSloganFlag=1;
                        } else {
                            slogan = matcher.group("sloganWithSpace");
                            slogan = slogan.trim();
                            slogan = slogan.substring(1, slogan.length() - 1);
                        }
                    } else {
                        System.out.println("your slogan field is empty!");

                    }
                }
                if(username!=null&&password!=null&&emailAddress!=null&&nickname!=null)
                checkUsername(username);
            }
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.USERLOGIN)) != null) {
                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGINUSER)) != null)
                    loginUser(matcher);
                else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.EMPTYUSERNAME)) == null)
                    System.out.println("your name field is empty!");
                else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.EMPTYPASSWORD)) == null)
                    System.out.println("your password field is empty!");
            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.PASSWORDFOROT)) != null) {
                if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOTPASSWORD)) != null) {
                    forgotPassword(matcher);
                } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.EMPTYUSERNAME)) == null) {
                    System.out.println("your username field is empty!");
                }
            }
            else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.STAY_LOGGED_IN_LOGIN)) != null){
                stayLoggedInlogin(matcher);

            }            else
                System.out.println("Invalid command!");
        }

    }

    private static void stayLoggedInlogin(Matcher matcher) {
        String USERNAME=new String();
        if(matcher.group("StayUsernameWithSpace")!=null){
            USERNAME=matcher.group("StayUsernameWithSpace");
            USERNAME=USERNAME.substring(1,USERNAME.length()-1);
        }
        else if(matcher.group("StayUsernameWithOutSpace")!=null){
            USERNAME=matcher.group("StayUsernameWithOutSpace");

        }
       GameMenuMessage message=LoginSignupControl.stayLoggedInlogin(username);
        switch (message){
            case SUCCESS:
                System.out.println("you logged in successfully");
                break;
            case PROBLEM:
                System.out.println("you cant login");
                break;
        }
    }

    private static void validUsername(String username) {
    }


    private static void forgotPassword(Matcher matcher) {
        String username = matcher.group("username");
        LoginMenuMessage message = LoginSignupControl.forgotPassword(username);
        switch (message) {
            case INVALIDUSERNAME:
                System.out.println("username is invalid");
                break;
            case SECURITYQUESTION:
                System.out.println("Pick your security question: 1. What is my father’s name? 2. What\n" +
                        "was my first pet’s name? 3. What is my mother’s last name?");
                Scanner scanner = Scan.getScanner();
                String answer = scanner.nextLine();
                LoginMenuMessage message1 = LoginSignupControl.checkSecurityAnswer(username, answer);
                switch (message1) {
                    case SUCCESS:
                        System.out.println("type your new password");
                        makeNewPassword(username);
                        break;
                    case INVALIDANSWER:
                        System.out.println("your answer was incorrect");
                        break;
                }
                break;
        }
    }

    private static String implementCaptcha() {
        return null;
    }


    private static void loginUser(Matcher matcher) {
        String username = matcher.group("username").trim();
        String password = matcher.group("password").trim();
        removeQutation(username);
        removeQutation(password);
        int loggedInflag = 0;
        if (matcher.group("loggedInFlag") != null) {
            loggedInflag = 1;
        }
        LoginMenuMessage message = LoginSignupControl.loginUser(username, password, loggedInflag);
        switch (message) {
            case SUCCESS:
                System.out.println("you logged in successfully");
                counterWrongPassword = 0;
                MainMenu.run();
                break;
            case USERNOTFOUND:
                System.out.println("username not found");
                break;
            case INCORRECTPASSWORD:
                System.out.println("password is incorrect");
                counterWrongPassword++;
                break;
        }
    }

    private static void pickQuestion() {
        System.out.println("Pick your security question: 1. What is my father’s name? 2. What\n" +
                "was my first pet’s name? 3. What is my mother’s last name?");
        while (true) {
            Scanner scanner = Scan.getScanner();
            String ans = scanner.nextLine();
            Matcher matcher;
            if (ans.equals("exit"))
                break;
            if ((matcher = LoginMenuCommands.getMatcher(ans, LoginMenuCommands.QUESTIONPICK)) != null) {
                if ((matcher = LoginMenuCommands.getMatcher(ans, LoginMenuCommands.QUSETIONNUMBER)) != null) {

                    int number = Integer.parseInt(matcher.group("number"));
                    if (number <= 0 || number > 3)
                        System.out.println("your number should be in range 1 to 3");
                    else
                        ansNumber = number;
                } else {
                    System.out.println("your number field is empty!");
                }
                if ((matcher = LoginMenuCommands.getMatcher(ans, LoginMenuCommands.ANSWERQUESTION)) != null) {
                    if ((matcher.group("answerWithoutSpace")) != null) {
                        answer = matcher.group("answerWithoutSpace");
                    }
                    else {
                        answer = matcher.group("answerWithSpace");
                        answer.trim();
                        answer = answer.substring(1, answer.length() - 1);
                    }
                } else {
                    System.out.println("your answer field is empty!");
                }
                if ((matcher = LoginMenuCommands.getMatcher(ans, LoginMenuCommands.CONFIRMANSWER)) != null) {
                    if ((matcher.group("answerWithoutSpace")) != null)
                        confirmAnswer = matcher.group("answerWithoutSpace");
                    else {
                        confirmAnswer = matcher.group("answerWithSpace");
                        confirmAnswer.trim();
                        confirmAnswer = confirmAnswer.substring(1, confirmAnswer.length() - 1);
                    }
                } else {
                    System.out.println("your answer field is empty!");
                }
                if (ansNumber != 0 && answer != null && confirmAnswer != null) {
                    if (!answer.equals(confirmAnswer)) {
                        System.out.println("answer and confirm answer should be equal");
                        ansNumber = 0;
                        confirmAnswer = null;
                        answer = null;
                    } else {
                        String securtiyAnswer = answer;
                        createUser(username, password, emailAddress, nickname, slogan, securtiyAnswer);
                        break;
                    }
                }
            }else {
                ansNumber = 0;
                confirmAnswer = null;
                answer = null;
                System.out.println("Invalid command!");
            }
        }

    }
    private static void checkUsername(String userName){
        if(username==null)
            return;
        LoginMenuMessage message = LoginSignupControl.checkUsername(username);
        switch (message) {
            case INVALIDUSERNAME:
                System.out.println("username format is invalid");
                break;
            case SUCCESS:
                validPassword(password);
                break;
            case SAMEUSERNAME:
                username=makeSuggestionUsername(userName);
                System.out.println(userName);
                if(username!=null) {
                    validPassword(password);
                }
                break;
        }

    }

    public static void createUser(String username, String password, String confirmPassword, String nickname, String slogan, String securityAnswer) {
    LoginMenuMessage message=LoginSignupControl.createUser(username,password,emailAddress,nickname,slogan,securityAnswer);
    if(message.equals(LoginMenuMessage.SUCCESS))
        System.out.println("user created successfully");
    }


    private static String makeSuggestionUsername(String username) {
        while (true) {
            Random random = new Random();
            int low = 10;
            int high = 100;
            int result = random.nextInt(high - low) + low;
            username = username + Integer.toString(result);
            LoginMenuMessage message = LoginSignupControl.checkUsername(username);
            if(message.equals(LoginMenuMessage.SUCCESS))
                break;
        }
        System.out.println("Suggested username is:  "+username);
        System.out.println("Type yes to confirm it");
        Scanner scanner=Scan.getScanner();
        String check=scanner.nextLine();
        if(check.equals("yes")) {
         //   System.out.println(username);
            return username;
        }
        else {
            return null;
        }
    }

        private static void validPassword (String password){
            if (password == null)
                return;
            if (randomPasswordFlag == 1) {
                String randomPass = LoginSignupControl.findRandomPassword();
                System.out.println("Your password is: " + randomPass);
                System.out.println("please type password for check it:");
                Scanner scanner = Scan.getScanner();
                String checkPassword = scanner.nextLine();
                if (checkPassword.equals(randomPass)) {
                    validEmail(emailAddress);
                } else {
                    System.out.println("password is incorrect!");
                    randomPasswordFlag=0;

                }
            } else {
                LoginMenuMessage message = LoginSignupControl.validatePassword(password);
                switch (message) {
                    case STRONGPASSWORD:
                        ConfirmPassword(password, confirmPassword);
                        break;
                    case WITHOUTUPPERCASE:
                        System.out.println("you dont have uppercase in your password!");
                        break;
                    case WITHOUTLOWERCASE:
                        System.out.println("you dont have lowercase in your password!");
                        break;
                    case WITHOUTNUMBER:
                        System.out.println("you dont have number in your password!");
                        break;
                    case LOW_LENGTH_PASS:
                        System.out.println("your password is short");
                        break;
                    case WITHOUTSPECIALCHARACTER:
                        System.out.println("you dont have character except number and alphabetical letters in your password!");
                        break;
                }
            }
        }

        private static void ConfirmPassword (String password, String confirmPassword){
        if(confirmPassword==null)
            return;
            if (password.equals(confirmPassword)) {
                validEmail(emailAddress);
            } else {
                System.out.println("Confirm password should be equal to password");
            }
        }

        private static void validEmail (String emailAddress){
        if(emailAddress==null)
            return;
            LoginMenuMessage message = LoginSignupControl.validateEmail(emailAddress);
            switch (message) {
                case INVALIDEMAILFORMAT:
                    System.out.println("this email format is invalid");
                    break;
                case DUPLICATEEMAIL:
                    System.out.println("this email already exists");
                    break;
                case SUCCESS:
                    checkSlogan();
                    break;
            }
        }

        private static void checkSlogan () {
            ArrayList<String> slogans = new ArrayList<>();
            if (hasSlogan == 1 && randomSloganFlag == 1) {
                LoginSignupControl.randomSlogan(slogans);
                Random random = new Random();
                int result = random.nextInt(9);
                slogan = slogans.get(result);

            } else {

            }
            pickQuestion();
        }

        private static void removeQutation (String mystring){
            if (mystring.charAt(0) == '"' && mystring.charAt(mystring.length() - 1) == '"')
                mystring = mystring.substring(1, mystring.length() - 1);

        }

        private static void timeLimit ( int counterWrongPassword) throws InterruptedException {
            if (counterWrongPassword >= 3) {
                Thread.sleep((counterWrongPassword - 2) * 5000);
            }
        }

        private static void makeNewPassword (String username){
            Scanner scanner = Scan.getScanner();
            String newPassword = scanner.nextLine();
            Matcher matcher;
            if ((matcher = LoginMenuCommands.getMatcher(newPassword, LoginMenuCommands.STRONGPASSWORD)) != null) {
                LoginMenuMessage message = LoginSignupControl.makeNewPassword(username, newPassword);
                switch (message) {
                    case SUCCESS:
                        System.out.println("password changed successfully");
                        break;
                }
            } else {
                System.out.println("new password should be strong");


            }
        }
    }
