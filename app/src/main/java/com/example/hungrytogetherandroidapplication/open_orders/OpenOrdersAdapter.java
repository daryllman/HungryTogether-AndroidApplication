package com.example.hungrytogetherandroidapplication.open_orders;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungrytogetherandroidapplication.R;

import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;
import com.example.hungrytogetherandroidapplication.ordering_portal.OrderingPortalActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;



public class OpenOrdersAdapter extends FirestoreRecyclerAdapter<OpenOrderItem, OpenOrdersAdapter.OpenOrderHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private Context context;
    private Activity activity;

    public OpenOrdersAdapter(@NonNull FirestoreRecyclerOptions<OpenOrderItem> options, Activity context) {
        super(options);
        this.activity=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final OpenOrderHolder holder, int position, @NonNull OpenOrderItem model) { // Tells Adapter what we want to put in each view in each card layout
        context = holder.restaurantImage.getContext(); //taking any to access context.

        holder.restaurantName.setText(model.getRestaurant_name());
        holder.captainName.setText(model.getCaptain_name());
        holder.pickupLocation.setText(model.getPickup_location());
        holder.dateTimeDeadline.setText(model.getDatetimedeadline());
        holder.slotsLeft.setText(model.getSlots_left());

        // Glide is a module to load and cache images (read the build.gradle file for more info that i added)
        Glide
                .with(holder.restaurantImage.getContext())
                .load(model.getRestaurant_image()).into(holder.restaurantImage); //load photo from url(personPhotoURL) into the photoField

        holder.toOrderingPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Dialog dialog = new Dialog(activity);
//               dialog.setContentView(R.layout.sample_dialog);
//               dialog.show();

//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
//                View mView = activity.getLayoutInflater().inflate(R.layout.sample_dialog, null);
//                mBuilder.setView(mView);
//                final AlertDialog dialog = mBuilder.create();
//                dialog.show();

                Intent orderingPortalIntent = new Intent(activity, OrderingPortalActivity.class);
                activity.startActivity(orderingPortalIntent);



            }
        });

    }

    @NonNull
    @Override
    public OpenOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //tells adapter which layout to inflate
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_order_item, parent, false);
        context = parent.getContext();
        return new OpenOrderHolder(v);
    }


    class OpenOrderHolder extends RecyclerView.ViewHolder{
        ImageView restaurantImage;
        TextView restaurantName;
        TextView captainName;
        TextView pickupLocation;
        TextView dateTimeDeadline;
        TextView slotsLeft;
        Button toOrderingPortalButton;



        public OpenOrderHolder(@NonNull View itemView) { // Holder to connect variable to a particular layout id in card
            super(itemView);
            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            captainName = itemView.findViewById(R.id.food_captain_name);
            pickupLocation = itemView.findViewById(R.id.pick_up_name);
            dateTimeDeadline = itemView.findViewById(R.id.time_left_mins);
            slotsLeft = itemView.findViewById(R.id.slots_left_num);
            toOrderingPortalButton = itemView.findViewById(R.id.to_ordering_portal_button);
        }
    }
}
