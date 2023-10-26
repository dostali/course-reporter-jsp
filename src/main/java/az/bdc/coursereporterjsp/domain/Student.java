package az.bdc.coursereporterjsp.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Student {
    private long id;
    private String name;
    private String surname;
    private String fullName;
    private Date birthDate;
    private String phoneNumber;
    private String pinCode;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Student(long id,
                   String name,
                   String surname,
                   String fullName,
                   Date birthDate,
                   String phoneNumber,
                   String pinCode,
                   LocalDateTime createDate,
                   LocalDateTime updateDate
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Student(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName() {
        this.fullName = this.name + " " + this.surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pinCode=" + pinCode +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

}
