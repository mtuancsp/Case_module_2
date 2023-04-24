package controller;

import model.AccessLevel;
import view.LoggedMenu;

import java.io.IOException;
import java.time.LocalDate;

import static controller.AccountManager.*;
import static get_input.Input.checkPassword;
import static get_input.Input.getExistAccount;
import static view.Menu.getValidIntChoice;

public class AccessLevelManager {

    public static void listADMIN() throws IOException, ClassNotFoundException {
        updateListFromFile();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách ADMIN");
        updateListFromFile().stream().filter(acc -> acc.getAccessLevel() == AccessLevel.ADMIN).forEach(System.out::println);
    }

    //add admin
    public static void addADMIN() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Thêm ADMIN");
        do {
            String account = getExistAccount();
            var acc = findAccount(account);
            if (acc.getAccessLevel() == AccessLevel.ADMIN || acc.getAccessLevel() == AccessLevel.BOSS) {
                System.out.println("Tài khoản đã là ADMIN hoặc cao hơn, vui lòng kiểm tra lại");
            } else {
                checkPassword(LoggedMenu.getAccount());
                acc.setAccessLevel(AccessLevel.ADMIN);
                System.out.println("Đã nâng cấp tài khoản '" + account + "' thành ADMIN");
                writeAccountsListToFile();
                break;
            }
        } while (true);
    }

    public static void removeADMIN() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Tước quyền ADMIN");
        do {
            String account = getExistAccount();
            var acc = findAccount(account);
            if (acc.getAccessLevel() == AccessLevel.BOSS) {
                System.err.println("Định tự hủy à ??? Không có đâu");
            }
            if (acc.getAccessLevel() != AccessLevel.ADMIN) {
                System.out.println("Tài khoản không phải quyền ADMIN, vui lòng kiểm tra lại");
            } else {
                do {
                    System.out.println("───────────────────────────────────────────────────────────────────────────────");
                    System.out.println("Bạn muốn hạ tài khoản '" + account + "' xuống cấp độ nào?");
                    System.out.println("1. MODERATOR");
                    System.out.println("2. USER");
                    System.out.println("0. Quay lại");
                    int choice = getValidIntChoice(0, 2);
                    switch (choice) {
                        case 1 -> {
                            checkPassword(LoggedMenu.getAccount());
                            acc.setAccessLevel(AccessLevel.MODERATOR);
                            System.out.println("Đã hạ cấp tài khoản '" + account + "' thành MODERATOR");
                            writeAccountsListToFile();
                            return;
                        }
                        case 2 -> {
                            checkPassword(LoggedMenu.getAccount());
                            acc.setAccessLevel(AccessLevel.USER);
                            System.out.println("Đã hạ cấp tài khoản '" + account + "' thành USER");
                            writeAccountsListToFile();
                            return;
                        }
                        case 0 -> {
                            return;
                        }
                    }
                } while (true);
            }
        } while (true);
    }

    public static void listMODERATOR() throws IOException, ClassNotFoundException {
        updateListFromFile();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách MODERATOR");
        updateListFromFile().stream().filter(acc -> acc.getAccessLevel() == AccessLevel.MODERATOR).forEach(System.out::println);
    }

    public static void addMODERATOR() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Thêm MODERATOR");
        do {
            String account = getExistAccount();
            var acc = findAccount(account);
            if (acc.getAccessLevel() == AccessLevel.MODERATOR || acc.getAccessLevel() == AccessLevel.ADMIN || acc.getAccessLevel() == AccessLevel.BOSS) {
                System.out.println("Tài khoản đã là MODERATOR hoặc cao hơn, vui lòng kiểm tra lại");
            } else {
                acc.setAccessLevel(AccessLevel.MODERATOR);
                System.out.println("Đã nâng cấp tài khoản '" + account + "' thành MODERATOR");
                writeAccountsListToFile();
                break;
            }
        } while (true);
    }

    public static void removeMODERATOR() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Tước quyền MODERATOR");
        do {
            String account = getExistAccount();
            var acc = findAccount(account);
            if (acc.getAccessLevel() == AccessLevel.BOSS || acc.getAccessLevel() == AccessLevel.ADMIN) {
                System.err.println("Tài khoản này cấp cao hơn MODERATOR, vui lòng kiểm tra lại");
            }
            if (acc.getAccessLevel() == AccessLevel.USER) {
                System.out.println("Tài khoản này đã là USER, vui lòng kiểm tra lại");
            } else {
                acc.setAccessLevel(AccessLevel.USER);
                System.out.println("Đã hạ cấp tài khoản '" + account + "' thành USER");
                writeAccountsListToFile();
                break;
            }
        } while (true);
    }

    public static void listUSER() throws IOException, ClassNotFoundException {
        updateListFromFile();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách USER");
        updateListFromFile().stream().filter(acc -> acc.getAccessLevel() == AccessLevel.USER).forEach(System.out::println);
    }

    public static void listBannedUser() throws IOException, ClassNotFoundException {
        updateListFromFile();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách USER đang bị ban");
        updateListFromFile().stream().filter(acc -> acc.getBanDate() != null).forEach(System.out::println);
    }

    public static void unbanUSER() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Mở khóa tài khoản");
        String account;
        do{
            account = getExistAccount();
            var acc = findAccount(account);
            if (acc.getBanDate() == null) {
                System.out.println("Tài khoản này không bị khóa, vui lòng kiểm tra lại");
            }
            if (acc.getBanDate() != null) {
                acc.setBanDate(null);
                System.out.println("Đã mở khóa tài khoản '" + account + "'");
                writeAccountsListToFile();
                break;
            }
        } while (true);
    }

    public static void banUSER() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("BAN USER");
        String account = getExistAccount();
        var acc = findAccount(account);
        do {
            System.out.println("Bạn muốn khóa tài khoản '" + account + "' bao lâu?");
            System.out.println("1. 1 ngày");
            System.out.println("2. 3 ngày");
            System.out.println("3. 1 tuần");
            System.out.println("4. 1 tháng");
            System.out.println("5. 999 năm");
            System.out.println("0. Quay lại");
            int choice = getValidIntChoice(0, 5);
            switch (choice) {
                case 1 -> {
                    acc.setBanDate(LocalDate.now().plusDays(1));
                    writeAccountsListToFile();
                    System.out.println("Đã khóa tài khoản '" + account + "' 1 ngày");
                    return;
                }
                case 2 -> {
                    acc.setBanDate(LocalDate.now().plusDays(3));
                    writeAccountsListToFile();
                    System.out.println("Đã khóa tài khoản '" + account + "' 3 ngày");
                    return;
                }
                case 3 -> {
                    acc.setBanDate(LocalDate.now().plusDays(7));
                    writeAccountsListToFile();
                    System.out.println("Đã khóa tài khoản '" + account + "' 1 tuần");
                    return;
                }
                case 4 -> {
                    acc.setBanDate(LocalDate.now().plusMonths(1));
                    writeAccountsListToFile();
                    System.out.println("Đã khóa tài khoản '" + account + "' 1 tháng");
                    return;
                }
                case 5 -> {
                    acc.setBanDate(LocalDate.now().plusYears(999));
                    writeAccountsListToFile();
                    System.out.println("Đã khóa tài khoản '" + account + "' 999 năm");
                    return;
                }
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }
}
