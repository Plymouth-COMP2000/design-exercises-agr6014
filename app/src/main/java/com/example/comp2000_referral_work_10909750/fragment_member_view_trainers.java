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

        ArrayList<trainer_item> trainer_items = new ArrayList<>();

        trainer_items.add(new trainer_item(
                "Alex",
                "Monday     10:00 - 17:00",
                "07785883990"
        ));

        trainer_items.add(new trainer_item(
                "Jake",
                "Monday     10:00 - 15:00",
                "07783883770"
        ));
        // This adds values which will be put into the adapter

        trainer_adapter adapter = new trainer_adapter(trainer_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        // This should fill in the adapter

        member_back_home.setOnClickListener(v -> {
            ((MainActivity) requireActivity())
                    .openFragment(new fragment_member_home());
        });

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.member_view_account_details) {
                ((MainActivity) requireActivity()).openFragment(new fragment_member_account_details());
                return true;
            }
            return false;
        });

    }

}