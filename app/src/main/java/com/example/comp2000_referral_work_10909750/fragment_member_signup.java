package com.example.comp2000_referral_work_10909750;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.example.comp2000_referral_work_10909750.R;
// This will let me use "R"
import com.example.comp2000_referral_work_10909750.MainActivity;

public class fragment_member_signup extends Fragment {
    private database db_helper;

    public fragment_member_signup() {
        super(R.layout.fragment_member_signup);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText member_firstname_type = view.findViewById(R.id.member_firstname_type);
        EditText member_lastname_type = view.findViewById(R.id.member_lastname_type);
        EditText member_email_type = view.findViewById(R.id.member_email_type);
        EditText member_phone_type = view.findViewById(R.id.member_phone_type);
        EditText member_password_type = view.findViewById(R.id.member_password_type);
        // These find the ids of the edit text boxes

        Button create_account_button = view.findViewById(R.id.create_account_button);
        Button member_back_choice = view.findViewById(R.id.member_back_choice);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        // Under this will be the account creation and storing

        db_helper = new database(requireContext());

        create_account_button.setOnClickListener(v -> {

            String firstname = member_firstname_type.getText().toString();
            String lastname = member_lastname_type.getText().toString();
            String email = member_email_type.getText().toString();
            String phone = member_phone_type.getText().toString();
            String password = member_password_type.getText().toString();

            if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                return;
            }
            // This will simply not make the account if any of them are empty

            db_helper.insert_account(firstname, lastname, email, phone, password, "Member");
            // This creates the account and stores it in the databas

            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
            );

        });

        member_back_choice.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_signup_login()
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