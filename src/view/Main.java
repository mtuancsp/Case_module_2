package view;

import static login.LoginSystem.logInMenu;

public class Main {
    public static void main(String[] args) {

        try {
            logInMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
