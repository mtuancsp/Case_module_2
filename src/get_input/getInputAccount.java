package get_input;

import login.Account;

import static login.AccountManager.getAccountsList;

public class getInputAccount {

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

}
