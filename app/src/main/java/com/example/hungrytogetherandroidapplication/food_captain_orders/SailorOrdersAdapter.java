package com.example.hungrytogetherandroidapplication.food_captain_orders;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytogetherandroidapplication.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SailorOrdersAdapter extends FirestoreRecyclerAdapter<SailorOrderItem, SailorOrdersAdapter.SailorOrdersHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private Context context;
    private Activity activity;

    public SailorOrdersAdapter(@NonNull FirestoreRecyclerOptions<SailorOrderItem> options, Activity context) {
        super(options);
        this.activity = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull SailorOrdersHolder holder, int position, @NonNull SailorOrderItem model) {
        context = holder.sailor_name.getContext();    //taking any to access context??

        holder.meal_cost.setText(model.getMeal_cost());
        holder.meal_label.setText(model.getMeal_label());
        holder.sailor_name.setText(model.getSailor_name());
        holder.total_cost.setText(model.getTotal_cost());

    }

    @NonNull
    @Override
    public SailorOrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //tells adapter which layout to inflate
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sailor_order_item, parent, false);
//        context = parent.getContext();
        return new SailorOrdersHolder(v);
    }


    class SailorOrdersHolder extends RecyclerView.ViewHolder{

        TextView meal_cost;
        TextView meal_label;
        TextView sailor_name;
        TextView total_cost;

        public SailorOrdersHolder(@NonNull View itemView) {
            super(itemView);
            meal_cost = itemView.findViewById(R.id.meal_cost);
            meal_label = itemView.findViewById(R.id.meal_label);
            sailor_name = itemView.findViewById(R.id.sailor_name);
            total_cost = itemView.findViewById(R.id.total_cost);
        }
    }
}