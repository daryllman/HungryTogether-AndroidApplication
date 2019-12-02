package com.example.hungrytogetherandroidapplication.open_orders;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungrytogetherandroidapplication.R;

import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;
import com.example.hungrytogetherandroidapplication.ordering_portal.OrderingFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;



public class OpenOrdersAdapter extends FirestoreRecyclerAdapter<OpenOrderItem, OpenOrdersAdapter.OpenOrderHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public OpenOrdersAdapter(@NonNull FirestoreRecyclerOptions<OpenOrderItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OpenOrderHolder holder, int position, @NonNull final OpenOrderItem model) { // Tells Adapter what we want to put in each view in each card layout
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
                Fragment newFragment = new OrderingFragment();
                FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new OrderingFragment());
               fragmentTransaction.addToBackStack(null).commit();
            }

            /*
            // Create new fragment and transaction
                Fragment newFragment = new ExampleFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            */


        });

    }

    @NonNull
    @Override
    public OpenOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //tells adapter which layout to inflate

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_order_item, parent, false);
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
            toOrderingPortalButton = itemView.findViewById(R.id.to_ordering_button);
        }
    }
}
