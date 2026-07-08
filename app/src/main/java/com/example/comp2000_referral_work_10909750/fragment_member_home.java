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

public class fragment_member_home extends Fragment {
    public fragment_member_home() {
        super(R.layout.fragment_member_home);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


        Button member_view_trainers_button = view.findViewById(R.id.member_view_trainers_button);
        Button member_make_booking_button = view.findViewById(R.id.member_make_booking_button);
        Button member_view_bookings_button = view.findViewById(R.id.member_view_bookings_button);
        Button member_back_choice = view.findViewById(R.id.member_back_choice);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        Toolbar toolbar = view.findViewById(R.id.member_tool_bar);
        // Hopefully the toolbar will now work!!!

        member_view_trainers_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_view_trainers()
            );

        });

        member_make_booking_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_make_booking()
            );

        });

        member_view_bookings_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_view_bookings()
            );

        });

        member_back_choice.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_login()
            );

        });

        // The on click listeners will load the fragments wanted (basically changing the UI)

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.member_view_account_details) {
                ((MainActivity) requireActivity()).openFragment(new fragment_member_account_details());
                return true;
            }
            return false;
        });
        // This in theory will allow for the transition to the account details

    }
}