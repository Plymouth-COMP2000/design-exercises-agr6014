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
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
// These will allow for the overflow menu

public class fragment_trainer_home extends Fragment {
    public fragment_trainer_home() {
        super(R.layout.fragment_trainer_home);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


        Button trainer_manage_availiability_button = view.findViewById(R.id.trainer_manage_availiability_button);
        Button trainer_view_bookings_button = view.findViewById(R.id.trainer_view_bookings_button);
        Button trainer_view_member_details_button = view.findViewById(R.id.trainer_view_member_details_button);
        Button trainer_back_login = view.findViewById(R.id.trainer_back_login);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        Toolbar toolbar = view.findViewById(R.id.trainer_tool_bar);
        // Hopefully the toolbar will now work!!!

        trainer_manage_availiability_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_manage_availibility()
            );

        });

        trainer_view_bookings_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_view_bookings()
            );

        });

        trainer_view_member_details_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_view_members()
            );

        });

        trainer_back_login.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_login()
            );

        });

        // The on click listeners will load the fragments wanted (basically changing the UI)

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.view_account_details) {
                ((MainActivity) requireActivity()).openFragment(new fragment_trainer_account_details());
                return true;
            }
            return false;
        });
        // This in theory will allow for the transition to the account details

    }
}