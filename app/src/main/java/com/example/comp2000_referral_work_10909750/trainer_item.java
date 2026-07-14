package com.example.comp2000_referral_work_10909750;

public class trainer_item {
    private int trainer_id;
    // Allows for database methods
    private String trainer_name;
    private String trainer_availability;
    private String trainer_phone;

    public trainer_item(int trainer_id, String trainer_name, String trainer_availability, String trainer_phone) {
        this.trainer_id = trainer_id;
        this.trainer_name = trainer_name;
        this.trainer_availability = trainer_availability;
        this.trainer_phone = trainer_phone;
    }
    // This is the basic OOP approach

    public int getTrainer_id() {
        return trainer_id;
    }

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

    @Override
    public String toString() {
        return trainer_name;
    }
    // Needed because it was showing the name of the project in the trainer choice spinner
}
