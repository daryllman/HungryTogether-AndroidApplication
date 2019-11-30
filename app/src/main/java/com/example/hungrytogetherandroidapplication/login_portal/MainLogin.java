package com.example.hungrytogetherandroidapplication.login_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hungrytogetherandroidapplication.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainLogin extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    // SignInButton signInButton;
    Button signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        // Initialise Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Refer signInButton variable to the sign_in_button
        signInButton = findViewById(R.id.sign_in_button);


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) // default builder "DEFAULT_SIGN_IN"  that contains only basic info that we need - according to our app usage
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso - gso is the context of this activity
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Add Listener to the sign in button in login page
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(); // call "signIn" method if button clicked
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN); // intent: " signInIntent"    constant: "RC_SIGN_IN"  - RC_SIGNIN is declared as 0 at the top
    }

    @Override // when user interacts with activity, it will return  a result as another Intent object - receives it in this onActivityResult() callback
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data); // create a Task which will gather all the details of user

            try{
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account); // pass task to this handleSignInResult() - which will check if user really signed in
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.i("Google Sign In API Fail", "Google sign in failed", e);

            }

        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.i("firebaseAuthWithGoogle", "firebaseAuthWithGoogle: (ID is) " + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Sign in with credential", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainLogin.this, "Welcome!", Toast.LENGTH_LONG).show();
                            // If Sign In is SUCCESSFUL, go to the next page (authenticated screen) - which is my AccountDetails UI
                            startActivity(new Intent(MainLogin.this, AccountDetailsActivity.class)); // if already signed in, jump straight to next page.
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Sign in with credential", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainLogin.this, "Sign in failed, please try again", Toast.LENGTH_LONG).show(); // Show error message on screen!
                        }

                        // ...
                    }
                });
    }


    @Override // At Start screen, must check if user has already signed in - affects which page UI is shown.
    protected void onStart() {

        // Check for existing Google Sign In account - if the user is already signed in
        // the GoogleSignInAccount will not be null if already signed in

        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(MainLogin.this, AccountDetailsActivity.class)); // if already signed in, jump straight to next page.
        }
    }

}