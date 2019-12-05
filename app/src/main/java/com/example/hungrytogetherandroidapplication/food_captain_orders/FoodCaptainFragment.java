package com.example.hungrytogetherandroidapplication.food_captain_orders;


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

import com.bumptech.glide.Glide;
import com.example.hungrytogetherandroidapplication.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

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
//    captain_orders = "evangefctesting";

    // Get current user's fb uid, store in fbUid string object
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String fbUid = user.getUid();

    // Obtain userRef, the docref to my current account
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.collection("UserBase").document(fbUid);

    //temporary: populate the recyclerview with sailororders (by right supposed to get the OpenOrder id (evangefctesting) from captain_orders
//    private DocumentReference openOrderRef = db.collection("OpenOrders").document(captain_orders);
//    private CollectionReference sailorOrdersRef = db.collection("OpenOrders").document("evangefctesting").collection("SailorOrders");

    private SailorOrdersAdapter adapter;

    Context thisContext;
    ViewGroup viewGroupContainer;

    //------------------hardcode broooo-----------------
    // temp: hardcode macUrl to imageView
    String macUrl = "https://firebasestorage.googleapis.com/v0/b/hungrytogetherapp.appspot.com/o/restaurantImages%2FMacdonald's%2Fmacdonalds_background.jpg?alt=media&token=220ba19d-7658-4deb-a8f6-49f3aeb12f79";

    String TAG="testtest";


//    private final static int TYPE_EMPTY=1,TYPE_GOTORDER=2;


    public FoodCaptainFragment() {
        // Required empty public constructor
    }

    // attempts to change fragment
//    public int getItemViewType(DocumentReference openOrderRef){
//        openOrderRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        captain_orders = document.getString("captain_orders");
//                        if (captain_orders.isEmpty()){
//
//                        }
//                    }
//                }
//            }
//        });
//        if (captain_orders.isEmpty()){
//            return TYPE_EMPTY;
//        }
//        if (mOrderActivityList.get(position) instanceof ConsumerOrderItem){
//            return TYPE_JOINEDORDERS;
//        } else if (mOrderActivityList.get(position) instanceof FoodCaptOrderItem){
//            return TYPE_ORDERSFROMOTHERS;
//        }
//        return -1;
//    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        viewGroupContainer = container;

        // Inflate the layout for this fragment
//        View fragmentView = inflater.inflate(R.layout.fragment_food_captain_empty, container, false);
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

        //-------------start by retrieving the relevant OpenOrder id----------------
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Store fc's OpenOrder id in String captain_orders
                        captain_orders = document.getString("captain_orders");
                        Log.d("firestore", "retrieved captain_orders as: " + captain_orders);

                        // Get firestore references
                        DocumentReference openOrderRef = db.collection("OpenOrders").document(captain_orders);
//                        CollectionReference sailorOrdersRef = db.collection("OpenOrders").document(captain_orders).collection("SailorOrders");

                        // Inflate the fragment layout
                        inflateLayout(openOrderRef);


                    } else {
                        Log.d("success", "No such document");
                    }
                } else {
                    Log.d("fail", "get failed with ", task.getException());
                }
            }
        });


        return fragmentView;
    }

    private void inflateLayout(final DocumentReference openOrderRef) {

        // todo find a better representation
        final ArrayList<Integer> foodCaptProgress = new ArrayList<>();
        foodCaptProgress.add(R.drawable.foodcapt1);
        foodCaptProgress.add(R.drawable.foodcapt2);
        foodCaptProgress.add(R.drawable.foodcapt3);
        foodCaptProgress.add(R.drawable.foodcapt4);
        foodCaptProgress.add(R.drawable.foodcapt4);

        // ----------------Populate all the views with the relevant OpenOrder details----------------
        // ==TextView==
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

        // ==ImageView==
        // Glide is a module to load and cache images (read the build.gradle file for more info that i added)
        Glide
                .with(getContext())
                .load(macUrl).into(restaurant_image); //load photo from url(personPhotoURL) into the photoField

        //todo save the image state when closing the app/fragment
        // but for now, every onCreateView, we read progress_state to know which image to put for imageViewFoodCaptProgress
        openOrderRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String progstate_string = documentSnapshot.getString("progress_state");
                int progstate_int = Integer.parseInt(progstate_string);
                int image = foodCaptProgress.get(progstate_int);
                imageViewFoodCaptProgress.setImageResource(image);
            }
        });


        // ==Button==
        buttonUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // --------------------------Update progress_state-----------------------------
                /** progress_state indicated the stage in which the OpenOrder is in
                 * 0 --> default, still accepting orders from new hungry sailors
                 * 1 --> close order, no longer accepting new orders
                 * 2 --> orders sent, food order has been placed via GrabFood or any external delivery service
                 * 3 --> arrived, food has arrived at the pick_up_location prompting sailors to come collect their food
                 * 4 --> complete, all the food has been collected so I can archive this OpenOrder item into PastOrders
                 */
                // Update progress_state (read, convert String-->Integer, ++, convert Integer-->String, update progress_state in Firestore)
                openOrderRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String progstate_string = documentSnapshot.getString("progress_state");
                        Log.d("progress state", "fetch string: "+progstate_string);

                        int progstate_int = Integer.parseInt(progstate_string);
                        Log.d("progress state", "before local update: "+progstate_int);
                        if (progstate_int<4) {   // maximum of progress_state=4
                            progstate_int++;
                            // Update the relevant imageView to show FoodCapt which state he is in
                            int image = foodCaptProgress.get(progstate_int);
                            imageViewFoodCaptProgress.setImageResource(image);
                        }
                        Log.d("progress state", "after local update: "+progstate_int);

                        String progstate_new = String.valueOf(progstate_int);
                        openOrderRef.update("progress_state", progstate_new);

                        // Close order, remove from OpenOrders page but still able to be accessed by FoodCapt/Sailors on Firebase
                        // (update accepting_orders tag from true-->false)
                        if (progstate_int==1){
                            openOrderRef.update("accepting_orders", false);
                            Log.d("accepting_orders", "order closed");
                        }

                        // Completed order, delete from OpenOrders and write to PastOrders
                        //todo: problem with subcollection SailorOrders is not deleted nor ported over
                        if (progstate_int==4) {
                            moveFirestoreDocument(fromPath, toPath);
                            Log.d(TAG, "onClick: movingtest to moved");
                        }
                    }
                });
            }
        });
    }

    // Moving completed orders from OpenOrders to PastOrders: use the same document id (captain_orders)
    // to test this code, first create document "movingtest" under OpenOrders
    private DocumentReference fromPath = db.collection("OpenOrders").document("movingtest");
    private DocumentReference toPath = db.collection("PastOrders").document("movingtest");

    /**
     * How to move a document in Cloud Firestore?
     * https://stackoverflow.com/questions/47244403/how-to-move-a-document-in-cloud-firestore
     * @param fromPath = db.collection("OpenOrders").document(captain_orders)
     * @param toPath = db.collection("PastOrders").document(captain_orders)
     */
    public void moveFirestoreDocument(final DocumentReference fromPath, final DocumentReference toPath) {
        fromPath.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        toPath.set(document.getData())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                        fromPath.delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error deleting document", e);
                                                    }
                                                });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error writing document", e);
                                    }
                                });
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }


    private void setUpRecyclerView(String orderid){

        CollectionReference sailorOrdersRef = db.collection("OpenOrders").document(orderid).collection("SailorOrders");
        Log.d("setUpRecyclerView", "setUpRecyclerView: works!!");
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

//    String captorders = null;

    @Override
    public void onStart() {
        super.onStart();

        //-------------start by retrieving the relevant OpenOrder id----------------
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Store fc's OpenOrder id in String captain_orders
                        captain_orders = document.getString("captain_orders");
//                        Log.d("recycler", "captain_orders: "+captain_orders);
//
//                        // Get firestore reference
//                        CollectionReference sailorOrdersRef = db.collection("OpenOrders").document(captain_orders).collection("SailorOrders");
//                        Log.d("recycler", "collection reference id (sailorordersref): "+sailorOrdersRef.get());
//                        Log.d("recycler", "collection reference path (sailorordersref): "+sailorOrdersRef.getPath());

                        // set up Recyclerview
                        setUpRecyclerView("evangefctesting");
//                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("success", "No such document");
                    }
                } else {
                    Log.d("fail", "get failed with ", task.getException());
                }
            }
        });

//        Log.d("setUpRecyclerView", "onStart: setting up recyclerview");

//        Log.d("aye", "captain orders: "+captain_orders);
//        setUpRecyclerView("evangefctesting");

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
