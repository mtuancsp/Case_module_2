package view;

import static controller.AccountManager.displayAccountsListByAccessLevel;
import static login.LoginSystem.logInMenu;

public class Main {
    public static void main(String[] args) {
        try {
            displayAccountsListByAccessLevel();
            logInMenu();
        }
        catch (Exception e) {
            System.err.println("Có bug rồi anh zai ơi");
        }

    }


}
