package com.example.comp2000_referral_work_10909750;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// These will help with the making and filling of the databases
// I want to make a account, availability, and booking table
import androidx.annotation.Nullable;
// This will allow some fields to be null
import android.content.Context;
import android.database.Cursor;

public class database extends SQLiteOpenHelper{
    private static String database_name = "gym.db";
    private static int database_version = 1;
    // Required for SQLiteOpenHelper


    public static String account_table = "Accounts";
    public static String account_id = "account_id";
    public static String account_firstname = "Firstname";
    public static String account_lastname = "Lastname";
    public static String account_email = "Email";
    public static String account_phone = "Phone_number";
    public static String account_password = "Password";
    public static String account_role = "Role";
    // This will be the fields for the account table
    // All details, as well as their account id and role (member ot trainer)

    private static String create_account_table = "CREATE TABLE " + account_table + " (" +
            account_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            account_firstname + " TEXT NOT NULL, " +
            account_lastname + " TEXT NOT NULL, " +
            account_email + " TEXT NOT NULL UNIQUE, " +
            account_phone + " TEXT NOT NULL, " +
            account_password + " TEXT NOT NULL, " +
            account_role + " TEXT NOT NULL CHECK( " +
            account_role + " IN ('Member', 'Trainer'))" + ")";
    // This creates the account table, this stores all of the details of each account, including their role

    public static String availability_table = "Availability";
    public static String availability_id = "availability_id";
    public static String availability_trainer_id = "trainer_id";
    public static String availability_date_time = "Date_time";
    public static String availability_status = "available";
    // This is everything in the availability table

    private static String create_availability_table = "CREATE TABLE " + availability_table + " (" +
            availability_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            availability_trainer_id + " INTEGER NOT NULL, " +
            availability_date_time + " TEXT NOT NULL, " +
            availability_status + " INTEGER NOT NULL DEFAULT 1, " +
            "FOREIGN KEY(" + availability_trainer_id + ") REFERENCES " +
            account_table + "(" + account_id +") ON DELETE CASCADE" + ")";

    public static String booking_table = "Bookings";
    public static String booking_id = "booking_id";
    public static String booking_member_id = "member_id";
    public static String booking_trainer_id = "trainer_id";
    public static String booking_date_time = "Date_time";
    public static String booking_status = "booking";

    private static String create_booking_table = "CREATE TABLE " + booking_table + " (" +
            booking_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            booking_member_id + " INTEGER NOT NULL, " +
            booking_trainer_id + " INTEGER NOT NULL, " +
            booking_date_time + " TEXT NOT NULL, " +
            booking_status + " TEXT NOT NULL DEFAULT 'Confirmed', " +
            "FOREIGN KEY(" + booking_trainer_id + ") REFERENCES " + account_table + "(" + account_id + "), " +
            "FOREIGN KEY(" + booking_member_id + ") REFERENCES " + account_table + "(" + account_id + ")" + ")";
    // This creates the booking table
    // All tables should now link with each respectively shared id

    public database(@Nullable Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onConfigure(SQLiteDatabase database) {
        super.onConfigure(database);
        database.setForeignKeyConstraintsEnabled(true);
    }
    // This allows for foreign keys

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(create_account_table);
        database.execSQL(create_availability_table);
        database.execSQL(create_booking_table);
    }
    // This creates the tables

    @Override
    public void onUpgrade(SQLiteDatabase database, int old_version, int new_version) {
        database.execSQL("DROP TABLE IF EXISTS " + booking_table);
        database.execSQL("DROP TABLE IF EXISTS " + availability_table);
        database.execSQL("DROP TABLE IF EXISTS " + account_table);

        onCreate(database);
    }
    // This gets rid of the old databases and replaces them with updated ones

    // Next i need to actually add the methods

    public long insert_account(String firstname, String lastname, String email, String phone, String password, String role) {

        SQLiteDatabase db = getWritableDatabase();
        // This opens the database and makes it so i can write data

        ContentValues values = new ContentValues();

        values.put(account_firstname, firstname);
        values.put(account_lastname, lastname);
        values.put(account_email, email);
        values.put(account_phone, phone);
        values.put(account_password, password);
        values.put(account_role, role);
        // This adds values

        return db.insert(account_table, null, values);
    }
    // Now i will do the cursor queries for the accounts table
    // What is needed?
    // Probably the get by id (since that is unique)

    public  Cursor get_account_id(int id) {
        SQLiteDatabase db = getReadableDatabase();
        // Since we are just getting details by the id, it makes sense to use readable

        return db.query(account_table, null, account_id + " = ?",
                new String[]{
                        String.valueOf(id)},
                null,
                null,
                null,
                "1"
        );
        // Accessing the right table, not looking for specific columns, where statement (" = ?" is apparently standard and acts as a placeholder)
        // Then the following one gives the value of id
        // no need to order details

    }

    public Cursor get_trainers() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(account_table, null, account_role + " = ?",
                new String[]{
                        "Trainer"
                },
                null,
                null,
                account_lastname + " ASC"
        );
        // Similar to get account id, but have ordered it to make it easier when booking/viewing trainers


    }

    public int account_update(int id, String firstname, String lastname, String email, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        // Updating, therefore i need to change details and write to the database

        ContentValues values = new ContentValues();

        values.put(account_firstname, firstname);
        values.put(account_lastname, lastname);
        values.put(account_email, email);
        values.put(account_phone, phone);
        // All changeable details

        return db.update(account_table, values, account_id + " = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
        // This returns the SQL querry to update the table

    }
    // This should conclude the account methods/functions


}
