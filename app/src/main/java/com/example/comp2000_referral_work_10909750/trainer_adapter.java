package com.example.comp2000_referral_work_10909750;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.example.comp2000_referral_work_10909750.api.user;
public class trainer_adapter extends RecyclerView.Adapter<trainer_adapter.MenuViewHolder> {
    private ArrayList<user> trainer_items;

    public trainer_adapter(ArrayList<user> trainer_items) {
        this.trainer_items = trainer_items;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainer_list_details,
                parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder,
                                 int position) {

        user item = trainer_items.get(position);

        String name = item.getFirstname() + " " + item.getLastname();

        holder.trainer_name.setText(name);
      //  holder.trainer_availability.setText(item.getTrainer_availability());
        holder.trainer_phone.setText(item.getContact());

    }
    // This gets the value of each attribute

    @Override
    public int getItemCount() {
        return trainer_items.size();
    }
    // This gets the size of the amount of entries

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView trainer_name;
        TextView trainer_availability;
        TextView trainer_phone;

        public MenuViewHolder(@NonNull View trainer_view) {
            super(trainer_view);

            trainer_name = trainer_view.findViewById(R.id.trainer_name);
            trainer_availability = trainer_view.findViewById(R.id.trainer_availability);
            trainer_phone = trainer_view.findViewById(R.id.trainer_phone);

        }

    }

}
