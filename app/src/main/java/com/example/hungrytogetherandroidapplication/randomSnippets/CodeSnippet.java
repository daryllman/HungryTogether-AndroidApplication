package com.example.hungrytogetherandroidapplication.randomSnippets;

public class CodeSnippet {


    // TODO: READING A LIST OF CUSTOM OBJECTS IN A SUBCOLLECTION (a subcollection == a collection in a document in a root collection)
    //  THIS FULFILS OP 1.
    //  SCENARIO: USER 1337 wants to retrieve the list of all his active OpenOrder objects that he created prior.
    //  This is done by a read operation on CollectionPath "UserBase/1337/OpenOrderCol".
    /* Outside of onComplete, instantiate the following:
    * - FirebaseFirestore db = FirebaseFirestore.getInstance();
    * Other data that is required:
    * - The userID of this user, so that you know what to put in the CollectionPath to read from. In this example, it's 1337.

    db.collection("UserBase/1337/OpenOrderCol")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        OpenOrder oo = document.toObject(OpenOrder.class);
                        // IN EACH ITERATION IN THIS FORLOOP, YOU HAVE OBTAIN ONE OPENORDER OBJECT FOR YOUR PERUSAL.
                        // YOUR CODE HERE.
                        Log.d("FIRESTORE", document.getId() + " => " + oo.toString());
                        Toast.makeText(SecondScreen.this, oo.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("FIRESTORE", "Error getting documents: ", task.getException());
                }
            }
        });

     */



    // TODO: BATCH WRITE SAME OBJECT TO TWO DIFFERENT DOCUMENTS - IDENTICAL GENERATED ID FOR BOTH DOCUMENTS
    //  THIS FULFILS OP 2.
    //  SCENARIO: USER 2468 raises an OpenOrder obj.
    //  OP 2 TAKES 2 WRITE ACTIONS IN A BATCH:
    //  (1): Write the OpenOrder obj in path "OpenOrder/< generated_id >". This is done because of OP 3 - whereby any user is free to review all available OpenOrders easily.
    //  (2): Write the OpenOrder obj in path "UserBase/2468/OpenOrderCol/< generated_id >". This is done because our database needs to records that 2468 has created this OpenOrder.
    /* Remember to instantiate the following (for the example):
    * - FirebaseFirestore object
    * Other data that is required:
    * - The userID of this user, so that you know what to put in the CollectionPath to read from. In this example, it's 2468.

    OpenOrder myOpenOrder = new OpenOrder(2468, "Janitor Closet", 0.50, new Date());

            // Get a new write batch
            WriteBatch batch = db.batch();

            DocumentReference dref_openorder = db.collection("OpenOrder").document();
            final String gen_id = dref_openorder.getId();
            DocumentReference dref_useropenorder = db.collection("UserBase/2468/OpenOrderCol").document(gen_id);

            batch.set(dref_openorder, myOpenOrder);
            batch.set(dref_useropenorder, myOpenOrder);

            // Commit the batch
            batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(MainLogin.this, "Batch Write Attempt Successful", Toast.LENGTH_SHORT).show();
                    Log.d("FIRESTORE", "Batch Write Operation Completed. Document ID: " + gen_id);
                }
            });

     */



    // TODO: READING A LIST OF CUSTOM OBJECTS IN A COLLECTION
    //  THIS FULFILS OP 3.
    //  SCENARIO: USER enters the app on startup, and wishes to inspect all available OpenOrders.
    //  Assuming that our database has duly recorded all OpenOrder objects written by all food captains into the OpenOrder collection,
    //  then user is able to see list of all available OpenOrders. So that he can choose to enroll if he wish.
    /* Remember to instantiate the following:
    * - FirebaseFirestore object
    * - That's it! You don't have to instantiate anything else because this is purely a read operation that is independent of current user.

    db.collection("OpenOrder")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            OpenOrder oo = document.toObject(OpenOrder.class);
                            Log.d("FIRESTORE", document.getId() + " => " + oo.toString());
                            Toast.makeText(SecondScreen.this, oo.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("FIRESTORE", "Error getting documents: ", task.getException());
                    }
                }
            });

     */


    // TODO: BATCH WRITING TO DIFFERENT DOCUMENT REFERENCES, WITH IDENTICAL GENERATED ID FOR BOTH.
    //  THIS FULFILS OP 4.
    //  SCENARIO: USER 8642 creates an Order - which means he enrols in an OpenOrder, which in this example is created by USER 2468.
    //  OP4 TAKES 2 WRITE ACTIONS THAT IS WRITTEN IN BATCH:
    //  (1): Write Order object to UserBase/8642/JoinedOrders/< generated_id >.
    //  (2): Write Order object to UserBase/2468/OrdersFromOthers< generated_id >.
    /* Remember to instantiate the following:
    * - FirebaseFirestore object
    * - An ArrayList<String> to store the list of food in the order. This will be used as a argument to the Order constructor.
    * Other data that is required:
    * - The userID of this user, so that you know what to put in the CollectionPath to read from. In this example, it's 7531.
    * - The userID of the food captain. Again, same reason. In this example, it is 1337.
    * For Your Information:
    * - Read the Order Class definition to know what kind of parameters are required for instantiation. Like for example, it needs an ArrayList as one of them.
    * - The code snippet below will demonstrate an example of populating the ArrayList with example foods, and then subsequently instantiating an Order object.

    final String current_user_id = "8642";
    final String food_captain_id = "2468";

    food_menu.add("McMeat");
    food_menu.add("Fries(L)");
    food_menu.add("Orange Juice");
    Order my_enrolled_order = new Order(current_user_id, food_menu, 8.50);

    // Get a new write batch
    WriteBatch batch = db.batch();

    DocumentReference dref_joinorders = db.collection("UserBase/" + current_user_id + "/JoinedOrders").document();
    String gen_id = dref_joinorders.getId();
    DocumentReference dref_openorder = db.collection("UserBase/" + food_captain_id + "/OrdersFromOthers").document(gen_id);

    batch.set(dref_joinorders, my_enrolled_order);
    batch.set(dref_openorder, my_enrolled_order);

    // Commit the batch
    batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            Toast.makeText(MainLogin.this, "Successfully enrolled in " + food_captain_id + "'s OpenOrder!", Toast.LENGTH_SHORT).show();
            Log.d("FIRESTORE", "Batch Write Operation Completed. " + current_user_id +
                    " has joined OpenOrder by " + food_captain_id);
        }
    });


     */



    // TODO: READING A LIST OF CUSTOM OBJECTS IN A SUBCOLLECTION (a subcollection == a collection in a document in a root collection)
    //  THIS FULFILS OP 5:
    //  User is able to see all open orders that he joined.
    //  SCENARIO: USER 7531 wishes to know what orders he has joined (what OpenOrders from other food captains that he has enrolled in).
    //  This is done by reading from the Collection: UserBase/7531/JoinedOrders.
    /* Remember to instantiate the following:
     * - FirebaseFirestore object
     * Other data that is required:
     * - The userID of this user, so that you know what to put in the CollectionPath to read from.

    db.collection("UserBase/7531/JoinedOrders")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Order oo = document.toObject(Order.class);
                        Log.d("FIRESTORE", document.getId() + " => " + oo.toString());
                        Toast.makeText(SecondScreen.this, oo.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("FIRESTORE", "Error getting documents: ", task.getException());
                }
            }
        });


     */


    // TODO: OP 6. Updating Progress State.
    //  SCENARIO: USER 2468 has raised an OpenOrder prior. A number of people has enrolled into this order as Object.
    /* Remember to instantiate the following:
     * - FirebaseFirestore object
     * Other data that is required:
     * - The userID of this user, so that you know what to put in the String user_id.
     * - The ID of the OpenOrder generated by this user, as the food captain. An example has been given below.
     * Additional information:
     * - Obtaining the ID of the OpenOrder seems to be the most difficult. I feel like I have to change certain things here, TY.
     * - If you don't see the Logcat on some/all parts, it means that you have inserted an invalid path, and thus FireStore cannot increment.

    final String current_user_id = "2468"; // This is the food captain.
    final String open_order_id = "6XAmYIpeeSxSHN8NE3mN";

    DocumentReference docref = db.document("UserBase/" + current_user_id + "/OpenOrderCol/" + open_order_id);
    docref.update("progress_state", FieldValue.increment(1))
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) { Log.d("FIRESTORE", "UserBase/" + current_user_id + "/OpenOrderCol/" + open_order_id +
                        ": progress_state incremented by 1 successful."); }
            });


    db.collection("UserBase/" + current_user_id + "/OrdersFromOthers")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Order order = document.toObject(Order.class);
                            final String order_id = document.getId();
                            final String orderer_id = order.getUser_id();

                            // === INCREMENT PROGRESS_STATE OF ORDER in OrdersFromOthers BY 1 ============
                            DocumentReference docref = db.document("UserBase/" + current_user_id + "/OrdersFromOthers/" + order_id);
                            docref.update("progress_state", FieldValue.increment(1))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) { Log.d("FIRESTORE", "UserBase/" + current_user_id + "/OrdersFromOthers/" + order_id +
                                                ": progress_state incremented by 1 successful."); }
                                    });

                            // === INCREMENT PROGRESS_STATE OF ORDER in "UserBase/orderer_id/JoinedOrders/order_id" BY 1 =======
                            DocumentReference docref2 = db.document("UserBase/" + orderer_id + "/JoinedOrders/" + order_id);
                            docref2.update("progress_state", FieldValue.increment(1))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) { Log.d("FIRESTORE", "UserBase/" + orderer_id + "/JoinedOrders/" + order_id +
                                                "Incremented by 1 successful."); }
                                    });

                        }
                    } else {
                        Log.d("FIRESTORE", "Error getting documents: ", task.getException());
                    }
                }
            });


     */




    // TODO: NORMAL READ
    /* Outside of OnCreate(), instantiate the following:
    * - FirebaseFirestore object
    * - OpenOrder object


    DocumentReference docRef = db.collection("cities").document("SF");
    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            oorder = documentSnapshot.toObject(OpenOrder.class);
        }
    });

    Toast.makeText(SecondScreen.this, oorder.toString(), Toast.LENGTH_SHORT).show();

     */


    // TODO: NORMAL WRITE TO DOCUMENT REFERENCE - NO GENERATED ID
    /* Remember to instantiate the following:
    * - FirebaseFirestore object
    * - OpenOrder oorder1 = new OpenOrder(1234, "Blk 59", 1.80, new Date());
    * - DocumentReference docref = db.document("/UserBase/1337/OpenOrderCol/OpenOrder1");

    Toast.makeText(MainLogin.this, "Write Attempt", Toast.LENGTH_SHORT).show();
    docref.set(oorder1);

     */


    // TODO: INCREMENT A FIELD IN A SPECIFIED DOCUMENT BY 1
    /*

    DocumentReference docref = db.document("test1/NYC");
                docref.update("cost", FieldValue.increment(1))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("FIRESTORE", "Incremented by 1 successful.");
                        }
                        });

     */


    // TODO: WRITE OBJECT TO TWO DIFFERENT DOCUMENTS - GENERATED ID FOR BOTH DOCUMENTS
    //  THIS (USED TO) FULFILS OP 2. THIS IMPLEMENTATION HAS BEEN REPLACED BY NEWER IMPLEMENTATION.
    //  SCENARIO: USER 9988 has raised an OpenOrder obj.
    //  OP 2 TAKES 2 WRITE ACTIONS IN SEQUENCE:
    //  (1): Write the OpenOrder obj in path "OpenOrder". This is done because of OP 3 - whereby any user is free to review all available OpenOrders easily.
    //  (2): Write the OpenOrder obj in path "UserBase/9988/OpenOrderCol". This is done because our database needs to records that 9987 has created this OpenOrder.
    /* Remember to instantiate the following (for the example):
    * - FirebaseFirestore object
    * - OpenOrder oorder1 = new OpenOrder(9988, "Laundry Room", 1.50, new Date());
    * Other data that is required:
    * - The userID of this user, so that you know what to put in the CollectionPath to read from. In this example, it's 9988.

                db.collection("OpenOrder")
                        .add(oorder1)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("FIRESTORE", "In Collection OpenOrder: DocumentSnapshot written with ID: " + documentReference.getId());
                                Toast.makeText(MainLogin.this, "Write Attempt Successful.", Toast.LENGTH_SHORT).show();
                            }
                        });

                db.collection("UserBase/9988/OpenOrderCol")
                        .add(oorder1)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("FIRESTORE", "In UserBase/user_id/OpenOrderCol: DocumentSnapshot written with ID: " + documentReference.getId());
                                Toast.makeText(MainLogin.this, "Write Attempt Successful.", Toast.LENGTH_SHORT).show();
                            }
                        });


     */


    // TODO: WRITE OBJECT TO TWO DIFFERENT DOCUMENTS - GENERATED DISTINCT ID FOR EACH OF THE 2 DOCUMENTS
    //  THIS (USED TO) FULFILS OP 4. BUT THIS IMPLEMENTATION HAS BEEN SUPERCEDED. DON'T USE THIS EXCEPT AS A REFERENCE.
    //  SCENARIO: USER 7531 enrols in an OpenOrder created by USER 1337.
    //  OP 4 TAKES 2 WRITE ACTIONS IN SEQUENCE:
    //  (1): Write Order object to UserBase/7531/JoinedOrders.
    //  (2): Write Order object to UserBase/1337/OrdersFromOthers.
    //  *** Note that Order Class != OpenOrder Class ***
    /* Remember to instantiate the following:
    * - FirebaseFirestore object
    * - An ArrayList<String> to store the list of food in the order. This will be used as a argument to the Order constructor.
    * Other data that is required:
    * - The userID of this user, so that you know what to put in the CollectionPath to read from. In this example, it's 7531.
    * - The userID of the food captain. Again, same reason. In this example, it is 1337.
    * For Your Information:
    * - Read the Order Class definition to know what kind of parameters are required for instantiation. Like for example, it needs an ArrayList as one of them.
    * - The code snippet below will demonstrate an example of populating the ArrayList with example foods, and then subsequently instantiating an Order object.

    food_menu.add("McChicken");
    food_menu.add("Fries(L)");
    food_menu.add("Coke(L)");
    Order my_enrolled_order = new Order("7531", food_menu, 10.50);

    db.collection("UserBase/7531/JoinedOrders")
            .add(my_enrolled_order)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("FIRESTORE", "In Collection UserBase/7531/JoinedOrders: DocumentSnapshot written with ID: " + documentReference.getId());
                    Toast.makeText(MainLogin.this, "Write Attempt Successful.", Toast.LENGTH_SHORT).show();
                }
            });

    db.collection("UserBase/1337/OrdersFromOthers")
            .add(my_enrolled_order)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("FIRESTORE", "In Collection UserBase/1337/OrdersFromOthers: DocumentSnapshot written with ID: " + documentReference.getId());
                    Toast.makeText(MainLogin.this, "Write Attempt Successful.", Toast.LENGTH_SHORT).show();
                }
            });

     */

    // TODO: NORMAL WRITE TO COLLECTION REFERENCE - GENERATED ID FOR DOCUMENT
    /* Remember to instantiate the following:
    * - FirebaseFirestore object
    * - OpenOrder oorder1 = new OpenOrder(9988, "Laundry Room", 1.50, new Date());

    db.collection("OpenOrder")
            .add(oorder1)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("FIRESTORE", "DocumentSnapshot written with ID: " + documentReference.getId());
                    Toast.makeText(MainLogin.this, "Write Attempt Successful.", Toast.LENGTH_SHORT).show();
                }
            });

     */

    // TODO: USING CALLBACKS - OBSOLETE. IGNORE.
    /*

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);

        nav_button = findViewById(R.id.second_screen_nav);
        nav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondScreen.this, MainLogin.class));
            }
        });

        read_button = findViewById(R.id.second_screen_read);
        read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readData(new MyCallback() {
                    @Override
                    public void onCallback(UserBase userdata) {
                        Log.d("FIREBASE", "DocumentSnapshot data: " + userdata.toString());
                    }
                });


            }
        });

    }

    public void readData(final MyCallback myCallback) {
        DocumentReference docRef = db.collection("UserBase").document(user_id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user_data = documentSnapshot.toObject(UserBase.class);
                myCallback.onCallback(user_data);
            }
        });
    }

    public interface MyCallback {
        void onCallback(UserBase userdata);
    }

     */







}
