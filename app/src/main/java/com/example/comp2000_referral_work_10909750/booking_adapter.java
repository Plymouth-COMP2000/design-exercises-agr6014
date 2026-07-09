package com.example.comp2000_referral_work_10909750;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class booking_adapter extends RecyclerView.Adapter<booking_adapter.MenuViewHolder> {
    private ArrayList<booking_item> booking_items;

    public booking_adapter(ArrayList<booking_item> booking_items) {
        this.booking_items = booking_items;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_list_details,
                parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder,
                                 int position) {

        booking_item item = booking_items.get(position);

        holder.member_on_booking.setText(item.getMember_on_booking());
        holder.trainer_on_booking.setText(item.getTrainer_on_booking());
        holder.date_time_on_booking.setText(item.getDate_time_on_booking());

    }
    // This gets the value of each attribute

    @Override
    public int getItemCount() {
        return booking_items.size();
    }
    // This gets the size of the amount of entries

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView member_on_booking;
        TextView trainer_on_booking;
        TextView date_time_on_booking;

        public MenuViewHolder(@NonNull View booking_view) {
            super(booking_view);

            member_on_booking = booking_view.findViewById(R.id.member_on_booking);
            trainer_on_booking = booking_view.findViewById(R.id.trainer_on_booking);
            date_time_on_booking = booking_view.findViewById(R.id.date_time_on_booking);

        }

    }

}

