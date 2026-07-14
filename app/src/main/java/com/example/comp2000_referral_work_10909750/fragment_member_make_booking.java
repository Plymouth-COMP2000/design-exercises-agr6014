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
import android.widget.AdapterView;
import java.util.ArrayList;
import android.database.Cursor;

public class fragment_member_make_booking extends Fragment {
    private database db_helper;

    private ArrayList<availability_item> slots;
    private ArrayAdapter<availability_item> slot_adapter;

    private ArrayList<trainer_item> trainers;
    private ArrayAdapter<trainer_item> trainer_adapter;


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


       // String [] trainers = {
               // "Alex",
               // "Jake"
       // };
        // Filling in details for now

        db_helper = new database(requireContext());

        trainers = new ArrayList<>();
        Cursor cursor = db_helper.get_trainers();
        while (cursor.moveToNext()) {
            int trainer_id = cursor.getInt(cursor.getColumnIndexOrThrow(database.account_id));
            String firstname = cursor.getString(cursor.getColumnIndexOrThrow(database.account_firstname));
            String lastname = cursor.getString(cursor.getColumnIndexOrThrow(database.account_lastname));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(database.account_phone));
            trainers.add(new trainer_item(trainer_id, firstname + " " + lastname, "Available", phone));
        }
        cursor.close();
        trainer_adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                trainers
        );
        trainer_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trainer_choice_spinner.setAdapter(trainer_adapter);

        slots = new ArrayList<>();
        slot_adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, slots);
        slot_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_slot_choice_spinner.setAdapter(slot_adapter);

        trainer_choice_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                slots.clear();
                // Clears them ready to show up to date slots
                trainer_item trainer = trainers.get(pos);
                ArrayList<availability_item> trainer_times = db_helper.get_availability(trainer.getTrainer_id());

                for (availability_item slot : trainer_times) {
                    if (slot.isStatus()) {
                        slots.add(slot);
                        // This will add the up to date dlots
                    }
                }
                slot_adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                slots.clear();
                slot_adapter.notifyDataSetChanged();
            }
        });

       // String [] timeslots = {
               // "Monday     10:00 - 17:00",
               // "Tuesday    13:00 - 18:00"
       // };

        // slots = new ArrayList<>();
        // slot_adapter = new ArrayAdapter<>(requireContext(),
               // android.R.layout.simple_spinner_dropdown_item, slots);
        // slot_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // time_slot_choice_spinner.setAdapter(slot_adapter);


        member_submit_booking_button.setOnClickListener(v -> {
            trainer_item trainer = (trainer_item) trainer_choice_spinner.getSelectedItem();
            availability_item slot = (availability_item) time_slot_choice_spinner.getSelectedItem();

            int member_id = 2;
            db_helper.insert_booking(member_id, trainer.getTrainer_id(), slot.getDate_time());
            db_helper.update_availability(slot.getAvailability_id(), false);
            // These will add the booking to the booking table and make the slot unavailible

            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_view_bookings()
            );
            // This will now show you the booking after it has been made


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

    @Override
    public void onDestroyView() {
        if (db_helper != null) {
            db_helper.close();
        }
        super.onDestroyView();
    }


}