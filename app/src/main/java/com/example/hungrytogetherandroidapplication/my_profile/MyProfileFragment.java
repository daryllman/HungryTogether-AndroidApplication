package com.example.hungrytogetherandroidapplication.my_profile;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hungrytogetherandroidapplication.R;
import com.example.hungrytogetherandroidapplication.login_portal.AccountDetailsActivity;
import com.example.hungrytogetherandroidapplication.login_portal.MainLogin;
import com.example.hungrytogetherandroidapplication.main_activity_portal.MainActivity;
import com.example.hungrytogetherandroidapplication.new_order_portal.NewOrderActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    GoogleSignInClient mGoogleSignInClient;
    TextView nameField;
    TextView emailField;
    TextView idField;
    ImageView photoField;
    TextView fbUid;

    Button sign_out;


    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        // Initialise all the variables
        nameField = fragmentView.findViewById(R.id.test_name);
        emailField = fragmentView.findViewById(R.id.test_email);
        photoField = fragmentView.findViewById(R.id.test_photo);
        sign_out = fragmentView.findViewById(R.id.log_out);


        // Configure sign-in to request the user's ID, email address, and basic
        // profile, ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        // Initialise object for GoogleSignInAccount class
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());

        // If there is an account, save the account details in variables below
        if (acct != null) {
            String personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
//            String personId = acct.getId();
            Uri personPhotoURL = acct.getPhotoUrl(); //URL that contains the photo file of account
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            // Show the account details on this page~

            nameField.setText(personName);
            emailField.setText(personEmail);
//            idField.setText("ID: "+ personId);
//            fbUid.setText("fb UID:" + user.getUid());



            // Glide is a module to load and cache images (read the build.gradle file for more info that i added)
            Glide
                    .with(this)
                    .load(personPhotoURL).into(photoField); //load photo from url(personPhotoURL) into the photoField
        }


        // Create Listener for sign out button
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "sign out", Toast.LENGTH_SHORT).show();
            }
        });

        return fragmentView;
    }

//    private void signOut() {
//
//        mGoogleSignInClient.signOut() // use google sign in client to sign out officially...
//                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() { // Adds a listener that is called when the Task completes. //The listener will be called on main application thread. If the Task is already complete, a call to the listener will be immediately scheduled. If multiple listeners are added, they will be called in the order in which they were added.
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) { // Execute this onComplete() func when listener (OnCompleteListener) listens that task is completed // Note: task is never null as the task here is a completed task
//                        Toast.makeText(getActivity(), "You have signed out successfully", Toast.LENGTH_SHORT).show();
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(new Intent(getActivity(), MainLogin.class)); // Jump to main login screen once sign out is successfull
//                        finish();
//                    }
//                });


    }

