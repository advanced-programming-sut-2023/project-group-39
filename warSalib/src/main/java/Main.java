import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import control.GameControl;
import control.LoginSignupControl;
import model.Game;
import model.user.User;
import view.LoginSignupMenu;

public class Main {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    LoginSignupControl loginSignupControl=new LoginSignupControl();
    LoginSignupControl.readUsersData();
    LoginSignupMenu runner=new LoginSignupMenu();
    runner.run();

    }

}