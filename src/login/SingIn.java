package login;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static controller.AccountManager.updateListFromFile;
import static login.ForgotPassword.forgotPassword;
import static view.LoggedMenu.loggedMenu;

public class SingIn {

    public static void signIn() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────");
            System.out.println("Đăng nhập");
            System.out.print("Tài khoản: ");
            String account = scanner.nextLine();
            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();

            var acc = updateListFromFile().stream().filter(a -> a.getAccount().equals(account) && a.getPassword().equals(password)).findFirst().orElse(null);
            if (acc != null) {
                if (acc.getBanDate() != null && acc.getBanDate().isAfter(LocalDate.now())){
                    System.err.println("Tài khoản đã bị BAN đến " + acc.getBanDate());
                    System.err.println("Thời gian bị BAN còn lại: " + ChronoUnit.DAYS.between(LocalDate.now(), acc.getBanDate()) + " ngày");
                }
                else {
                    System.out.println("Đăng nhập thành công");
                    loggedMenu(acc);
                }

            } else {
                System.out.println("Tài khoản hoặc mật khẩu không đúng");
                System.out.print("1.Thử đăng nhập lại\n2.Quên mật khẩu\n0.Quay lại\nLựa chọn của bạn: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        break;
                    case "2":
                        forgotPassword();
                        break;
                    case "0":
                            return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                        break;
                }
            }
        } while (true);
    }

}
