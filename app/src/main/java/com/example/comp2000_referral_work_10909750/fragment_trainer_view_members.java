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

public class fragment_trainer_view_members extends Fragment {
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

        ArrayList<member_item> member_items = new ArrayList<>();

        member_items.add(new member_item(
                "Shawn",
                "shawn@gmail.com",
                "07785883867"
        ));

        member_items.add(new member_item(
                "Bob",
                "bob@gmail.com",
                "07783576770"
        ));
        // This adds values which will be put into the adapter

        member_adapter adapter = new member_adapter(member_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        // This should fill in the adapter

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