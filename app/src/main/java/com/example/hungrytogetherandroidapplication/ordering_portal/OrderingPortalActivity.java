package com.example.hungrytogetherandroidapplication.ordering_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;
import com.example.hungrytogetherandroidapplication.my_orders.MyOrderItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;

public class OrderingPortalActivity extends AppCompatActivity {

    private RadioGroup radioFoodGroup;
    private RadioButton radioFoodButton;
    private Button completeOrder;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button completeorderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_portal);

        // this is your activity.
        // the layout is activity_ordering_portal

        //button to get back to main activity
//        Button backToMainActivityButton= (Button) findViewById(R.id.back_to_mainActivity_button);
//        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // code to send final submissions to firestore
//                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
//                startActivity(mainActivityIntent);
//            }
//        });


        //button to complete your order
        //clicking of button does 2 things
        //1) send data of order to the firestore
        //2) switch to next activity (being back to mainactivity?)

        completeorderButton = findViewById(R.id.completeorderbutton);
        completeorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //1) send data(label/name of food, cost and userid) to firestore
                //get selected radio button from radioGroup
                //find the radio button by returned id
                int selectedId = radioFoodGroup.getCheckedRadioButtonId();
                radioFoodButton = (RadioButton) findViewById(selectedId);

                String food = (String) radioFoodButton.getText();

                //sending up to firebase
//                final String openorderid = "pdQwKDYLtmCoO1PK54MF";// open order id currently hardcoded for testing
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                final String userId=user.getUid();
//                MyOrder my_enrolled_order = new MyOrder(food, userId , "10.50");
//                db.document("ABC/DEF")
//                        .set(my_enrolled_order)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) { Log.d("THURSDAY", "UserBase/" + openorderid + "/OpenOrderCol/" + "hwllo"); }
//                        });
//
//                Toast.makeText(OrderingPortalActivity.this, "Write Attempt", Toast.LENGTH_SHORT).show();


                db.collection("OpenOrder")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("FIRESTORE", document.getId() + " => " + document.getData());
                                        Toast.makeText(OrderingPortalActivity.this, document.getData().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Log.d("FIRESTORE", "Error getting documents: ", task.getException());
                                }
                            }
                        });


                //get intent to switch to next activity
                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}
