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
import  android.widget.TextView;


public class fragment_trainer_account_details extends Fragment {
    public fragment_trainer_account_details() {
        super(R.layout.fragment_trainer_account_details);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        TextView trainer_firstname_text = view.findViewById(R.id.trainer_firstname_text);
        TextView trainer_lastname_text = view.findViewById(R.id.trainer_lastname_text);
        TextView trainer_email_text = view.findViewById(R.id.trainer_email_text);
        TextView trainer_phone_text = view.findViewById(R.id.trainer_phone_text);

        Button trainer_modify_details_button = view.findViewById(R.id.trainer_modify_details_button);
        Button trainer_back_home = view.findViewById(R.id.trainer_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to


        trainer_modify_details_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_modify_details()
            );

        });



        trainer_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_home()
            );

        });

        // The on click listeners will load the fragments wanted (basically changing the UI)


    }
}