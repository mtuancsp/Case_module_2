package get_input;

import login.Account;

import static login.AccountManager.getAccountsList;

public class getInput {

    public static String getNonexistentAccount() {
        String account;
        boolean isExist = false;
        String regex = "^[a-z0-9_]*$"; // Biểu thức chính quy cho chuỗi chỉ chứa chữ thường, số và dấu gạch dưới
        do {
            System.out.print("Nhập account: ");
            account = new java.util.Scanner(System.in).nextLine();
            if (!account.matches(regex)) {
                System.out.println("Tên tài khoản không hợp lệ. Vui lòng nhập lại.");
                continue; // Nếu tên tài khoản không hợp lệ, quay lại vòng lặp
            }
            isExist = false;
            for (Account acc : getAccountsList("admin")) {
                if (acc.getAccount().equals(account)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Tài khoản đã tồn tại. Vui lòng nhập lại.");
            }
        } while (isExist);

        return account;
    }

    public static String getExistAccount() {
        String account;
        boolean isExist = false;
        do {
            System.out.print("Nhập account: ");
            account = new java.util.Scanner(System.in).nextLine();
            for (Account acc : getAccountsList("admin")) {
                if (acc.getAccount().equals(account)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                System.out.println("Tài khoản không tồn tại. Vui lòng nhập lại.");
            }
        } while (!isExist);

        return account;
    }

    public static String getValidPassword() {
        String password;
        do {
            System.out.print("Nhập password: ");
            password = new java.util.Scanner(System.in).nextLine();
            if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))
            // mật khẩu phải phải có 8 ký tự trở lên (không chứa khoảng trắng),
            // trong đó chứa ít nhất 1 chữ thường, 1 chữ hoa, 1 số và 1 ký tự đặc biệt
            {
                System.out.println("Mật khẩu không đáp ứng yêu cầu. Vui lòng nhập lại.");
                continue; // quay lại vòng lặp
            }

            // quy định không chứa từ 'password'
            if (password.contains("password")) {
                System.out.println("Mật khẩu không được chứa từ 'password'. Vui lòng nhập lại.");
                continue;
            }

            // quy định không chứa chuỗi '123456'
            if (password.contains("123456")) {
                System.out.println("Mật khẩu không được chứa chuỗi '123456'. Vui lòng nhập lại.");
                continue;
            }

            return password;

        } while (true);
    }


}
