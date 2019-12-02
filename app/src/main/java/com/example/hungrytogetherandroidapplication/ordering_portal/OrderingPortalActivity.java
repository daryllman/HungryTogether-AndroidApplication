package com.example.hungrytogetherandroidapplication.ordering_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;

public class OrderingPortalActivity extends AppCompatActivity {

    Button backToMainActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_portal);

        //IDK WHY THIS CANNOT WORK>>>TBC
//        backToMainActivityButton.findViewById(R.id.back_to_mainActivity_button);
//        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //
//                // code to send final submissions to firestore
//                //
//
//
//                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
//                startActivity(mainActivityIntent);
//            }
//        });

    }
}
