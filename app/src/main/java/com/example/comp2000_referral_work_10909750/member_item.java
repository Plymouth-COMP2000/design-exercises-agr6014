package com.example.comp2000_referral_work_10909750;

public class member_item {
    private String member_name;
    private String member_email;
    private String member_phone;

    public member_item(String member_name, String member_email, String member_phone) {
        this.member_name = member_name;
        this.member_email = member_email;
        this.member_phone = member_phone;
    }
    // This is the basic OOP approach

    public String getMember_name() {
        return member_name;
    }

    public String getMember_email() {
        return member_email;
    }

    public String getMember_phone() {
        return member_phone;
    }
    // These will get the vslues for each String

}
