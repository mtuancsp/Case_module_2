package login;

import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    guess();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }

    public static void displayMenu() {
        System.out.println("""
               Welcome to the login page
               1. Sign In
               2. Sign Up
               3. Guess
               4. Exit
                """);
    }

    public static void signIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Đăng nhập");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

    }

    public static void signUp() {
        System.out.println("Sign Up functionality goes here.");
    }

    public static void guess() {
        System.out.println("Guess functionality goes here.");
    }

    public static void exit() {
        System.out.println("Exiting...");
    }
}
