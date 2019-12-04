package com.example.hungrytogetherandroidapplication.food_captain_orders;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hungrytogetherandroidapplication.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodCaptainFragment extends Fragment {

    ImageView restaurant_image;
    ImageView imageViewFoodCaptProgress;
    TextView textViewRestaurant;
    TextView textViewCaptfee;
    TextView textViewPickUpPt;
    TextView textViewSlotsLeft;
    TextView textViewTimeEnd;
    Button buttonUpdateStatus;

    String captain_orders;

    // Get current user's fb uid, store in fbUid string object
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String fbUid = user.getUid();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.collection("UserBase").document(fbUid);

    //temporary: populate the recyclerview with sailororders (by right supposed to get the OpenOrder id (evangefctesting) from captain_orders
    private DocumentReference openOrderRef = db.collection("OpenOrders").document("evangefctesting");
    private CollectionReference sailorOrdersRef = db.collection("OpenOrders").document("evangefctesting").collection("SailorOrders");
//    private CollectionReference sailorOrdersRef = db.collection("/OpenOrders/evangefctesting/SailorOrders");

    //------------------hardcode broooo-----------------
    // temp: hardcode macUrl to imageView
    String macUrl = "https://firebasestorage.googleapis.com/v0/b/hungrytogetherapp.appspot.com/o/restaurantImages%2FMacdonald's%2Fmacdonalds_background.jpg?alt=media&token=220ba19d-7658-4deb-a8f6-49f3aeb12f79";


    private SailorOrdersAdapter adapter;

    Context thisContext;
    ViewGroup viewGroupContainer;

    public FoodCaptainFragment() {
        // Required empty public constructor
    }

    public interface CaptainOrdersCallback{
        void onCallback(String captain_orders);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewGroupContainer = container;

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_food_captain, container, false);

        // Initialise all the variables
        restaurant_image = fragmentView.findViewById(R.id.restaurant_image);
        imageViewFoodCaptProgress = fragmentView.findViewById(R.id.imageViewFoodCaptProgress);
        textViewRestaurant = fragmentView.findViewById(R.id.textViewRestaurant);
        textViewCaptfee = fragmentView.findViewById(R.id.textViewCaptfee);
        textViewPickUpPt = fragmentView.findViewById(R.id.textViewPickUpPt);
        textViewSlotsLeft = fragmentView.findViewById(R.id.textViewSlotsLeft);
        textViewTimeEnd = fragmentView.findViewById(R.id.textViewTimeEnd);
        buttonUpdateStatus = fragmentView.findViewById(R.id.buttonUpdateStatus);

        // ----------------Populate all the views with the relevant open order details----------------
        openOrderRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("success", "DocumentSnapshot data: " + document.getData());

                        String restaurant_name = document.getString("restaurant_name");
                        String captain_fee = document.getString("captain_fee");
                        String pick_up_location = document.getString("pick_up_location");
                        String slots_left = document.getString("slots_left");
                        String datetimedeadline = document.getString("datetimedeadline");

                        textViewRestaurant.setText(restaurant_name);
                        textViewCaptfee.setText(captain_fee);
                        textViewPickUpPt.setText(pick_up_location);
                        textViewSlotsLeft.setText(slots_left);
                        textViewTimeEnd.setText(datetimedeadline);

                    } else {
                        Log.d("success", "No such document");
                    }
                } else {
                    Log.d("fail", "get failed with ", task.getException());
                }
            }
        });

        // Glide is a module to load and cache images (read the build.gradle file for more info that i added)
        Glide
                .with(getContext())
                .load(macUrl).into(restaurant_image); //load photo from url(personPhotoURL) into the photoField


        // ----------------Getting the relevant OpenOrder id----------------

        //TODO: fix async problem: right now i'm unable to save captain_orders=evangefctesting
        // (it is null because i try to use captain_orders before onComplete)

        // Get captain_orders OpenOrder id
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("success", "DocumentSnapshot data: " + document.getData());
                        Log.d("inside", "DocumentSnapshot data: " + document.getString("captain_orders"));  //hardcode the thing i wanna get :))))

                        // Store fc's OpenOrder id in captain_orders String
                        captain_orders = document.getString("captain_orders");

                        Log.d("inside", "is there a string: " + captain_orders);
//                        Toast.makeText(getContext(), "openorderid: " + captain_orders, Toast.LENGTH_LONG).show();

                    } else {
                        Log.d("success", "No such document");
                    }
                } else {
                    Log.d("fail", "get failed with ", task.getException());
                }
            }
        });



        // ---------------- irrelevant shit--------------------
//        Toast.makeText(getContext(), "outside: " + captain_orders, Toast.LENGTH_LONG).show();
//        Log.d("outside", "this is captorder? " + captain_orders);


        return fragmentView;
    }


    private void setUpRecyclerView(){
        Query query = sailorOrdersRef;
        FirestoreRecyclerOptions<SailorOrderItem>  sailorOrderOptions = new FirestoreRecyclerOptions.Builder<SailorOrderItem>()
                .setQuery(query, SailorOrderItem.class)
                .build();

        adapter = new SailorOrdersAdapter(sailorOrderOptions, getActivity());

        RecyclerView recyclerView = getActivity().findViewById(R.id.food_capt_recyclerview_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        setUpRecyclerView();

        if (adapter != null) {
            adapter.startListening(); // only listens when app is in foreground
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening(); // stop listening so if app goes into background, it wont keep listening - waste resources
        }

    }

}
