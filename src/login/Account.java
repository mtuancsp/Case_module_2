package login;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    private String account;
    private String username;
    private String password;
    private login.AccessLevel accessLevel;
    private Date date;


    public Account(String account, String password, AccessLevel accessLevel) {
        this.account = account;
        this.password = password;
        this.accessLevel = accessLevel;
        this.date = new java.util.Date();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return
                "Account = '" + account + '\'' +
                ", Username = '" + username + '\'' +
                ", Password = '" + "*******" + '\'' +
                ", Access level = '" + accessLevel + '\'' +
                ", Join Date = " + date;
    }

    public static void main(String[] args) {
        System.out.println(new Account("tuan267", "password", AccessLevel.ADMIN));
    }
}
