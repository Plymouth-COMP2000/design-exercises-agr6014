package com.example.comp2000_referral_work_10909750;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
// These will allow for the overflow menu
import android.database.Cursor;
import com.example.comp2000_referral_work_10909750.api.api;
import com.example.comp2000_referral_work_10909750.api.user;
// These will allow me to get the users from the api
import java.util.List;

public class fragment_member_view_trainers extends Fragment {
    public fragment_member_view_trainers() {
        super(R.layout.fragment_member_view_trainers);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.view_trainer_recycler_view);
        Button member_back_home = view.findViewById(R.id.member_back_home);
        Toolbar toolbar = view.findViewById(R.id.member_tool_bar);
        // These are all of the features in the fragment
        // recyclerview will show the trainers, back button and overflow menu

        ArrayList<user> trainers = new ArrayList<>();
        /// Changed to use the api.user file
       // database db_helper = new database(requireContext());
       // Cursor cursor = db_helper.get_trainers();

       // while (cursor.moveToNext()) {
          //  int trainer_id = cursor.getInt(cursor.getColumnIndexOrThrow(database.account_id));
           // String firstname = cursor.getString(cursor.getColumnIndexOrThrow(database.account_firstname));
            // This will get the firstname from the database
           // String lastname = cursor.getString(cursor.getColumnIndexOrThrow(database.account_lastname));
            // Same as above
           // String phone = cursor.getString(cursor.getColumnIndexOrThrow(database.account_phone));

           // trainer_items.add(new trainer_item(trainer_id, firstname + " " + lastname, "Monday  12:00 - 13:00", phone));
       // }

       // cursor.close();

       // trainer_items.add(new trainer_item(
               // "Alex",
               // "Monday     10:00 - 17:00",
               // "07785883990"
       // ));

       // trainer_items.add(new trainer_item(
               // "Jake",
               // "Monday     10:00 - 15:00",
               // "07783883770"
        // ));
        // This adds values which will be put into the adapter

        trainer_adapter adapter = new trainer_adapter(trainers);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        // This should fill in the adapter

        api.get_all_users(requireContext(), new api.callback_user() {
            @Override
            public void success(List<user> users) {
                for (user trainer : users) {
                    if (trainer.getUserType().equalsIgnoreCase("Trainer")) {
                        // This will check if the account is a trainer account
                        String name = trainer.getFirstname() + " " + trainer.getLastname();
                        // This will let me combine both the first and last name of the trainer
                        // trainer_items.add(new trainer_item(name, "To be added", trainer.getContact()));

                        trainers.add(trainer);
                    }
                }
                adapter.notifyDataSetChanged();;
            }

            @Override
            public void error(String message) {

            }
        });



        member_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity())
                    .openFragment(new fragment_member_home());
        });

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.view_account_details) {
                ((MainActivity) requireActivity()).openFragment(new fragment_member_account_details());
                return true;
            }
            return false;
        });

    }

}