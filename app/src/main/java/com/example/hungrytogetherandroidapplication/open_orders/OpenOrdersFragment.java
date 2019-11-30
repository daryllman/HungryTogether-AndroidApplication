package com.example.hungrytogetherandroidapplication.open_orders;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungrytogetherandroidapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenOrdersFragment extends Fragment {


    public OpenOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_orders, container, false);
    }

}
