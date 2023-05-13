import control.LoginSignupControl;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import view.enums.commands.LoginMenuCommands;
import view.enums.messages.LoginMenuMessage;

import java.util.ArrayList;

public class LoginSignupTest {
    @Test
    public void testLoginUser1() {
        LoginMenuMessage message = LoginSignupControl.loginUser("", "Keyh123^", 0);
        Assertions.assertEquals(LoginMenuMessage.USERNOTFOUND, message);
    }

    @Test
    public void testLoginUser2() {
        LoginMenuMessage message = LoginSignupControl.loginUser("Keyhan", "fvdasfd", 0);
        Assertions.assertEquals(LoginMenuMessage.INCORRECTPASSWORD, message);
    }

    @Test
    public void testLoginUser3() {
        LoginMenuMessage message = LoginSignupControl.loginUser("Keyhan", "Keyh123^", 0);
        Assertions.assertEquals(LoginMenuMessage.SUCCESS, message);
    }

    @Test
    public void testValidatePassword1() {
        LoginMenuMessage message = LoginSignupControl.validatePassword("Key123^");
        Assertions.assertEquals(LoginMenuMessage.STRONGPASSWORD, message);
    }

    @Test
    public void testValidatePassword2() {
        LoginMenuMessage message = LoginSignupControl.validatePassword("key123^");
        Assertions.assertEquals(LoginMenuMessage.WITHOUTUPPERCASE, message);
    }

    @Test
    public void testValidatePassword3() {
        LoginMenuMessage message = LoginSignupControl.validatePassword("KEY123^");
        Assertions.assertEquals(LoginMenuMessage.WITHOUTLOWERCASE, message);
    }

    @Test
    public void testValidatePassword4() {
        LoginMenuMessage message = LoginSignupControl.validatePassword("Key^");
        Assertions.assertEquals(LoginMenuMessage.WITHOUTNUMBER, message);
    }

    @Test
    public void testValidatePassword5() {
        LoginMenuMessage message = LoginSignupControl.validatePassword("Key123");
        Assertions.assertEquals(LoginMenuMessage.WITHOUTSPECIALCHARACTER, message);
    }

    @Test
    public void testValidatePassword6() {
        LoginMenuMessage message = LoginSignupControl.validatePassword("Ke1^");
        Assertions.assertEquals(LoginMenuMessage.LOW_LENGTH_PASS, message);
    }

    @Test
    public void testCreateUser1() {
        LoginMenuMessage message = LoginSignupControl.createUser("Keyhan", "Keyh123^",
                "Keyhan@gmail.com", "Keyhanies", "random", "Mohseni");
        Assertions.assertEquals(LoginMenuMessage.SUCCESS, message);
    }

    @Test
    public void testCreateUser2() {
        LoginMenuMessage message = LoginSignupControl.createUser("sepehr", "Sepehr123^",
        "sepehr@gmail.com", "sepehries", "random", "in yekiam mohsenies");
    }

    @Test
    public void testCreateUser3() {
        LoginMenuMessage message = LoginSignupControl.createUser("Ardalan", "Ardal123^",
        "ardal@gmail.com", "ardalies", "random", "na baba, inam Mohseni?");
    }

    @Test
    public void testValidateEmail1() {
        LoginMenuMessage message = LoginSignupControl.validateEmail("Keyha@gmailcom");
        Assertions.assertEquals(LoginMenuMessage.INVALIDEMAILFORMAT, message);
    }

    @Test
    public void testValidateEmail2() {
        LoginMenuMessage message = LoginSignupControl.validateEmail("Keyhangmail.com");
        Assertions.assertEquals(LoginMenuMessage.INVALIDEMAILFORMAT, message);
    }

    @Test
    public void testValidateEmail3() {
        LoginMenuMessage message = LoginSignupControl.validateEmail("ardal@gmail.com");
        Assertions.assertEquals(LoginMenuMessage.DUPLICATEEMAIL, message);
    }

    @Test
    public void testValidateEmail4() {
        LoginMenuMessage message = LoginSignupControl.validateEmail("Keyhani@gmail.com");
        Assertions.assertEquals(LoginMenuMessage.SUCCESS, message);
    }

    @Test
    public void testFindRandomPassword1() {
        String password = LoginSignupControl.findRandomPassword();
        Assertions.assertNotNull(password);
    }

    @Test
    public void testFindRandomPassword2() {
        String password = LoginSignupControl.findRandomPassword();
        LoginMenuMessage message = LoginSignupControl.validatePassword(password);
        Assertions.assertEquals(LoginMenuMessage.STRONGPASSWORD, message);
    }

    @Test
    public void testCheckUsername1() {
        LoginMenuMessage message = LoginSignupControl.checkUsername("");
        Assertions.assertEquals(LoginMenuMessage.INVALIDUSERNAME, message);
    }

    @Test
    public void testCheckUsername3() {
        LoginMenuMessage message = LoginSignupControl.checkUsername("Keyhan1383");
        Assertions.assertEquals(LoginMenuMessage.SUCCESS, message);
    }

    @Test
    public void testRandomSlogan() {
        ArrayList<String> slogans = new ArrayList<>();
        LoginSignupControl.randomSlogan(slogans);
        Assertions.assertNotEquals(0, slogans.size());
    }

    @Test
    public void testForgotPassword1() {
        LoginMenuMessage message = LoginSignupControl.forgotPassword("Keyhan");
        Assertions.assertEquals(LoginMenuMessage.SECURITYQUESTION, message);
    }

    @Test
    public void testForgotPassword2() {
        LoginMenuMessage message = LoginSignupControl.forgotPassword("biuoshjksngsfugsrheavinhg");
        Assertions.assertEquals(LoginMenuMessage.INVALIDUSERNAME, message);
    }

    @Test
    public void testCheckSecurityAnswer1() {
        LoginMenuMessage message = LoginSignupControl.checkSecurityAnswer("ajiuhrklfngihn", "nisuhiajv");
        Assertions.assertNull(message);
    }

    @Test
    public void testMakeNewPassword1() {
        LoginMenuMessage message = LoginSignupControl.makeNewPassword("Keyhan", "Keyh123^");
        Assertions.assertEquals(LoginMenuMessage.SUCCESS, message);
    }

    @Test
    public void testMakeNewPassword2() {
        LoginMenuMessage message = LoginSignupControl.makeNewPassword("", "");
        Assertions.assertNull(message);
    }
}
