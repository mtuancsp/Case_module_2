package model;

import java.io.Serializable;

public class UserInformation implements Serializable {
    private String id;
    private String fullName;
    private String birthDate;
    private String description;

    public UserInformation() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CCCD/CMND: " + id +
                ", Họ và tên: '" + fullName + '\'' +
                ", Ngày sinh: '" + birthDate + '\'' +
                "\nMô tả: '" + description + '\'';
    }
}

