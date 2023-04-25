package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 10001L;
    private final String account;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private AccessLevel accessLevel = AccessLevel.USER;
    private LocalDate joinDate;
    private LocalDate banDate;
    private final UserInformation userInformation = new UserInformation();

    public Account(String account, String password, String phoneNumber, String email) {
        this.account = account;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.joinDate = LocalDate.now();
    }

    public Account(String account, String password, String number, String email, AccessLevel accessLevel) {
        this.account = account;
        this.password = password;
        this.phoneNumber = number;
        this.email = email;
        this.accessLevel = accessLevel;
        this.joinDate = LocalDate.now();
    }

    public String getAccount() {
        return account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getBanDate() {
        return banDate;
    }

    public void setBanDate(LocalDate banDate) {
        this.banDate = banDate;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-30s %-15s %-15s",
                account, username, password, accessLevel, phoneNumber, email,
                joinDate, banDate);
    }
}
