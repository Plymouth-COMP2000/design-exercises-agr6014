package com.example.comp2000_referral_work_10909750;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        // This sets the main layout as main_activity.xml
        // This is good due to it being the fragment container

        database db_helper = new database(this);

        long trainer_id = db_helper.insert_account("Bob",
                "Harry",
                "bob@gmail.com",
                "07745387290",
                "password",
                "Trainer");

        long member_id = db_helper.insert_account("Harry",
                "Hock",
                "harry@gmail.com",
                "0778998090",
                "password",
                "Member");

        db_helper.insert_availability(1, "Monday    11:00 - 12:00", true);

        db_helper.insert_booking(2, 1, "Monday      14:00 - 15:00");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new fragment_start()).commit();
        }
        // First checks if the activity has been created before
        // Then if not it created loads in fragment_start
    }

    public void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment)
                .addToBackStack(null).commit();
    }
    // This allows transistion between the fragments

}
