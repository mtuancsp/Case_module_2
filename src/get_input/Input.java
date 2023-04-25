package get_input;

import model.Account;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import static controller.AccountManager.*;
import static login.ForgotPassword.forgotPassword;
import static login.LoginSystem.logInMenu;

public class Input {

    public static String getValidAccount() throws IOException, ClassNotFoundException {
        String account;
        boolean isValid;
        String regex = "^[a-z0-9_]*$";
        Set<String> existingAccounts = getAccountSet();
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.print("Nhập tên tài khoản: ");
            account = scanner.nextLine();
            if (account.equals("0")) logInMenu();

            if (!account.matches(regex)) {
                System.out.println("Tên tài khoản không hợp lệ. Vui lòng nhập lại.");
                isValid = false;
                continue;
            }

            if (existingAccounts.contains(account)) {
                isValid = false;
                System.out.println("Tài khoản đã tồn tại. Vui lòng nhập lại.");
            } else {
                isValid = true;
            }

        } while (!isValid);

        return account;
    }

    public static String getExistAccount() throws IOException, ClassNotFoundException {
        Set<String> existingAccounts = getAccountSet();
        String account;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập account: ");
            account = scanner.nextLine();

            if (existingAccounts.contains(account)) {
                return account;

            } else {
                System.out.println("Tài khoản không tồn tại. Vui lòng nhập lại.");
            }
        }
    }

    public static String getValidPassword() {
        String password;
        boolean isValid = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            System.out.println("Mật khẩu phải có 8 ký tự trở lên, không bao gồm khoảng trắng, và trong đó có ít nhất 1 chữ thường, 1 chữ hoa, 1 số, 1 kí tự đặc biệt.");
            System.out.print("Nhập mật khẩu: ");
            password = scanner.nextLine();

            if (!isPasswordValid(password)) {
                System.out.println("Mật khẩu không đáp ứng yêu cầu. Vui lòng nhập lại.");
                continue;
            }

            if (password.contains("password")) {
                System.out.println("Mật khẩu không được chứa từ 'password'. Vui lòng nhập lại.");
                continue;
            }

            if (password.contains("12345678")) {
                System.out.println("Mật khẩu không được chứa chuỗi '12345678'. Vui lòng nhập lại.");
                continue;
            }

            isValid = true;

        } while (!isValid);

        return password;
    }

    // Kiểm tra mật khẩu
    // mật khẩu phải có 8 ký tự trở lên (không chứa khoảng trắng),
    // trong đó chứa ít nhất 1 chữ thường, 1 chữ hoa, 1 số và 1 ký tự đặc biệt
    public static boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    public static String getValidPhoneNumber() throws IOException, ClassNotFoundException {
        Set<String> existingPhoneNumbers = getPhoneNumberSet();
        String newPhoneNumber;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Nhập số điện thoại: ");
            newPhoneNumber = scanner.nextLine();

            if (!newPhoneNumber.matches("^0[0-9]{9}$")) {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
                continue;
            }
            if (existingPhoneNumbers.contains(newPhoneNumber)) {
                System.out.println("Số điện thoại đã liên kết với tài khoản khác. Vui lòng nhập số điện thoại khác.");
                continue;
            }

            return newPhoneNumber;

        } while (true);
    }

    public static String getValidEmail() throws IOException, ClassNotFoundException {
        Set<String> existingEmails = getEmailSet();
        String newEmail;

        do {
            System.out.print("Nhập email: ");
            newEmail = new Scanner(System.in).nextLine();

            if (!newEmail.matches("^[A-Za-z0-9]+[A-Za-z0-9_]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+$")) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                continue;
            }
            if (existingEmails.contains(newEmail)) {
                System.out.println("Email đã liên kết với tài khoản khác. Vui lòng nhập email khác.");
                continue;
            }

            return newEmail;

        } while (true);
    }

    public static String getExistPhoneNumber() throws IOException, ClassNotFoundException {
        Set<String> existingPhoneNumbers = getPhoneNumberSet();
        String phoneNumber;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phoneNumber = scanner.nextLine();

            if (existingPhoneNumbers.contains(phoneNumber)) {
                return phoneNumber;

            } else {
                System.out.println("Số điện thoại không tồn tại trong hệ thống. Vui lòng nhập lại.");
            }
        }
    }

    public static void checkPassword(Account acc) throws IOException, ClassNotFoundException {
        String currentPassword = acc.getPassword();
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.print("Nhập mật khẩu hiện tại: ");
        password = scanner.nextLine();

        do {
            if (!password.equals(currentPassword)) {
                System.out.println("Mật khẩu không đúng. Vui lòng nhập lại hoặc nhập 'forgot' nếu đã quên mật khẩu:");
                password = scanner.nextLine();
            }
        } while (!password.equals(currentPassword) && !password.equals("forgot"));

        if (password.equals("forgot")) {
            forgotPassword();
            logInMenu();
        }
    }

    // getValidId
    public static String getValidId() {
        Scanner scanner = new Scanner(System.in);
        String id;
        boolean isValid = false;
        do {
            System.out.print("Nhập CCCD/CMND (9 hoặc 12 số): ");
            id = scanner.nextLine();
            if (id.length() == 9 || id.length() == 12) {
                isValid = true;
                for (int i = 0; i < id.length(); i++) {
                    if (!Character.isDigit(id.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (!isValid) {
                System.out.println("CCCD/CMND không hợp lệ. Vui lòng nhập lại.");
            }
        } while (!isValid);
        return id;
    }

    //getValidFullName
    public static String getValidFullName() {
        Scanner scanner = new Scanner(System.in);
        String fullName;
        do {
            System.out.print("Nhập họ và tên: ");
            fullName = scanner.nextLine();
            if (!fullName.matches("^[\\p{L}\\s]+(?:\\s[\\p{L}\\s]+)+$")) {
                System.err.println("Tên không hợp lệ. Vui lòng nhập lại chính xác.");
                continue;
            }
            return fullName;

        } while (true);
    }

    public static String getValidBirthDate() {
        Scanner scanner = new Scanner(System.in);
        String birthDate;
        do {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            birthDate = scanner.nextLine();
            if (!birthDate.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                System.err.println("Ngày sinh không đúng định dạng. Vui lòng nhập lại.");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dateOfBirth;
                try {
                    dateOfBirth = sdf.parse(birthDate);
                    Calendar dob = Calendar.getInstance();
                    dob.setTime(dateOfBirth);
                    Calendar today = Calendar.getInstance();
                    if (dob.after(today)) {
                        System.err.println("Người đến từ tương lai à ??. Vui lòng nhập lại.");
                    } else {
                        today.add(Calendar.YEAR, -18);
                        if (dob.after(today)) {
                            System.err.println("Á à bắt được ông cháu chưa đủ tuổi nhé!");
                        }
                        return birthDate;
                    }
                } catch (ParseException e) {
                    System.err.println("Ngày sinh không hợp lệ. Vui lòng nhập lại.");
                }
            }
        } while (true);
    }



}
