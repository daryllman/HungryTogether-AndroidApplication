package com.example.hungrytogetherandroidapplication.my_orders;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.health.SystemHealthManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.new_order_portal.NewOrderActivity;
import com.example.hungrytogetherandroidapplication.open_orders.OpenOrderItem;
import com.example.hungrytogetherandroidapplication.open_orders.OpenOrdersAdapter;
import com.example.hungrytogetherandroidapplication.ordering_portal.OrderingPortalActivity;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static android.util.Log.d;
import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    RecyclerView recyclerView;
    MyOrdersAdapter adapter;
    List<MyOrderItem> myOrdersItemList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroupContainer = container;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        Button createNewOrderButton = view.findViewById(R.id.create_new_order_button);
        createNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "button clicked", Toast.LENGTH_SHORT).show();

                // To pass order_id to OrderingPortalActivity --- so the activity knows which order is the click referring to.
                Intent orderingPortalIntent = new Intent(getActivity(), NewOrderActivity.class);
                Toast.makeText(getContext(),"create your order!", Toast.LENGTH_SHORT).show(); //to be removed
                startActivity(orderingPortalIntent);
            }
        });

        return view;

    }


    public void setUpRecyclerView() {

        //______________________________________________________________________________________________________
        d(TAG, "state1");
        d(TAG, "state1.2");
        myOrdersItemList = new ArrayList<>();
        //______________________________________________________________________________
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();
        DocumentReference myAcctDoc = db.collection("UserBase").document(userId);
        myAcctDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot document = task.getResult();
                Map map = document.getData();
                List<String> sailor_orders = (List<String>) map.get("sailor_orders");

                for (final String sailor_order : sailor_orders) {

                    DocumentReference order = db.collection("OpenOrders").document(sailor_order);
                    order.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot orderDoc = task.getResult();
                            Map orderMap = orderDoc.getData();
                            // all the data needed under the order document, before going into sailor orders
                            final String captain_name = (String) orderMap.get("captain_name");
                            final String restaurant_name = (String) orderMap.get("restaurant_name");
                            final String datetimedeadline = (String) orderMap.get("datetimedeadline");
                            final String pickup_location = (String) orderMap.get("pickup_location");
                            final String captain_fee = (String) orderMap.get("captain_fee");
                            final String progress_state = (String) orderMap.get("progress_state");
                            d(TAG, "state2");

                            DocumentReference order = db.collection("OpenOrders/" + sailor_order + "/SailorOrders").document(userId);
                            order.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot sailorOrderDoc = task.getResult();
                                    Map sailorOrderMap = sailorOrderDoc.getData();
                                    // all the data needed under the sailor order
                                    d(TAG, "state3");
                                    String meal_label = (String) sailorOrderMap.get("meal_label");
                                    String meal_cost = (String) sailorOrderMap.get("meal_cost");
                                    String total_cost = (String) sailorOrderMap.get("total_cost");
                                    d(TAG, "state3");



                                    //Add open orders to list!
                                    myOrdersItemList.add(
                                            new MyOrderItem(
                                                    restaurant_name,
                                                    captain_name,
                                                    datetimedeadline,
                                                    pickup_location,
                                                    meal_label,
                                                    meal_cost,
                                                    captain_fee,
                                                    total_cost,
                                                    progress_state));

                                    //______________________________________________________________________________________________________
                                    adapter = new MyOrdersAdapter(getContext(), myOrdersItemList, getActivity());////

                                    RecyclerView recyclerView = getActivity().findViewById(R.id.my_orders_recyclerview_list);
                                    //recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //by default is vertical layout
                                    recyclerView.setAdapter(adapter);
                                    //______________________________________________________________________________________________________

                                    adapter.notifyDataSetChanged();


                                    d(TAG, "state4");

                                    d(TAG, "restaurant name: " + restaurant_name);
                                    d(TAG, "captain name: " + captain_name);
                                    d(TAG, "datetimedeadline: " + datetimedeadline);
                                    d(TAG, "pickup_location: " + pickup_location);
                                    d(TAG, "meal_label: " + meal_label);
                                    d(TAG, "meal_cost: " + meal_cost);
                                    d(TAG, "captain_fee: " + captain_fee);
                                    d(TAG, "total_cost: " + total_cost);
                                    d(TAG, "progress_state: " + progress_state);
                                    d(TAG, "state5");

                                }
                            });
                        }
                    });
                }

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        setUpRecyclerView();
//
//        if (adapter != null) {
//            adapter.startListening(); // only listens when app is in foreground
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        if (adapter != null) {
//            adapter.stopListening(); // stop listening so if app goes into background, it wont keep listening - waste resources
//        }

    }
}
