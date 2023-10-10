package com.example.discoverring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity
{
    TextInputEditText editTextEmail, editTextPassword, editTextConfirmPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    FirebaseUser firebaseUser;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usersRef = dbRef.child("Users");

    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confirm_password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                progressBar.setVisibility(View.VISIBLE);

                String email, password, confirmPassword;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                confirmPassword = String.valueOf(editTextConfirmPassword.getText());

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Register.this, "Enter E-mail", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(confirmPassword))
                {
                    Toast.makeText(Register.this, "Enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!TextUtils.equals(password, confirmPassword))
                {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // checking if email already exists in the db
                dbRef.child("Users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if(snapshot.exists())
                            Toast.makeText(Register.this, "E-mail already exists", Toast.LENGTH_SHORT).show();

                        else
                        {
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful())
                                    {
                                        // store user in db
                                        firebaseUser = mAuth.getCurrentUser();
                                        saveUserDataToDatabase(firebaseUser.getUid(), email, password);

                                        Toast.makeText(Register.this, "Account created!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else
                                    {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {}
                });
            }
        });
    }

    private void saveUserDataToDatabase(String uid, String email, String password)
    {
        usersRef = dbRef.child("Users").child(uid);

        User user = new User(email, password, uid);

        usersRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                    Toast.makeText(Register.this, "User Registered Successfully!", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(Register.this, "Not able to create an account!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}