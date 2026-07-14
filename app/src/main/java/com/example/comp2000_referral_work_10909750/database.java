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

import java.util.ArrayList;
import java.util.concurrent.Callable;

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

    private static String create_booking_table = "CREATE TABLE " + booking_table + " (" +
            booking_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            booking_member_id + " INTEGER NOT NULL, " +
            booking_trainer_id + " INTEGER NOT NULL, " +
            booking_date_time + " TEXT NOT NULL, " +
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

    public Cursor get_members() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(account_table, null, account_role + " = ?",
                new String[]{
                        "Member"
                },
                null,
                null,
                account_lastname + " ASC"
        );
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

    public long insert_booking(int member_id, int trainer_id, String date_time) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(booking_member_id, member_id);
        values.put(booking_trainer_id, trainer_id);
        values.put(booking_date_time, date_time);
        // All the values needed for a booking
        // Who booked, who they booked, when

        return  db.insert(booking_table, null, values);
    }
    // This will insert a booking to the table

    // Next i need to make get all the bookings for the member and the ones for the trainer

    public ArrayList<booking_item> get_member_bookings(int id) {
        ArrayList<booking_item> bookings = new ArrayList<>();
        // I want to store the information in an array, to put into the recycler view

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(booking_table, null, booking_member_id + " = ?",
                new String[]{
                        String.valueOf(id)
                },
                null,
                null,
                booking_date_time + " ASC"
                // I SPENT AGES FINDING THE ISSUE AND IT WAS BECAUSE I FORGOT TO ADD A SPACE :(
                );

        while (cursor.moveToNext()) {
            int id_for_booking = cursor.getInt(cursor.getColumnIndexOrThrow(booking_id));
            // Added this to help with cancelling
            String member_id = cursor.getString(cursor.getColumnIndexOrThrow(booking_member_id));
            String trainer_id = cursor.getString(cursor.getColumnIndexOrThrow(booking_trainer_id));
            String date_time = cursor.getString(cursor.getColumnIndexOrThrow(booking_date_time));
            // This will allow me to cycle through the table

            bookings.add(new booking_item(id_for_booking, member_id, trainer_id, date_time));




        }

        cursor.close();

        return bookings;



    }

    public ArrayList<booking_item> get_trainer_bookings(int id) {
        ArrayList<booking_item> bookings = new ArrayList<>();
        // I want to store the information in an array, to put into the recycler view

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(booking_table, null, booking_trainer_id + " = ?",
                new String[]{
                        String.valueOf(id)
                },
                null,
                null,
                booking_date_time + " ASC"
        );

        while (cursor.moveToNext()) {
            int id_for_booking = cursor.getInt(cursor.getColumnIndexOrThrow(booking_id));
            String member_id = cursor.getString(cursor.getColumnIndexOrThrow(booking_member_id));
            String trainer_id = cursor.getString(cursor.getColumnIndexOrThrow(booking_trainer_id));
            String date_time = cursor.getString(cursor.getColumnIndexOrThrow(booking_date_time));
            // This will allow me to cycle through the table

            bookings.add(new booking_item(id_for_booking, member_id, trainer_id, date_time));




        }

        cursor.close();

        return bookings;



    }

    public int delete_booking(int booking_choice){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(booking_table, booking_id + " = ?",
                new String[]{
                        String.valueOf(booking_choice)
                }
        );
    }
    // This will dete the chosen booking
    // This should do it in terms of booking methods


    public long insert_availability(int id, String date_time, boolean status) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(availability_trainer_id, id);
        values.put(availability_date_time, date_time);
        values.put(availability_status, status);

        return db.insert(availability_table, null, values);

    }
    // This will add availiability into the table

    public ArrayList<availability_item> get_availability(int id) {
        ArrayList<availability_item> availability_items = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(availability_table, null, availability_trainer_id + " = ?",
                new String[]{
                        String.valueOf(id)
                },
                null,
                null,
                availability_date_time + " ASC"
                );

        while (cursor.moveToNext()) {
            int position = cursor.getInt(cursor.getColumnIndexOrThrow(availability_id));
            int trainer_id = cursor.getInt(cursor.getColumnIndexOrThrow(availability_trainer_id));
            String date_time = cursor.getString(cursor.getColumnIndexOrThrow(availability_date_time));
            boolean status = cursor.getInt(cursor.getColumnIndexOrThrow(availability_status)) == 1;
            // getInt has been used here due to the boolean values (1 or 0)
            // True or false are set as 1 or 0

            availability_items.add(new availability_item(position, trainer_id, date_time, status));

        }

        cursor.close();

        return availability_items;

    }

    public int update_availability(int id, boolean status) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(availability_status, status ? 1 : 0);
        // the status of each time slot is either true (1) or false (0)

        return db.update(availability_table, values, availability_id + " = ?",
                new String[]{
                        String.valueOf(id)
                });
    }



}
