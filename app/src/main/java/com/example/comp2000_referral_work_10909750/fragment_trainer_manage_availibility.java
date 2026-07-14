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
import android.widget.CheckBox;
import android.widget.AdapterView;
import java.util.ArrayList;

public class fragment_trainer_manage_availibility extends Fragment {
    private ArrayList<availability_item> availability_items;
    private ArrayAdapter<availability_item> availability_adapter;
    private database db_helper;

    public fragment_trainer_manage_availibility() {
        super(R.layout.fragment_trainer_manage_availibility);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


        Spinner trainer_time_availibility_spinner = view.findViewById(R.id.trainer_time_availibility_spinner);

        CheckBox trainer_status_checkbox = view.findViewById(R.id.trainer_status_checkbox);

        Button trainer_submit_status_button = view.findViewById(R.id.trainer_submit_status_button);
        Button trainer_back_home = view.findViewById(R.id.trainer_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        Toolbar toolbar = view.findViewById(R.id.trainer_tool_bar);
        // Hopefully the toolbar will now work!!!

       // String [] availiability = {
               // "Monday   10:00 - 11:00",
               // "Monday   11:00 - 12:00",
               // "Monday   12:00 - 13:00",
               // "Monday   14:00 - 15:00",
               // "Monday   15:00 - 16:00",
               // "Monday   16:00 - 17:00",
               // "Tuesday  10:00 - 11:00",
                //"Tuesday  11:00 - 12:00",

       // };
        // Above is the old dummy data

         db_helper = new database(requireContext());
         int trainer_id = 1;
         // Until authentication has been added, i will just get one trainer's availability

        availability_items = db_helper.get_availability(trainer_id);
        availability_adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                availability_items);

        availability_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


       // ArrayAdapter<String> availability_spinner_adapter = new ArrayAdapter<>(
               // requireContext(),
               // android.R.layout.simple_spinner_dropdown_item,
               // availiability
        // );
        trainer_time_availibility_spinner.setAdapter(availability_adapter);

        trainer_time_availibility_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View selected_view, int pos, long id) {
                availability_item selected_item = availability_items.get(pos);
                trainer_status_checkbox.setChecked(selected_item.isStatus());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                trainer_status_checkbox.setChecked(false);
            }

        });

        // Under here i want to put the checkbox


        trainer_submit_status_button.setOnClickListener(v -> {
           availability_item selected_item = (availability_item) trainer_time_availibility_spinner.getSelectedItem();

            boolean new_status = trainer_status_checkbox.isChecked();

            int rows_updated = db_helper.update_availability(selected_item.getAvailability_id(), new_status);

            if (rows_updated > 0) {
                selected_item.setStatus(new_status);
                availability_adapter.notifyDataSetChanged();
            }

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