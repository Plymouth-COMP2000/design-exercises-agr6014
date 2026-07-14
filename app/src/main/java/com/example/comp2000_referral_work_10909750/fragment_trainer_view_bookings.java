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

import java.util.ArrayList;

public class fragment_trainer_view_bookings extends Fragment {

    private database db_helper;

    public fragment_trainer_view_bookings() {
        super(R.layout.fragment_trainer_view_bookings);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.trainer_view_bookings_recycler_view);

        Spinner trainer_booking_choice_spinner = view.findViewById(R.id.trainer_booking_choice_spinner);

        Button trainer_cancel_booking_button = view.findViewById(R.id.trainer_cancel_booking_button);
        Button trainer_back_home = view.findViewById(R.id.trainer_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        Toolbar toolbar = view.findViewById(R.id.trainer_tool_bar);
        // Hopefully the toolbar will now work!!!


        db_helper = new database(requireContext());
        int trainer_id = 1;
        // This will act as a placeholder until the authentication is done

        ArrayList<booking_item> bookings = db_helper.get_trainer_bookings(trainer_id);



       // bookings.add(new booking_item(
               // "Shawn",
               // null,
               // "Monday     12:00 - 13:00"
       // ));
       // bookings.add(new booking_item(
               // "Bob",
               // null,
               // "Monday     15:00 - 16:00"
       // ));
        booking_adapter adapter = new booking_adapter(bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

       // String [] booking_choice = {
               // "Monday     12:00 - 13:00",
               // "Monday     15:00 - 16:00"
       // };

        ArrayList<String> booking_choice = new ArrayList<>();

        for (booking_item booking : bookings) {
            // This will cycle through the finding all of the bookings

            booking_choice.add(booking.getDate_time_on_booking());

        }

        ArrayAdapter<String> booking_choice_adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                booking_choice
        );
        trainer_booking_choice_spinner.setAdapter(booking_choice_adapter);

        trainer_cancel_booking_button.setOnClickListener(v -> {
            int pos = trainer_booking_choice_spinner.getSelectedItemPosition();

            booking_item booking = bookings.get(pos);
            db_helper.delete_booking(booking.getBooking_id());

        });

        trainer_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_home()
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

    @Override
    public void onDestroyView() {
        if (db_helper != null) {
            db_helper.close();
        }
        super.onDestroyView();
    }

}