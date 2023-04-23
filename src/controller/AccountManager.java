package controller;

import model.AccessLevel;
import model.Account;
import read_write.ReadWrite;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static get_input.Input.*;
import static read_write.ReadWrite.write;
import static view.Menu.getValidIntChoice;

public class AccountManager {
    private static List<Account> accountsList = new ArrayList<>();

    private AccountManager() throws IOException {
        write("src/file/accounts.txt", accountsList);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        accountsList.add(new Account("boss", "boss", "113", "boss", AccessLevel.BOSS));
        writeAccountsListToFile();
    }

    public static List<Account> getAccountsList() {
        return accountsList;
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

    //display ADMIN accounts
    public static void listADMIN() throws IOException, ClassNotFoundException {
        updateListFromFile();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách ADMIN");
        accountsList.stream().filter(acc -> acc.getAccessLevel() == AccessLevel.ADMIN).forEach(System.out::println);
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
                System.out.println("Định tự hủy à ??? Không có đâu");
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
                            acc.setAccessLevel(AccessLevel.MODERATOR);
                            System.out.println("Đã hạ cấp tài khoản '" + account + "' thành MODERATOR");
                            writeAccountsListToFile();
                            return;
                        }
                        case 2 -> {
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

    public static Account findAccount(String account) throws IOException, ClassNotFoundException {
        return accountsList.stream().filter(a -> a.getAccount().equals(account)).findFirst().orElse(null);
    }

    public static void displayAccountsListByAccessLevel() throws IOException, ClassNotFoundException {
        updateListFromFile().sort(Comparator.comparing(Account::getAccessLevel));
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Danh sách tài khoản");
        for (Account account : accountsList) {
            System.out.println(account);
        }
    }

    public static void deleteAccount() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Xóa tài khoản !!!");
        String account = getExistAccount();
        accountsList.removeIf(acc -> acc.getAccount().equals(account));
        System.out.println("Đã xóa tài khoản " + account);
        writeAccountsListToFile();
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
        String newPhoneNumber = getValidPhoneNumber();
        acc.setPhoneNumber(newPhoneNumber);
        writeAccountsListToFile();
        System.out.println("Đã thay đổi số điện thoại thành: " + newPhoneNumber);
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
        System.out.println("Đã thay đổi email thành: " + newEmail);
    }

    public String getJoinDuration(Account acc) {
        LocalDate currentDate = LocalDate.now();
        Period duration = Period.between(acc.getJoinDate(), currentDate);

        int years = duration.getYears();
        int months = duration.getMonths();
        int days = duration.getDays();

        return years + " năm, " + months + " tháng, " + days + " ngày";
    }

}

