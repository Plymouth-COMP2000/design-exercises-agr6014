package com.example.comp2000_referral_work_10909750;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class member_adapter extends RecyclerView.Adapter<member_adapter.MenuViewHolder> {
    private ArrayList<member_item> member_items;

    public member_adapter(ArrayList<member_item> member_items) {
        this.member_items = member_items;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list_details,
                parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder,
                                 int position) {

        member_item item = member_items.get(position);

        holder.member_name.setText(item.getMember_name());
        holder.member_email.setText(item.getMember_email());
        holder.member_phone.setText(item.getMember_phone());

    }
    // This gets the value of each attribute

    @Override
    public int getItemCount() {
        return member_items.size();
    }
    // This gets the size of the amount of entries

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView member_name;
        TextView member_email;
        TextView member_phone;

        public MenuViewHolder(@NonNull View trainer_view) {
            super(trainer_view);

            member_name = trainer_view.findViewById(R.id.member_name);
            member_email = trainer_view.findViewById(R.id.member_email);
            member_phone = trainer_view.findViewById(R.id.member_phone);

        }

    }

}
