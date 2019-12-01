package com.example.hungrytogetherandroidapplication.ordering_portal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.hungrytogetherandroidapplication.R;

public class OrderingFragment extends Fragment {
    Button sendMealOrderButton;


    public OrderingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        /**
         * Continue your code from here on.
         */

        //sendMealOrderButton = this.findViewById()






        return fragmentView;
    }
}
