package view;

import model.AccessLevel;
import model.Color;

import java.io.IOException;

import static controller.AccountManager.displayAccountsListByAccessLevel;
import static controller.AccountManager.updateListFromFile;
import static view.Menu.getValidIntChoice;
import static view.Menu.returnOrExit;



public class Web {

    public static void web() throws IOException, ClassNotFoundException {

        do {
            System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            System.out.println("WELCOME");
            System.out.println("1. Giới thiệu");
            System.out.println("2. Thành viên");
            System.out.println("3. Diễn đàn");
            System.out.println("4. Hoạt động");
            System.out.println("5. Liên hệ hỗ trợ");
            System.out.println("0. Quay lại");

            int choice = getValidIntChoice(0, 5);
            switch (choice) {
                case 1 -> intro();
                case 2 -> displayAccountsListByAccessLevel();
                case 3 -> forum();
                case 4 -> activity();
                case 5 -> help();
                case 0 -> {
                    return;
                }
            }
        } while (true);

    }

    public static void intro() {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Welcome to the Web!\n");

        System.out.println(Color.RED);
        int size = 4  ;
        for (int m = 0; m < size; m++) {
            for (int n = 0; n <= 4 * size; n++) {
                double pos1 = Math.sqrt(Math.pow(m - size, 2) + Math.pow(n - size, 2));
                double pos2 = Math.sqrt(Math.pow(m - size, 2) + Math.pow(n - 3 * size, 2));

                if (pos1 < size + 0.5 || pos2 < size + 0.5) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.print(System.lineSeparator());
        }

        for (int m = 1; m <= 2 * size; m++)
        {
            for (int n = 0; n < m; n++) {
                System.out.print(' ');
            }
            for (int n = 0; n < 4 * size + 1 - 2 * m; n++) {
                System.out.print("*");
            }
            System.out.print(System.lineSeparator());
        }
        System.out.println(Color.RESET_COLOR);

        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        returnOrExit();
    }

    public static void forum() {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Welcome to the Forum!\n");
        System.out.println("Hãy cùng nhau tham gia viết bài và thảo luận");
        System.out.println(Color.PINK);
        System.out.println(" /\\_/\\");
        System.out.println("( o.o )");
        System.out.println(" > ^ <");
        System.out.println(Color.RESET_COLOR);
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        returnOrExit();
    }

    public static void activity() {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Hiện chưa có hoạt động gì cả");
        System.out.println("Nếu rảnh bạn có thể đếm xem cây thông này có bao nhiêu ^.^");
        System.out.println(Color.GREEN);
        System.out.println("    ^    ");
        System.out.println("   ^^^   ");
        System.out.println("  ^^^^^  ");
        System.out.println(" ^^^^^^^ ");
        System.out.println("^^^^^^^^^");
        System.out.println("   | |   ");
        System.out.println(Color.RESET_COLOR);
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        returnOrExit();
    }

    public static void help() throws IOException, ClassNotFoundException {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Liên hệ Moderator hoặc Admin để được hỗ trợ");
        updateListFromFile().stream().filter(x -> x.getAccessLevel() == AccessLevel.ADMIN || x.getAccessLevel() == AccessLevel.MODERATOR).forEach(e -> System.out.println(e.getAccessLevel() + " " + e.getAccount() + " " + e.getUsername()));
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        returnOrExit();
    }

}
