package com.example.comp2000_referral_work_10909750;

public class trainer_item {
    private String trainer_name;
    private String trainer_availability;
    private String trainer_phone;

    public trainer_item(String trainer_name, String trainer_availability, String trainer_phone) {
        this.trainer_name = trainer_name;
        this.trainer_availability = trainer_availability;
        this.trainer_phone = trainer_phone;
    }
    // This is the basic OOP approach

    public String getTrainer_name() {
        return trainer_name;
    }

    public String getTrainer_availability() {
        return trainer_availability;
    }

    public String getTrainer_phone() {
        return trainer_phone;
    }
    // These will get the vslues for each String

}
