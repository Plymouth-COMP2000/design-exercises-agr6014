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
import  android.widget.EditText;


public class fragment_trainer_modify_details extends Fragment {
    private database db_helper;
    public fragment_trainer_modify_details() {
        super(R.layout.fragment_trainer_modify_details);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText trainer_firstname_change_type = view.findViewById(R.id.trainer_firstname_change_type);
        EditText trainer_lastname_change_type = view.findViewById(R.id.trainer_lastname_change_type);
        EditText trainer_email_change_type = view.findViewById(R.id.trainer_email_change_type);
        EditText trainer_phone_change_type = view.findViewById(R.id.trainer_phone_change_type);

        Button trainer_submit_changes_button = view.findViewById(R.id.trainer_submit_changes_button);
        Button trainer_back_home = view.findViewById(R.id.trainer_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        db_helper = new database(requireContext());

        trainer_submit_changes_button.setOnClickListener(v -> {

            String firstname = trainer_firstname_change_type.getText().toString();
            String lastname = trainer_lastname_change_type.getText().toString();
            String email = trainer_email_change_type.getText().toString();
            String phone = trainer_phone_change_type.getText().toString();
            // These will allow me to convert the text into a string
            // That will allow me to put it into the update method

            int trainer_id = 1;
            // Placeholde account

            db_helper.account_update(trainer_id, firstname, lastname, email, phone);



            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_home()
            );

        });


        trainer_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_home()
            );

        });

        // The on click listeners will load the fragments wanted (basically changing the UI)


    }

    @Override
    public void onDestroyView() {
        if (db_helper != null) {
            db_helper.close();
        }
        super.onDestroyView();
    }
}
