package com.example.comp2000_referral_work_10909750;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import androidx.annotation.NonNull;
import com.example.comp2000_referral_work_10909750.R;
// This will let me use "R"
import com.example.comp2000_referral_work_10909750.MainActivity;
import  android.widget.EditText;


public class fragment_member_modify_details extends Fragment {
    public fragment_member_modify_details() {
        super(R.layout.fragment_member_modify_details);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText member_firstname_change_type = view.findViewById(R.id.member_firstname_change_type);
        EditText member_lastname_change_type = view.findViewById(R.id.member_lastname_change_type);
        EditText member_email_change_type = view.findViewById(R.id.member_email_change_type);
        EditText member_phone_change_type = view.findViewById(R.id.member_phone_change_type);

        Button member_submit_changes_button = view.findViewById(R.id.member_submit_changes_button);
        Button member_back_home = view.findViewById(R.id.member_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to


        member_submit_changes_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_home()
            );

        });


        member_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
            );

        });

        // The on click listeners will load the fragments wanted (basically changing the UI)


    }
}
