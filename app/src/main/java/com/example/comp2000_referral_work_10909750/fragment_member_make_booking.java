package com.example.comp2000_referral_work_10909750;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.annotation.NonNull;
import com.example.comp2000_referral_work_10909750.R;
// This will let me use "R"
import com.example.comp2000_referral_work_10909750.MainActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
// These will allow for the overflow menu
import android.widget.Spinner;

public class fragment_member_make_booking extends Fragment {
    public fragment_member_make_booking() {
        super(R.layout.fragment_member_make_booking);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Spinner trainer_choice_spinner = view.findViewById(R.id.trainer_choice_spinner);
        Spinner time_slot_choice_spinner = view.findViewById(R.id.time_slot_choice_spinner);
        // This will find the ids of the spinners
        // I need to make the adapters and fill in the details
        // It will me changed when the databases are made

        Button member_submit_booking_button = view.findViewById(R.id.member_submit_booking_button);
        Button member_back_home = view.findViewById(R.id.member_back_home);

        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        Toolbar toolbar = view.findViewById(R.id.member_tool_bar);
        // Hopefully the toolbar will now work!!!


        String [] trainers = {
                "Alex",
                "Jake"
        };
        // Filling in details for now
        ArrayAdapter<String> trainer_spinner_adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                trainers
        );
        trainer_choice_spinner.setAdapter(trainer_spinner_adapter);


        String [] timeslots = {
                "Monday     10:00 - 17:00",
                "Tuesday    13:00 - 18:00"
        };
        ArrayAdapter<String> timeslot_spinner_adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                timeslots
        );
        time_slot_choice_spinner.setAdapter(timeslot_spinner_adapter);


        member_submit_booking_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
            );

        });

        member_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
            );

        });



        // The on click listeners will load the fragments wanted (basically changing the UI)

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.view_account_details) {
                ((MainActivity) requireActivity()).openFragment(new fragment_member_account_details());
                return true;
            }
            return false;
        });
        // This in theory will allow for the transition to the account details

    }
}