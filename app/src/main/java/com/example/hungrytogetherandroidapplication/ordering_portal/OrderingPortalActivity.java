package com.example.hungrytogetherandroidapplication.ordering_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;

public class OrderingPortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_portal);

        // this is your activity.
        // the layout is activity_ordering_portal


        Intent fromOpenOrdersIntent = getIntent();
        String orderIdString = fromOpenOrdersIntent.getStringExtra("order_id");
        //Toast.makeText(this,"received order_id: "+ orderIdString, Toast.LENGTH_LONG).show(); //to be removed





        Button backToMainActivityButton= (Button) findViewById(R.id.back_to_mainActivity_button);
        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                // code to send final submissions to firestore
                //


                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

    }
}
