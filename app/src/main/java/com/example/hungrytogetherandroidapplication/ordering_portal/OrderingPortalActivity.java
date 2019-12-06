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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderingPortalActivity extends AppCompatActivity {

    private RadioGroup radioFoodGroup;
    private RadioButton radioFoodButton;
    private Button completeOrder;
    Button completeorderButton;

    // Get current user's fb uid, store in fbUid string object
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String fbUid = user.getUid();
    private String sailor_name = user.getDisplayName();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DocumentReference userRef = db.collection("UserBase").document(fbUid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_portal);


        // Retrieve the OpenOrder doc id that I'm writing into
        Intent fromOpenOrdersIntent=getIntent();
        final String orderIdString= fromOpenOrdersIntent.getStringExtra("order_id");
        Log.d("Ordering portal", "selected order id: "+orderIdString);
        Log.d("Ordering portal", "my fbuid: "+fbUid);
        Log.d("Ordering portal", "sailor name: "+sailor_name);

        // Obtain mySailorOrderRef, the docref to where I'm supposed to write to (save my new sailor order here)
        final DocumentReference openOrderRef = db.collection("OpenOrders").document(orderIdString);
        final DocumentReference mySailorOrderRef = db.collection("OpenOrders").document(orderIdString).collection("SailorOrders").document(fbUid);



        //button to complete your order
        //clicking of button does 2 things
        //1) send data of order to the firestore
        //2) switch to next activity (being back to mainactivity)
        radioFoodGroup = (RadioGroup) findViewById(R.id.radioFood);
        completeorderButton = (Button) findViewById(R.id.completeorderbutton);
        completeorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //1) send data(label/name of food, cost and userid) to firestore
                //get selected radio button from radioGroup
                //find the radio button by returned id

                int selectedId = radioFoodGroup.getCheckedRadioButtonId();
                Log.d("evange", "onClick: "+selectedId);
                Toast.makeText(OrderingPortalActivity.this, "selected id is "+selectedId, Toast.LENGTH_SHORT).show();
                String meal_price="0";
                switch (selectedId) {
                    case 2131296495:
                        meal_price = "6.0";
                        break;
                    case 2131296496:
                        meal_price = "6.5";
                        break;
                    case 2131296497:
                        meal_price = "7.0";
                        break;
                    case 2131296498:
                        meal_price = "7.5";
                        break;
                    case 2131296499:
                        meal_price = "8.0";
                        break;
                }

                radioFoodButton = (RadioButton) findViewById(selectedId);

                String food = (String) radioFoodButton.getText();   //obtained meal_label!!
                Log.d("evange", "returned meal_label: "+food);



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

                Map<String, Object> order = new HashMap<>();
                order.put("meal_cost", meal_price);
                order.put("meal_label", food);
                order.put("sailor_name", sailor_name);
                order.put("total_cost", "7.5");

                mySailorOrderRef.set(order)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("evange", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("evange", "Error writing document", e);
                            }

                        });
//
//                Toast.makeText(OrderingPortalActivity.this, "Write Attempt", Toast.LENGTH_SHORT).show();
//
//
//                db.collection("OpenOrder")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        Log.d("FIRESTORE", document.getId() + " => " + document.getData());
//                                        Toast.makeText(OrderingPortalActivity.this, document.getData().toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Log.d("FIRESTORE", "Error getting documents: ", task.getException());
//                                }
//                            }
//                        });

                userRef.update("sailor_orders", FieldValue.arrayUnion(orderIdString));


                //get intent to switch to next activity
                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}
