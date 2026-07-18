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
import android.widget.EditText;
import com.example.comp2000_referral_work_10909750.api.api;
import com.example.comp2000_referral_work_10909750.api.user;
import com.example.comp2000_referral_work_10909750.api.authentication;
import android.widget.Toast;


public class fragment_member_modify_details extends Fragment {
   // private database db_helper;
    public fragment_member_modify_details() {
        super(R.layout.fragment_member_modify_details);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        EditText member_firstname_change_type = view.findViewById(R.id.member_firstname_change_type);
        EditText member_lastname_change_type = view.findViewById(R.id.member_lastname_change_type);
        EditText member_email_change_type = view.findViewById(R.id.member_email_change_type);
        EditText member_phone_change_type = view.findViewById(R.id.member_phone_change_type);

        Button member_submit_changes_button = view.findViewById(R.id.member_submit_changes_button);
        Button member_back_home = view.findViewById(R.id.member_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

      //  db_helper = new database(requireContext());

        user current_user = authentication.get_current_user_details(requireContext());
        // This will get the details to then populate the text boxes, showing that i can change them

        member_firstname_change_type.setText(current_user.getFirstname());
        member_lastname_change_type.setText(current_user.getLastname());
        member_email_change_type.setText(current_user.getEmail());
        member_phone_change_type.setText(current_user.getContact());


        member_submit_changes_button.setOnClickListener(v -> {

            String firstname = member_firstname_change_type.getText().toString();
            String lastname = member_lastname_change_type.getText().toString();
            String email = member_email_change_type.getText().toString();
            String phone = member_phone_change_type.getText().toString();
            // These will allow me to convert the text into a string
            // That will allow me to put it into the update method

           // int member_id = 2;
            // Placeholde account

            if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "Not all fields are filled", Toast.LENGTH_SHORT).show();
                return;
            }

          //  db_helper.account_update(member_id, firstname, lastname, email, phone);

            user user_updated = new user();
            user_updated.setId(current_user.getId());
            user_updated.setUsername(current_user.getUsername());
            user_updated.setFirstname(firstname);
            user_updated.setLastname(lastname);
            user_updated.setEmail(email);
            user_updated.setContact(phone);
            user_updated.setPassword(current_user.getPassword());
            user_updated.setUserType("Member");
            // These are all of the required fields of the api

            api.update_user(requireContext(), user_updated, current_user.getUsername(), new api.message_callback() {
                @Override
                public void success(String message) {
                    authentication.current_user_save(requireContext(), user_updated);
                    // This will save the user details

                    Toast.makeText(requireContext(), "Updates made", Toast.LENGTH_SHORT).show();

                    ((MainActivity) requireActivity()).openFragment(
                            new fragment_trainer_home()
                    );
                }

                @Override
                public void error(String message) {
                    Toast.makeText(requireContext(), "Updates failed", Toast.LENGTH_SHORT).show();
                }
            });



        });


        member_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
            );

        });

        // The on click listeners will load the fragments wanted (basically changing the UI)


    }
  //  @Override
  //  public void onDestroyView() {
      //  if (db_helper != null) {
          //  db_helper.close();
      //  }
      //  super.onDestroyView();
  //  }
}
