package login;

import model.Account;

import java.io.IOException;

import static controller.AccountManager.updateListFromFile;
import static controller.AccountManager.writeAccountsListToFile;
import static get_input.Input.*;

public class SingUp {

    public static void signUp() throws IOException, ClassNotFoundException {
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Đăng kí tài khoản mới, tên tài khoản chỉ chứa chữ thường, số và dấu gạch dưới.");
        String account = getValidAccount();
        String password = getValidPassword();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Thiết lập số điện thoại (vui lòng nhập đúng số điện thoại)");
        String phoneNumber = getValidPhoneNumber();
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Thiết lập email (vui lòng nhập đúng email)");
        String email = getValidEmail();
        var accountsList = updateListFromFile();
        accountsList.add(new Account(account, password, phoneNumber, email));
        writeAccountsListToFile();
        System.out.println("Đăng kí thành công tài khoản: " + account);
    }
}
