package com.test.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.app.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth fbAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fbAuth.getCurrentUser();

        Button registerButton = findViewById(R.id.buttonGotoRegister);
        Button loginButton = findViewById(R.id.buttonLogin);

        if(currentUser != null)
            gotoMainActivity();

        registerButton.setOnClickListener(v ->
        {
            Intent registerActivityIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerActivityIntent);
        });

        loginButton.setOnClickListener(v ->
        {
            EditText emailEditText = findViewById(R.id.editTextEmailAddress);
            EditText passwordEditText = findViewById(R.id.editTextPassword);

            fbAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Log.d("User manager", "successfully signed in user");
                                FirebaseUser user = fbAuth.getCurrentUser();
                                gotoMainActivity();
                            }
                            else
                            {
                                Log.w("User manager", "failed to sign in user", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    private void gotoMainActivity()
    {
        Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
