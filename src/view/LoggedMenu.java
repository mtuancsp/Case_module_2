package view;

import model.Account;

import java.io.IOException;

import static controller.AccessLevelManager.*;
import static controller.AccountManager.*;
import static controller.UserInfoManager.*;
import static login.LoginSystem.logInMenu;
import static view.Menu.getValidIntChoice;
import static view.Menu.returnOrExit;
import static view.Web.*;

public class LoggedMenu {

    private static Account account;

    public static Account getAccount() {
        return account;
    }

    public static void loggedMenu(Account acc) throws IOException, ClassNotFoundException {
        account = acc;
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Chào mừng '" + account.getUsername() + "' đã quay trở lại");
        int choice;

        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("""
                    1. Thông tin tài khoản
                    2. Thông tin cá nhân
                    3. Menu
                    4. Web
                    0. Đăng xuất""");
            choice = getValidIntChoice(0, 4);

            switch (choice) {
                case 1 -> infoAccount();
                case 2 -> userInfo();
                case 3 -> accountMenu();
                case 4 -> web();
                case 0 -> logout();
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (choice != 0);
    }

    public static void infoAccount() throws IOException, ClassNotFoundException {

        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("Thông tin tài khoản");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-30s %-15s %-15s%n",
                    "ACCOUNT", "USERNAME", "PASSWORD", "ACCESS LEVEL", "PHONE NUMBER", "EMAIL",
                    "Join Date", "Ban Date");
            System.out.println(account);
            System.out.println("Thâm niên trong ngành: " + getJoinDuration(account));
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            int choice;
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
            System.out.println("4. Xoá tài khoản user");
            System.out.println("5. Thông tin người dùng");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 5);
            switch (choice) {
                case 1 -> manageADMIN();
                case 2 -> manageMODERATOR();
                case 3 -> manageUSER();
                case 4 -> deleteAccount();
                case 5 -> usersInfo();
                case 0 -> {
                    return;
                }
            }

        } while (true);
    }

    private static void usersInfo() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Thông tin người dùng trong hệ thống");
        getUsersInfo().forEach(System.out::println);
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        returnOrExit();
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

    public static void adminMenu() throws IOException, ClassNotFoundException {
        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Quản lý MODERATOR");
            System.out.println("2. Quản lý USER");
            System.out.println("3. Xoá tài khoản user");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 3);
            switch (choice) {
                case 1 -> manageMODERATOR();
                case 2 -> manageUSER();
                case 3 -> manageForum();
                case 4 -> deleteAccount();
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static void moderatorMenu() throws IOException, ClassNotFoundException {
        do{
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Quản lý USER");
            System.out.println("2. Quản lý forum");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 2);
            switch (choice) {
                case 1 -> manageUSER();
                case 2 -> manageForum();
                case 0 -> {
                    return;
                }
            }

        } while (true);
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



    public static void logout() throws IOException, ClassNotFoundException {
        writeAccountsListToFile();
        logInMenu();
    }


    private static void manageUSER() throws IOException, ClassNotFoundException {
        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Danh sách USER");
            System.out.println("2. Danh sách USER bị BAN");
            System.out.println("3. Khóa USER");
            System.out.println("4. Mở khóa USER");
            System.out.println("0. Quay lại");
            int choice = getValidIntChoice(0, 4);
            switch (choice) {
                case 1 -> listUSER();
                case 2 -> listBannedUser();
                case 3 -> banUSER();
                case 4 -> unbanUSER();
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    private static void manageMODERATOR() throws IOException, ClassNotFoundException {
        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("1. Danh sách MOD");
            System.out.println("2. Thêm MOD");
            System.out.println("3. Tước quyền MOD");
            System.out.println("0. Quay lại");
            int choice = getValidIntChoice(0, 3);
            switch (choice) {
                case 1 -> listMODERATOR();
                case 2 -> addMODERATOR();
                case 3 -> removeMODERATOR();
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static void userMenu() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.err.println("Công trường đang thi công mời ra ngoài...");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
    }

    private static void manageForum() {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.err.println("Công trường đang thi công mời ra ngoài...");
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
    }


}
