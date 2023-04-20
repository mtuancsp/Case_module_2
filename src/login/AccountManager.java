package login;

import read_write.ReadWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static get_input.getInput.getExistAccount;
import static get_input.getInput.getNonexistentAccount;

public class AccountManager {
    private static List<Account> accountsList = new ArrayList<>();

    private AccountManager() {
    }

    public static List<Account> getAccountsList(String username) {
        return accountsList;
    }

    public static void updateListFromFile() throws IOException, ClassNotFoundException {
        accountsList = ReadWrite.read("src/file/accounts.txt");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        displayAccountsListByAccessLevel();
        signUp();
    }

    public void addAccount(Account account) throws IOException {
        accountsList.add(account);
        ReadWrite.write("src/file/accounts.txt", accountsList);
    }

    public static void signUp() throws IOException {
        System.out.print("Đăng kí tài khoản mới: ");
        String account = getNonexistentAccount();
        System.out.print("Tạo mật khẩu: ");
        String password = getNonexistentAccount();
        accountsList.add(new Account(account, password, AccessLevel.USER));
        ReadWrite.write("src/file/accounts.txt", accountsList);
        System.out.println("Đăng kí thành công tại khoản: " + account);
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
        ReadWrite.write("src/file/accounts.txt", accountsList);
    }
}




