package com.example.comp2000_referral_work_10909750.api;

public class user {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private String role;
    // These are the key information of each user
    // I am unsure if i need to match to be the same as on the api


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    // This concludes all of the getters

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    // This concludes all of the setters
}
