package controller;

import model.Account;
import model.UserInformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static controller.AccountManager.updateListFromFile;
import static controller.AccountManager.writeAccountsListToFile;
import static get_input.Input.*;
import static read_write.ReadWrite.write;

public class UserInfoManager {
    private static final List<UserInformation> usersInfo = new ArrayList<>();

    private UserInfoManager() {
    }

    public static void main(String[] args) {
        try {
            System.out.println( getUsersInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<UserInformation> getUsersInfo() throws IOException, ClassNotFoundException {
        var accountsList = updateListFromFile();
        accountsList.forEach(user -> usersInfo.add(user.getUserInformation()));
        writeUsersInfoToFile();
        return usersInfo;
    }

    public static void writeUsersInfoToFile() throws IOException {
        write("src/file/usersInfo.txt", usersInfo);
    }

    public static void changeId(Account acc) throws IOException, ClassNotFoundException {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cập nhật CCCD/CMND");
        checkPassword(acc);
        System.out.println("CCCD/CMND: " + acc.getUserInformation().getId());
        System.out.println("Cập nhập số CCCD/CMND mới");
        String newId = getValidId();
        acc.getUserInformation().setId(newId);
        writeAccountsListToFile();
        System.out.println("Cập nhật thành công");
    }

    public static void changeName(Account acc) throws IOException {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cập nhật họ và tên");
        String newName = getValidFullName();
        acc.getUserInformation().setFullName(newName);
        writeAccountsListToFile();
        System.out.println("Cập nhật họ và tên thành công");
    }

    public static void changeBirthDate(Account acc) throws IOException {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cập nhật ngày sinh");
        String newBirthDate = getValidBirthDate();
        acc.getUserInformation().setBirthDate(newBirthDate);
        writeAccountsListToFile();
        System.out.println("Cập nhật ngày sinh thành công");
    }

    public static void changeDescription(Account acc) throws IOException {
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Cập nhật mô tả");
        Scanner sc = new Scanner(System.in);
        String newDescription = sc.nextLine();
        acc.getUserInformation().setDescription(newDescription);
        writeAccountsListToFile();
        System.out.println("Cập nhật mô tả thành công");
    }

}
