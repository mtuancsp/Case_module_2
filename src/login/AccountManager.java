package login;

import read_write.ReadWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static get_input.getInputAccount.getExistAccount;

public class AccountManager {
    private static List<Account> accountsList = new ArrayList<>();

    private AccountManager() {
    }

    public static List<Account> getAccountsList(String username) {
        return accountsList;
    }

    public static void updateListFromFile() throws IOException, ClassNotFoundException {
        accountsList = ReadWrite.read("src/case_md4/file/accounts.txt");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        displayAccountsListByAccessLevel();
        deleteAccount();
    }

    public void addAccount(Account account) throws IOException {
        accountsList.add(account);
        ReadWrite.write("src/case_md4/file/accounts.txt", accountsList);
    }

    public static void signUp() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên tài khoản mới: ");
        String account = scanner.nextLine();
        boolean isDuplicate = false;
        while (true) {
            for (Account acc : accountsList) {
                if (acc.getAccount().equals(account)) {
                    isDuplicate = true;
                    System.out.println("Tài khoản đã tồn tại.");
                    System.out.print("Vui lòng nhập lại một tên tài khoản khác:");
                    account = scanner.nextLine();
                    break;
                }
            }
            if (!isDuplicate) {
                System.out.print("Nhập mật khẩu: ");
                String password = scanner.nextLine();
                accountsList.add(new Account(account, password, AccessLevel.USER));
                System.out.println("Đăng kí thành công.");
                ReadWrite.write("src/case_md4/file/accounts.txt", accountsList);
                break;
            } else {
                isDuplicate = false;
            }
        }
    }

    public static void displayAccountsListByAccessLevel() throws IOException, ClassNotFoundException {
        updateListFromFile();
        accountsList.sort(Comparator.comparing(Account::getAccessLevel));
        System.out.println("Danh sách tài khoản");
        for (Account account : accountsList) {
            System.out.println(account);
        }
    }

    public static void deleteAccount() throws IOException {
        System.out.println("Xóa tài khoản !!!");
        String account = getExistAccount();
        accountsList.removeIf(acc -> acc.getAccount().equals(account));
        System.out.println("Đã xóa tài khoản " + account);
        ReadWrite.write("src/case_md4/file/accounts.txt", accountsList);
    }
}



