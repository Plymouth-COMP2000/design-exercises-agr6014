package com.example.comp2000_referral_work_10909750;

public class booking_item {
    private int booking_id;
    private String member_on_booking;
    private String trainer_on_booking;
    private String date_time_on_booking;

    public booking_item(int booking_id, String member_on_booking, String trainer_on_booking, String date_time_on_booking) {
        this.booking_id = booking_id;
        // Added this for the database integration
        this.member_on_booking = member_on_booking;
        this.trainer_on_booking = trainer_on_booking;
        this.date_time_on_booking = date_time_on_booking;
    }
    // This is the basic OOP approach

    public int getBooking_id() {
        return booking_id;
    }

    public String getMember_on_booking() {
        return member_on_booking;
    }

    public String getTrainer_on_booking() {
        return trainer_on_booking;
    }

    public String getDate_time_on_booking() {
        return date_time_on_booking;
    }
    // These will get the vslues for each String


    public void setMember_on_booking(String member_on_booking) {
        this.member_on_booking = member_on_booking;
    }

    public void setTrainer_on_booking(String trainer_on_booking) {
        this.trainer_on_booking = trainer_on_booking;
    }

    public void setDate_time_on_booking(String date_time_on_booking) {
        this.date_time_on_booking = date_time_on_booking;
    }
}
