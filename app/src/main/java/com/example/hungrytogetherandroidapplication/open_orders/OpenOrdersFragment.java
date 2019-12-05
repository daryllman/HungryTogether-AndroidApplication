package com.example.hungrytogetherandroidapplication.open_orders;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenOrdersFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference openOrdersRef = db.collection("OpenOrders");

    private OpenOrdersAdapter adapter;

    Context thisContext;


    public OpenOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroupContainer = container;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_orders, container,false);

        //FloatingActionButton fab = (FloatingActionButton) container.findViewById(R.id.plus_button);


        return view;
    }


    private void setUpRecyclerView(){
        Query query = openOrdersRef.orderBy("slots_left", Query.Direction.ASCENDING); //order by the recency of orders. latest order at the top.
        FirestoreRecyclerOptions<OpenOrderItem> orderOptions = new FirestoreRecyclerOptions.Builder<OpenOrderItem>()
                //.setQuery(query, thisContext)
                .setQuery(query, OpenOrderItem.class)
                .build();

        adapter = new OpenOrdersAdapter(orderOptions, getActivity());

        RecyclerView recyclerView = getActivity().findViewById(R.id.open_orders_recyclerview_list);
        //recyclerView.setHasFixedSize(true);
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
