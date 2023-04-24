package login;

import model.Account;

import java.io.IOException;

import static controller.AccountManager.updateListFromFile;
import static controller.AccountManager.writeAccountsListToFile;
import static get_input.Input.getExistPhoneNumber;
import static login.RandomPasswordGenerator.generateRandomPassword;
import static login.SingIn.signIn;
import static login.SingUp.signUp;
import static view.Menu.getValidIntChoice;
import static view.Menu.returnOrExit;

public class LoginSystem {

    public static void logInMenu () throws IOException, ClassNotFoundException {
        int choice;
        do {
            displayLogInMenu();
            choice = getValidIntChoice(0 , 4);

            switch (choice) {
                case 1 -> signIn();
                case 2 -> signUp();
                case 3 -> guess();
                case 4 -> reissueAccount();
                case 0 -> exit();
            }
        } while (choice != 0);
    }

    public static void reissueAccount() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cấp lại tài khoản về số điện thoại");
        String phoneNumber = getExistPhoneNumber();
        for (Account account : updateListFromFile()) {
            if (account.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("───────────────────────────────────────────────────────────────────────────────");
                System.out.println("Tài khoản đã được gửi về số điện thoại");
                System.out.println("Tài khoản: " + account.getAccount());
                System.out.println("Mật khẩu: " + generateRandomPassword());
                break;
            }
        }
    }

    public static void displayLogInMenu() {
        System.out.println("""
                ───────────────────────────────────────────────────────────────────────────────
                CHÀO MỪNG CÁC BẠN ĐÃ ĐẾN VỚI BÌNH NGUYÊN VÔ TẬN
                ───────────────────────────────────────────────────────────────────────────────
                1. Đăng nhập
                2. Đăng ký
                3. Guess
                4. Cấp lại tài khoản
                0. Exit
                ───────────────────────────────────────────────────────────────────────────────""");
    }

    public static void guess() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đang xây dựng vui lòng quay lại sau ...");
        System.out.println("   _   ");
        System.out.println(" ('v')");
        System.out.println(" /-=-\\");
        System.out.println("(     )");
        System.out.println(" ^^ ^^");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        returnOrExit();
    }

    public static void exit() throws IOException {
        writeAccountsListToFile();
        System.out.println("Exiting...");
        System.exit(0);
    }
}
