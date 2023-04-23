package view;

import model.Account;

import java.io.IOException;

import static controller.AccountManager.*;
import static controller.UserInfoManager.*;
import static login.LoginSystem.logInMenu;
import static view.Menu.getValidIntChoice;

public class LoggedMenu {
    private static Account account;

    public static void loggedMenu(Account acc) throws IOException, ClassNotFoundException {
        account = acc;
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Chào mừng " + account.getUsername());
        int choice;

        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("""
                    1. Thông tin tài khoản
                    2. Thông tin cá nhân
                    3. Menu
                    0. Đăng xuất""");

            choice = getValidIntChoice(0, 3);

            switch (choice) {
                case 1 -> infoAccount();
                case 2 -> userInfo();
                case 3 -> accountMenu();
                case 0 -> logout();
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (choice != 0);

    }

    public static void accountMenu() throws IOException, ClassNotFoundException {
        switch (account.getAccessLevel()) {
            case BOSS -> bossMenu();
            case ADMIN -> adminMenu();
            case MODERATOR -> moderatorMenu();
            case USER -> userMenu();
        }
    }

    public static void bossMenu() throws IOException, ClassNotFoundException {
        do{
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Quản lý ADMIN");
            System.out.println("2. Quản lý MODERATOR");
            System.out.println("3. Quản lý USER");
            System.out.println("4. Quản lý trang Web");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 4);
            switch (choice) {
                case 1 -> manageADMIN();
                case 2 -> manageMODERATOR();
                case 3 -> manageUSER();
                case 4 -> manageWEB();
                case 0 -> {
                    return;
                }
            }

        } while (true);
    }

    private static void manageWEB() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đang xây dựng...");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
    }

    private static void manageUSER() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đang xây dựng...");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
    }

    private static void manageMODERATOR() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đang xây dựng...");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
    }

    private static void manageADMIN() throws IOException, ClassNotFoundException {
        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Danh sách ADMIN");
            System.out.println("2. Thêm ADMIN");
            System.out.println("3. Tước quyền ADMIN");
            System.out.println("0. Quay lại");
            int choice = getValidIntChoice(0, 3);
            switch (choice) {
                case 1 -> listADMIN();
                case 2 -> addADMIN();
                case 3 -> removeADMIN();
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static void adminMenu() {
        do{
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Quản lý ADMIN");
            System.out.println("2. Quản lý MODERATOR");
            System.out.println("3. Quản lý USER");
            System.out.println("4. Quản lý trang Web");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 3);
            switch (choice) {
                case 1 -> manageMODERATOR();
                case 2 -> manageUSER();
                case 3 -> manageWEB();
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static void moderatorMenu() {
        do{
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Quản lý USER");
            System.out.println("2. Quản lý trang Web");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 2);
            switch (choice) {
                case 1 -> manageUSER();
                case 2 -> manageWEB();
                case 0 -> {
                    return;
                }
            }

        } while (true);
    }

    public static void userMenu() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đang xây dựng...");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
    }


    private static void userInfo() throws IOException, ClassNotFoundException {
        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("Thông tin cá nhân\n" + account.getUserInformation());
            int choice;
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("""
                    1. Cập nhật CCCD/CMND
                    2. Cập nhật họ và tên
                    3. Cập nhật ngày sinh
                    4. Cập nhật mô tả
                    0. Quay lại""");
            choice = getValidIntChoice(0, 4);
            switch (choice) {
                case 1 -> changeId(account);
                case 2 -> changeName(account);
                case 3 -> changeBirthDate(account);
                case 4 -> changeDescription(account);
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static void infoAccount() throws IOException, ClassNotFoundException {

        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("Thông tin tài khoản\n" + account);
            int choice;
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("""
                    1. Đổi username
                    2. Đổi mật khẩu
                    3. Đổi số điện thoại
                    4. Đổi email
                    0. Quay lại""");

            choice = getValidIntChoice(0, 4);

            switch (choice) {
                case 1 -> changeUsername(account);
                case 2 -> changePassword(account);
                case 3 -> changePhoneNumber(account);
                case 4 -> changeEmail(account);
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static void logout() throws IOException, ClassNotFoundException {
        logInMenu();
    }
}
