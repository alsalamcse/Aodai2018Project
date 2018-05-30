package com.badran.aodai.aodai2018project;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ETMail;
    private EditText ETPassw;
    private EditText ETPassw2;
    private Button Bnewacc;

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ETMail=(EditText)findViewById(R.id.ETMail);
        ETPassw=(EditText)findViewById(R.id.ETPassw);
        ETPassw2=(EditText)findViewById(R.id.ETPassw2);
        Bnewacc=(Button)findViewById(R.id.Bnewacc);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        Bnewacc.setOnClickListener(this);

    }

    private void dataHandler() {
        String email = ETMail.getText().toString();
        String passw = ETPassw.getText().toString();
        String passw2=ETPassw2.getText().toString();

        createAccount(email,passw);
    }

    private void createAccount(String email, String passw ) {
        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (Bnewacc==view){
            dataHandler();

        }

    }
}
