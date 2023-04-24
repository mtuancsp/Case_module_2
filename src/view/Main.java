package view;

import static controller.AccountManager.*;
import static login.LoginSystem.logInMenu;

public class Main {
    public static void main(String[] args) {
        try {
            displayAccountsListByAccessLevel();
            logInMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
