package com.example.hungrytogetherandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AccountDetailsActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;
    TextView nameField;
    TextView emailField;
    TextView idField;
    ImageView photoField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        // Initialise all the variables
        sign_out = findViewById(R.id.log_out);
        nameField = findViewById(R.id.name);
        emailField = findViewById(R.id.email);
        idField = findViewById(R.id.id);
        photoField = findViewById(R.id.photo);


        // Configure sign-in to request the user's ID, email address, and basic
        // profile, ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialise object for GoogleSignInAccount class
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(AccountDetailsActivity.this);

        // If there is an account, save the account details in variables below
        if (acct != null) {
            String personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhotoURL = acct.getPhotoUrl(); //URL that contains the photo file of account

            // Show the account details on this page~
            nameField.setText("Name: "+ personName);
            emailField.setText("Email: "+ personEmail);
            idField.setText("ID: "+ personId);

            // Glide is a module to load and cache images (read the build.gradle file for more info that i added)
            Glide
                    .with(this)
                    .load(personPhotoURL).into(photoField); //load photo from url(personPhotoURL) into the photoField
        }

        // Create Listener for sign out button
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut() // use google sign in client to sign out officially...
                .addOnCompleteListener(this, new OnCompleteListener<Void>() { // Adds a listener that is called when the Task completes. //The listener will be called on main application thread. If the Task is already complete, a call to the listener will be immediately scheduled. If multiple listeners are added, they will be called in the order in which they were added.
                    @Override
                    public void onComplete(@NonNull Task<Void> task) { // Execute this onComplete() func when listener (OnCompleteListener) listens that task is completed // Note: task is never null as the task here is a completed task
                        Toast.makeText(AccountDetailsActivity.this,"You have signed out successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AccountDetailsActivity.this, MainActivity.class)); // Jump to main login screen once sign out is successfull
                        finish();
                    }
                });
    }
}