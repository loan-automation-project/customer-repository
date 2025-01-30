package com.project.customer.dto;

public class User {
    private int UserId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String age ;
    private String gender;

    public User() {
    }

    public User(String fullName, String username, int userId, String email, String phone, String age, String gender) {
        this.fullName = fullName;
        this.username = username;
        UserId = userId;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
