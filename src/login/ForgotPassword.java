package login;

import model.Account;

import java.io.IOException;
import java.util.Scanner;

import static controller.AccountManager.*;
import static get_input.Input.*;
import static login.RandomPasswordGenerator.generateRandomPassword;

public class ForgotPassword {
    public static void forgotPassword() throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("───────────────────────────────────────────────────────────────────────────────");
        System.out.println("Lấy lại mật khẩu");
        System.out.println("Nhập tên đăng nhập muốn lấy lại mật khẩu");
        String account = getExistAccount();

        Account acc = findAccount(account);
        String phoneNumberOfAccount = acc.getPhoneNumber();
        String phoneNumber;
        boolean isPhoneNumberMatch = false;

        do {
            System.out.println("Nhập số điện thoại để lấy lại mật khẩu: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.equals(phoneNumberOfAccount)) {
                isPhoneNumberMatch = true;
            } else {
                System.out.println("Số điện thoại không khớp với tài khoản. Vui lòng nhập lại.");
            }

        } while (!isPhoneNumberMatch);

        String newPassword = generateRandomPassword();
        acc.setPassword(newPassword);
        writeAccountsListToFile();
        System.out.println("Đã gửi mật khẩu mới về số điện thoại.");
        System.out.println("Mật khẩu: " + newPassword);
    }
}
