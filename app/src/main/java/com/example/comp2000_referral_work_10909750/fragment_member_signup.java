package com.example.comp2000_referral_work_10909750;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.example.comp2000_referral_work_10909750.R;
// This will let me use "R"
import com.example.comp2000_referral_work_10909750.MainActivity;

public class fragment_member_signup extends Fragment {
    public fragment_member_signup() {
        super(R.layout.fragment_member_signup);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText member_firstname_type = view.findViewById(R.id.member_firstname_type);
        EditText member_lastname_type = view.findViewById(R.id.member_lastname_type);
        EditText member_email_type = view.findViewById(R.id.member_email_type);
        EditText member_phone_type = view.findViewById(R.id.member_phone_type);
        EditText member_password_type = view.findViewById(R.id.member_password_type);
        // These find the ids of the edit text boxes

        Button create_account_button = view.findViewById(R.id.create_account_button);
        Button member_back_choice = view.findViewById(R.id.member_back_choice);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        create_account_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
            );

        });

        member_back_choice.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_signup_login()
            );

        });
        // The on click listeners will load the fragments wanted (basically changing the UI)

    }
}