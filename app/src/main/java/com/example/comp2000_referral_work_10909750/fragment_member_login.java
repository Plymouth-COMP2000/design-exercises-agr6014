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
import com.example.comp2000_referral_work_10909750.api.authentication;
import android.widget.Toast;
// This will let me display text


public class fragment_member_login extends Fragment {
    public fragment_member_login() {
        super(R.layout.fragment_member_login);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText member_email_type = view.findViewById(R.id.member_email_type);
        EditText member_password_type = view.findViewById(R.id.member_password_type);
        // These find the ids of the edit text boxes

        Button member_login_button = view.findViewById(R.id.member_login_button);
        Button member_back_choice = view.findViewById(R.id.member_back_choice);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        member_login_button.setOnClickListener(v -> {
            String email = member_email_type.getText().toString();
            String password = member_password_type.getText().toString();

            authentication.member_login(requireContext(), email, password, new authentication.callback_login() {
                @Override
                public void success() {

                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show();

                    ((MainActivity) requireActivity()).openFragment(
                            new fragment_member_home()
                    );
                }
                // If the credentials match and the login is successful, they will enter the app

                @Override
                public void error(String message) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                }
            });



        });

        member_back_choice.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_signup_login()
            );

        });
        // The on click listeners will load the fragments wanted (basically changing the UI)

    }
}