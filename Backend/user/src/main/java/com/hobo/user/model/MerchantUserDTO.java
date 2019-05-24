package com.hobo.user.model;


import java.util.Date;

public class MerchantUserDTO {

    private String emailId;
    private String name;
    private String password;
    private Long phoneNumber;
    private String gender;
    private Date dateOfBirth;
    private String address;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MerchantUserDTO{" +
                "emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}