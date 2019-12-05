package com.example.hungrytogetherandroidapplication.new_order_portal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hungrytogetherandroidapplication.R;


public class select_slots_available extends AppCompatActivity {

    Button confirm_slots_available_button;
    EditText slots_available_no;
    String slots_available;
    public static final String SAVE_SLOTS = "SAVE_SLOTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_slots_available);

        //set up the dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));

        //defining the widgets
        confirm_slots_available_button = findViewById(R.id.confirm_slots_available_button);
        slots_available_no = findViewById(R.id.slots_available_no);

        confirm_slots_available_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slots_available = slots_available_no.getText().toString();
                Log.i("glenda", slots_available);
                if (slots_available == null || slots_available == "0") {
                    Toast.makeText(select_slots_available.this, "please enter a number", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent myIntent = new Intent(v.getContext(), NewOrderActivity.class);
                    String slots_available_choice = slots_available_no.getText().toString();
                    SharedPreferences saved_slots_state = getSharedPreferences("SLOTS", 0);
                    SharedPreferences.Editor editor = saved_slots_state.edit();
                    editor.putString(SAVE_SLOTS,slots_available_choice);
                    editor.commit();


                    startActivityForResult(myIntent, 0);

                }
            }
        });
    }

}







