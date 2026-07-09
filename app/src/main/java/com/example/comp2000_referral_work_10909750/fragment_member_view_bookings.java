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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Spinner;

import java.time.MonthDay;
import java.util.ArrayList;

public class fragment_member_view_bookings extends Fragment {
    public fragment_member_view_bookings() {
        super(R.layout.fragment_member_view_bookings);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.member_view_bookings_recycler_view);

        Spinner member_cancel_choice_spinner = view.findViewById(R.id.member_cancel_choice_spinner);

        Button member_cancel_booking_button = view.findViewById(R.id.member_cancel_booking_button);
        Button member_back_home = view.findViewById(R.id.member_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        Toolbar toolbar = view.findViewById(R.id.member_tool_bar);
        // Hopefully the toolbar will now work!!!


        ArrayList<booking_item> bookings = new ArrayList<>();

        bookings.add(new booking_item(
                "Shawn",
                "Jack",
                "Monday     12:00 - 13:00"
        ));
        bookings.add(new booking_item(
                "Bob",
                "Jack",
                "Monday     15:00 - 16:00"
        ));
        booking_adapter adapter = new booking_adapter(bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        String [] booking_choice = {
                "Monday     12:00 - 13:00",
                "Monday     15:00 - 16:00"
        };
        ArrayAdapter<String> booking_choice_adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                booking_choice
        );
        member_cancel_choice_spinner.setAdapter(booking_choice_adapter);

        member_cancel_booking_button.setOnClickListener(v -> {
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