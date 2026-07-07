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

public class fragment_start extends Fragment {
    public fragment_start() {
        super(R.layout.fragment_start);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Button member_button = view.findViewById(R.id.member_button);
        Button trainer_button = view.findViewById(R.id.trainer_button);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        member_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_signup_login()
            );

        });

        trainer_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_login()
            );

        });
        // The on click listeners will load the fragments wanted (basically changing the UI)

    }
}