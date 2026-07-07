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

public class fragment_member_signup_login extends Fragment {
    public fragment_member_signup_login() {
        super(R.layout.fragment_member_signup_login);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Button member_signup_button = view.findViewById(R.id.member_signup_button);
        Button member_login_button = view.findViewById(R.id.member_login_button);
        Button back_button = view.findViewById(R.id.back_button);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        member_signup_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_signup()
            );

        });

        member_login_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_login()
            );

        });

        back_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_start()
            );

        });
        // The on click listeners will load the fragments wanted (basically changing the UI)

    }
}