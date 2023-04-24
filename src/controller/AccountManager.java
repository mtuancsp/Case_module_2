package controller;

import model.AccessLevel;
import model.Account;
import read_write.ReadWrite;
import view.LoggedMenu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static get_input.Input.*;
import static read_write.ReadWrite.write;

public class AccountManager {
    private static List<Account> accountsList = new ArrayList<>();

    private AccountManager() throws IOException {
        write("src/file/accounts.txt", accountsList);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        accountsList.add(new Account("boss", "boss", "113", "boss", AccessLevel.BOSS));
        findAccount("boss").setJoinDate(LocalDate.parse("2022-01-01"));
        writeAccountsListToFile();
        displayAccountsListByAccessLevel();
    }

    public static void writeAccountsListToFile() throws IOException {
        write("src/file/accounts.txt", accountsList);
    }

    public static List<Account> updateListFromFile() throws IOException, ClassNotFoundException {
        return accountsList = ReadWrite.read("src/file/accounts.txt");
    }

    public static Set<String> getAccountSet() throws IOException, ClassNotFoundException {
        return updateListFromFile().stream().map(Account::getAccount).collect(Collectors.toSet());
    }

    public static Set<String> getPhoneNumberSet() throws IOException, ClassNotFoundException {
        return updateListFromFile().stream().map(Account::getPhoneNumber).collect(Collectors.toSet());
    }

    public static Set<String> getEmailSet() throws IOException, ClassNotFoundException {
        return updateListFromFile().stream().map(Account::getEmail).collect(Collectors.toSet());
    }



    public static Account findAccount(String account) throws IOException, ClassNotFoundException {
        return updateListFromFile().stream().filter(a -> a.getAccount().equals(account)).findFirst().orElse(null);
    }

    public static void displayAccountsListByAccessLevel() throws IOException, ClassNotFoundException {
        updateListFromFile().sort(Comparator.comparing(Account::getAccessLevel));
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách tài khoản");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-30s %-15s %-15s%n",
                "ACCOUNT", "USERNAME", "PASSWORD", "ACCESS LEVEL", "PHONE NUMBER", "EMAIL",
                "Join Date", "Ban Date");
        for (Account account : accountsList) {
            System.out.println(account);
        }
    }

    public static void deleteAccount() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Xóa tài khoản !!!");
        String account = getExistAccount();
        Account acc = findAccount(account);
        if (acc.getAccessLevel() == AccessLevel.USER) {
            checkPassword(LoggedMenu.getAccount());
            accountsList.remove(acc);
            System.out.println("Đã xóa tài khoản " + account);
            writeAccountsListToFile();
        } else {
            System.err.println("Không thể xóa tài khoản có cấp bậc cao hơn USER");
        }

    }

    //change username
    public static void changeUsername(Account acc) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Username hiện tại: " + acc.getUsername());
        System.out.println("Nhập username mới: ");
        String newUsername = scanner.nextLine();
        acc.setUsername(newUsername);
        System.out.println("Đã đổi username thành: " + newUsername);
        writeAccountsListToFile();
    }

    //change password
    public static void changePassword(Account acc) throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đổi mật khẩu");
        checkPassword(acc);
        setNewPassword(acc);
    }

    public static void setNewPassword(Account acc) throws IOException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Thiết lập mật khẩu mới");
        String newPassword = getValidPassword();
        while (newPassword.equals(acc.getPassword())) {
            System.out.println("Mật khẩu mới không được trùng với mật khẩu cũ.");
            newPassword = getValidPassword();
        }
        acc.setPassword(newPassword);
        writeAccountsListToFile();
        System.out.println("Đã đổi mật khẩu thành công");
    }

    public static void changePhoneNumber(Account acc) throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cập nhật số điện thoại");
        Scanner scanner = new Scanner(System.in);
        String currentPhoneNumber = acc.getPhoneNumber();
        String phone;

        do {
            System.out.print("Nhập số điện thoại hiện tại: ");
            phone = scanner.nextLine();
            if (!phone.equals(currentPhoneNumber)) {
                System.out.println("Số điện thoại không khớp. Vui lòng nhập lại.");
            }
        } while (!phone.equals(currentPhoneNumber));

        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Xác thực số điện thoại thành công");
        System.out.println("Thiết lập số điện thoại mới");
//        String newPhoneNumber = scanner.nextLine();
        String newPhoneNumber = getValidPhoneNumber();
        acc.setPhoneNumber(newPhoneNumber);
        System.out.println("Đã thay đổi số điện thoại thành công.");
        writeAccountsListToFile();
    }

    public static void changeEmail(Account acc) throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cập nhật email");
        Scanner scanner = new Scanner(System.in);
        String currentEmail = acc.getEmail();
        String email;
        do {
            System.out.print("Nhập email hiện tại: ");
            email = scanner.nextLine();
            if (!email.equals(currentEmail)) {
                System.out.println("Email không khớp. Vui lòng nhập lại.");
            }
        } while (!email.equals(currentEmail));

        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Xác thực email thành công");
        System.out.println("Thiết lập email mới");
        String newEmail = getValidEmail();
        acc.setEmail(newEmail);
        writeAccountsListToFile();
        System.out.println("Đã thay đổi email thành công.");
    }

    public static String getJoinDuration(Account acc) {
        LocalDate currentDate = LocalDate.now();
        Period duration = Period.between(acc.getJoinDate(), currentDate);

        int years = duration.getYears();
        int months = duration.getMonths();
        int days = duration.getDays();

        String str;
        if (years >= 3) {
            str = "Trùm sò";
        } else if (years == 2) {
            str = "Lão luyện";
        } else if (years == 1) {
            str = "Kinh nghiệm";
        } else {
            str = "A ma tơ";
        }

        return years + " năm, " + months + " tháng, " + days + " ngày" + "  ─>  " + str.toUpperCase();
    }

}

