package com.ishwar_arcore.pacebook.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ishwar_arcore.pacebook.R;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private Button registerUser;

    private EditText editTextFullName, editTextAge, editTextEmail,editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        registerUser=(Button) findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(this);
        editTextFullName=(EditText) findViewById(R.id.etName);
        editTextAge=(EditText) findViewById(R.id.etAge);
        editTextEmail=(EditText)findViewById(R.id.etEmail);
        editTextPassword=(EditText)findViewById(R.id.etPassword);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String fullName=editTextFullName.getText().toString().trim();
        String age=editTextAge.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is Required");
            editTextFullName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Authentication Authentication=new Authentication(fullName,age,email);

                            FirebaseDatabase.getInstance().getReference("Authentication")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(Authentication).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(RegisterActivity.this,"Failed to register! Try again",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });


                        }else {
                            Toast.makeText(RegisterActivity.this,"Failed to register!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}