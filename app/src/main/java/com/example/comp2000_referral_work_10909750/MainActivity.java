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
