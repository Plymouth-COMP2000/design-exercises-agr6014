package com.example.comp2000_referral_work_10909750;

import android.database.Cursor;
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
import android.widget.TextView;
import com.example.comp2000_referral_work_10909750.api.api;
import com.example.comp2000_referral_work_10909750.api.user;
import com.example.comp2000_referral_work_10909750.api.authentication;

public class fragment_trainer_account_details extends Fragment {
    public fragment_trainer_account_details() {
        super(R.layout.fragment_trainer_account_details);
    }
    // Makes this the used fragment

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        TextView trainer_firstname_text = view.findViewById(R.id.trainer_firstname_text);
        TextView trainer_lastname_text = view.findViewById(R.id.trainer_lastname_text);
        TextView trainer_email_text = view.findViewById(R.id.trainer_email_text);
        TextView trainer_phone_text = view.findViewById(R.id.trainer_phone_text);

        Button trainer_modify_details_button = view.findViewById(R.id.trainer_modify_details_button);
        Button trainer_back_home = view.findViewById(R.id.trainer_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to


        // Below will show the new added integration
        // db_helper = new database(requireContext());
        // Cursor cursor = db_helper.get_account_id(1);
        // if (cursor.moveToFirst()) {
        // trainer_firstname_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_firstname)));
        // trainer_lastname_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_lastname)));
        // trainer_email_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_email)));
        // trainer_phone_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_phone)));

        user current_user = authentication.get_current_user_details(requireContext());
        // This will get the details to then populate the text boxes, showing that i can change them

        trainer_firstname_text.setText(current_user.getFirstname());
        trainer_lastname_text.setText(current_user.getLastname());
        trainer_email_text.setText(current_user.getEmail());
        trainer_phone_text.setText(current_user.getContact());

        trainer_modify_details_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_modify_details()
            );


        });

        trainer_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_trainer_home()
            );
        });

    }
}