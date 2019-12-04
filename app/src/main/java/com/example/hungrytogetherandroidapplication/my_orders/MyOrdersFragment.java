package com.example.hungrytogetherandroidapplication.my_orders;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungrytogetherandroidapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    RecyclerView recyclerView;
    MyOrdersAdapter adapter;

    List<MyOrderItem> myOrdersItemList;

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroupContainer = container;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container,false);
        //______________________________________________________________________________________________________


        recyclerView = (RecyclerView) view.findViewById(R.id.my_orders_recyclerview_list);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //by default is vertical layout

        myOrdersItemList = new ArrayList<>();

        //Add open orders to list!
        myOrdersItemList.add(
                new MyOrderItem(
                       "MacDonnies",
                        "Iman Heizer",
                        "23:23",
                        "McSpicy meal",
                        "6.5",
                        "0.5",
                        "7.0"));


        adapter = new MyOrdersAdapter(getContext(), myOrdersItemList, getActivity());
        recyclerView.setAdapter(adapter);

        //______________________________________________________________________________________________________
        return view;
    }

}
