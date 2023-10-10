package com.example.discoverring;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MyProfile extends AppCompatActivity
{
    TextInputEditText fullName, email, contactNum, password, confirmPassword;
    Button updateButton;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = firebaseDatabase.getReference("Users");
    DatabaseReference userRef;
    TextView fullNameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        fullName = findViewById(R.id.profile_full_name);
        email = findViewById(R.id.profile_email);
        contactNum = findViewById(R.id.profile_contact_num);
        password = findViewById(R.id.profile_password);
        confirmPassword = findViewById(R.id.profile_confirm_password);
        updateButton = findViewById(R.id.update_profile_btn);
        fullNameDisplay = findViewById(R.id.profileFullNameTV);
//        getData();


        updateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String full_name = String.valueOf(fullName.getText());
                String e_mail = String.valueOf(email.getText());
                String contact_num = String.valueOf(contactNum.getText());
                String passwd = String.valueOf(password.getText());
                String confirm_passwd = String.valueOf(confirmPassword.getText());
                String uid = firebaseUser.getUid();
                userRef = dbRef.child(uid);

                Map<String, Object> updateUserData = new HashMap<>();

                if (!TextUtils.isEmpty(full_name))
                {
                    updateUserData.put("fullName", full_name);
                }

                if (!TextUtils.isEmpty(e_mail))
                {
                    updateUserData.put("email", e_mail);
                }

                if (!TextUtils.isEmpty(full_name))
                {
                    updateUserData.put("fullName", full_name);
                }

                if (!TextUtils.isEmpty(contact_num))
                {
                    updateUserData.put("contactNumber", contact_num);
                }

                if ((!TextUtils.isEmpty(passwd)) && (!TextUtils.isEmpty(confirm_passwd)))
                {
                    if(TextUtils.equals(passwd, confirm_passwd))
                    {
                        updateUserData.put("password", passwd);
                    }

                    else
                    {
                        Toast.makeText(MyProfile.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }

                userRef.updateChildren(updateUserData, new DatabaseReference.CompletionListener()
                {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref)
                    {
                        if (error == null)
                            Toast.makeText(MyProfile.this, "Data updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
