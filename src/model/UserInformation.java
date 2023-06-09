package model;

import java.io.Serial;
import java.io.Serializable;

public class UserInformation implements Serializable {
    @Serial
    private static final long serialVersionUID = 10002L;
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
        return String.format("Họ và tên: %-20s CCCD/CMND: %-15s Ngày sinh: %-15s Mô tả: %s",
                getFullName(), id, getBirthDate(), getDescription());
    }
}

