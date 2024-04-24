package com.test.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.app.R;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginButton = findViewById(R.id.buttonGotoLogin);
        Button registerButton = findViewById(R.id.buttonRegister);

        FirebaseAuth fbAuth = FirebaseAuth.getInstance();

//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if(currentUser != null)
//        {
//            AllPokemonFragment allPokemonFragment = new AllPokemonFragment();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerView, allPokemonFragment)
//                    .commit();
//        }

        loginButton.setOnClickListener(v ->
        {
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(loginActivityIntent);
        });

        registerButton.setOnClickListener(v ->
        {
            EditText emailEditText = findViewById(R.id.editTextEmailAddress);
            EditText passwordEditText = findViewById(R.id.editTextPassword);
            EditText confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if(!password.equals(confirmPassword))
            {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            fbAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Log.d("User manager", "successfully signed up user");
                                FirebaseUser user = fbAuth.getCurrentUser();

                                Intent mainActivityIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(mainActivityIntent);
                            }
                            else
                            {
                                Log.w("User manager", "failed to sign up user", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}
