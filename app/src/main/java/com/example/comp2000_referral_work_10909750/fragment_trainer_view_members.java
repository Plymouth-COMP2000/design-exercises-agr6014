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

public class fragment_trainer_view_members extends Fragment {
  //  private database db_helper;

    public fragment_trainer_view_members() {
        super(R.layout.fragment_trainer_view_members);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.trainer_view_members_recycler_view);
        Button member_back_home = view.findViewById(R.id.trainer_back_home);
        Toolbar toolbar = view.findViewById(R.id.trainer_tool_bar);
        // These are all of the features in the fragment
        // recyclerview will show the trainers, back button and overflow menu

        // ArrayList<member_item> member_items = new ArrayList<>();

       // member_items.add(new member_item(
               // "Shawn",
               // "shawn@gmail.com",
               // "07785883867"
       // ));

       // member_items.add(new member_item(
               // "Bob",
               // "bob@gmail.com",
               // "07783576770"
       // ));
        // This adds values which will be put into the adapter

        // database db_helper = new database(requireContext());

        ArrayList<user> member_items = new ArrayList<>();
      //  Cursor cursor = db_helper.get_members();

      //  while (cursor.moveToNext()) {
          //  String firstname = cursor.getString(cursor.getColumnIndexOrThrow(database.account_firstname));
           // String lastname = cursor.getString(cursor.getColumnIndexOrThrow(database.account_lastname));
           // String email = cursor.getString(cursor.getColumnIndexOrThrow(database.account_email));
           // String phone = cursor.getString(cursor.getColumnIndexOrThrow(database.account_phone));

           // member_items.add(new member_item(firstname + " " + lastname, email, phone));

       // }

       // cursor.close();

        member_adapter adapter = new member_adapter(member_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        // This should fill in the adapter

        api.get_all_users(requireContext(), new api.callback_user() {
            @Override
            public void success(List<user> users) {
                for (user member : users) {
                    if (member.getUserType().equalsIgnoreCase("Member")) {
                        // This will check if the account is a trainer account
                        String name = member.getFirstname() + " " + member.getLastname();
                        // This will let me combine both the first and last name of the trainer
                        // trainer_items.add(new trainer_item(name, "To be added", trainer.getContact()));

                        member_items.add(member);
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
                    .openFragment(new fragment_trainer_home());
        });

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.view_account_details) {
                ((MainActivity) requireActivity()).openFragment(new fragment_trainer_account_details());
                return true;
            }
            return false;
        });

    }

}