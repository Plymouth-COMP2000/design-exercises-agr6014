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


public class fragment_trainer_login extends Fragment {
    public fragment_trainer_login() {
        super(R.layout.fragment_trainer_login);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText staff_email_type = view.findViewById(R.id.staff_email_type);
        EditText staff_password_type = view.findViewById(R.id.staff_password_type);
        // These find the ids of the edit text boxes

        Button staff_login_button = view.findViewById(R.id.staff_login_button);
        Button back_button = view.findViewById(R.id.back_button);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        staff_login_button.setOnClickListener(v -> {
            String email = staff_email_type.getText().toString();
            String password = staff_password_type.getText().toString();

            authentication.trainer_login(requireContext(), email, password, new authentication.callback_login() {
                @Override
                public void success() {

                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show();

                    ((MainActivity) requireActivity()).openFragment(
                            new fragment_trainer_home()
                    );
                }
                // If the credentials match and the login is successful, they will enter the app

                @Override
                public void error(String message) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                }
            });

        });

        back_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_start()
            );

        });
        // The on click listeners will load the fragments wanted (basically changing the UI)

    }
}