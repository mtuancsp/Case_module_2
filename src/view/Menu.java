package view;

import java.util.Scanner;

public class Menu {

    public static void returnOrExit() {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("Nhập '0' để quay lại hoặc 'Exit' để thoát hoàn toàn chương trình: ");
            input = scanner.nextLine();

            switch (input) {
                case "0" -> {
                    return;
                }
                case "Exit" -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!");
            }
        } while (true);
    }

    public static int getValidIntChoice(int minChoice, int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.printf("Nhập lựa chọn từ %d đến %d: ", minChoice, maxChoice);

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            if (choice < minChoice || choice > maxChoice) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                continue;
            }

            break;
        } while (true);
        scanner.nextLine();

        return choice;
    }


}
