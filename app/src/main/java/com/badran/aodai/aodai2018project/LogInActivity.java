package com.badran.aodai.aodai2018project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ETMail;
    private EditText ETPassw;
    private Button Blogin;
    private Button Bregister;

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    //Google
    private SignInButton GoogleButton;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private static final String TAG = "MAIN_ACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ETMail=(EditText)findViewById(R.id.ETMail);
        ETPassw=(EditText)findViewById(R.id.ETPassw);
        Blogin=(Button)findViewById(R.id.Blogin);
        Bregister=(Button)findViewById(R.id.Bregister);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        Blogin.setOnClickListener(this);
        Bregister.setOnClickListener(this);

        //

        GoogleButton=(SignInButton)findViewById(R.id.GoogleButton);
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(LogInActivity.this,MapsActivity.class));
                }

            }
        };

        //
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(LogInActivity.this, "You got an Error", Toast.LENGTH_SHORT).show();

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        GoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn1();
            }
        });
    }

    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }


    private void signIn1() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result  = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }else{
                //
                //
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithCredential:success"+task.isSuccessful());
                        if (!task.isSuccessful()){
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

                    private void signIn(String email, String passw) {
                        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LogInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LogInActivity.this, MapsActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LogInActivity.this, "signIn failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    task.getException().printStackTrace();

                                }

                            }
                        });
                    }

    private void dataHandler() {
        String email = ETMail.getText().toString();
        String passw = ETPassw.getText().toString();

        signIn(email,passw);
    }


    @Override
    public void onClick(View view) {
        if (view==Bregister){
            Intent i = new Intent(this,RegisterActivity.class);
            startActivity(i);
        }

        if (view==Blogin){
            dataHandler();
        }

    }
}

