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
import  android.widget.TextView;
import android.database.Cursor;


public class fragment_member_account_details extends Fragment {
    private database db_helper;
    public fragment_member_account_details() {
        super(R.layout.fragment_member_account_details);
    }
    // Makes it so this fragment will be used

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        TextView member_firstname_text = view.findViewById(R.id.member_firstname_text);
        TextView member_lastname_text = view.findViewById(R.id.member_lastname_text);
        TextView member_email_text = view.findViewById(R.id.member_email_text);
        TextView member_phone_text = view.findViewById(R.id.member_phone_text);

        Button member_modify_details_button = view.findViewById(R.id.member_modify_details_button);
        Button member_back_home = view.findViewById(R.id.member_back_home);
        // This finds the ids of the buttons#
        // Meaning that they will perform the actions i want them to

        // Below will show the new added integration
        db_helper = new database(requireContext());
        Cursor cursor = db_helper.get_account_id(2);
        if (cursor.moveToFirst()) {
            member_firstname_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_firstname)));
            member_lastname_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_lastname)));
            member_email_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_email)));
            member_phone_text.setText(cursor.getString(cursor.getColumnIndexOrThrow(database.account_phone)));


        }

        member_modify_details_button.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_modify_details()
            );

        });



        member_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(
                    new fragment_member_home()
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