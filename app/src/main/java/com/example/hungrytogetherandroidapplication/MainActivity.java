package com.example.hungrytogetherandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //Refer signInButton variable to the sign_in_button
        signInButton = findViewById(R.id.sign_in_button);

        // SIGN IN BUTTON TO BE REPLACED WITH CUSTOM ONE.
        // The standard sign in button given by google doesn't have a proper set text attribute. need to create custom buttons.
        // This is a make-shift hacky way..
        TextView textView = (TextView) signInButton.getChildAt(0);
        textView.setText("Sign in with Google");

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) // default builder "DEFAULT_SIGN_IN"  that contains only basic info that we need - according to our app usage
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
            handleSignInResult(task); // pass task to this handleSignInResult() - which will check if user really signed in
        }
    }

    // To Check if user really successfully signed in
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            /*
            // testing
            String personName = account.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = account.getEmail();
            String personId = acct.getId();
            Uri personPhotoURL = acct.getPhotoUrl(); //URL that contains the photo file of account
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
            */







            Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_LONG).show();

            // If Sign In is SUCCESSFUL, go to the next page (authenticated screen) - which is my AccountDetails UI
            startActivity(new Intent(MainActivity.this, AccountDetailsActivity.class));

        } catch (ApiException e) { // Catch exception if sign in failed for any reason...

            // The ApiException status code helps to indicate the detailed failure reason
            // Refer to the GoogleSignInStatusCodes class reference for more information (if needed)
            Log.i("Google Sign In Error", "signInResult:failed code = " + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Sign in failed, please try again", Toast.LENGTH_LONG).show(); // Show error message on screen!
        }
    }

    @Override // At Start screen, must check if user has already signed in - affects which page UI is shown.
    protected void onStart() {

        // Check for existing Google Sign In account - if the user is already signed in
        // the GoogleSignInAccount will not be null if already signed in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(MainActivity.this, AccountDetailsActivity.class)); // if already signed in, jump straight to next page.
        }
        super.onStart();

        /*
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, AccountDetailsActivity.class)); // if already signed in, jump straight to next page.
        }
        */

    }
}