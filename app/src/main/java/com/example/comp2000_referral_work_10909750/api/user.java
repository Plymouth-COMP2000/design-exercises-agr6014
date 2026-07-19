package com.example.comp2000_referral_work_10909750.api;

import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("_id")
    // This will make it so i can find the _id using the variable "id"
    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String contact;
    private String password;
    @SerializedName("usertype")
    // This will make it so i can find the usertype using the variable "userType"

    private String userType;
    // These are the key information of each user
    // I am unsure if i need to match to be the same as on the api
    /// I have updated these so that it better matches the the rest API


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }
    // This concludes all of the getters

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    // This concludes all of the setters
}
