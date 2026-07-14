package com.example.comp2000_referral_work_10909750;

import android.widget.Adapter;

public class availability_item {

    private int availability_id;
    private int trainer_id;
    private String date_time;
    private  boolean status;

    public availability_item(int availability_id, int trainer_id, String date_time, boolean status) {
        this.availability_id = availability_id;
        this.trainer_id = trainer_id;
        this.date_time = date_time;
        this.status = status;
    }

    public int getAvailability_id() {
        return availability_id;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public String getDate_time() {
        return date_time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return date_time;
    }

}
