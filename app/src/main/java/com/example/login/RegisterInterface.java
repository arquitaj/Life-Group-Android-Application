package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterInterface extends AppCompatActivity implements View.OnClickListener{
    EditText fullName, userName, userPassword;
    Button btnCreate;

    FirebaseAuth mAuth;
    ProgressBar progressBar;
    RadioGroup radioSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_interface);

        fullName = findViewById(R.id.editTxtName);
        userName = findViewById(R.id.editTxtEmail);
        userPassword = findViewById(R.id.editTxtPassword);
        btnCreate = findViewById(R.id.btnCreate);
        radioSex = findViewById(R.id.radioSex);
        progressBar = findViewById(R.id.progressSubmit);

        mAuth = FirebaseAuth.getInstance();
        btnCreate.setOnClickListener(this);
    }

    public void onClick(View v){
        progressBar.setVisibility(v.VISIBLE);
        String name = String.valueOf(fullName.getText());
        String email = String.valueOf(userName.getText());
        String password = String.valueOf(userPassword.getText());

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            progressBar.setVisibility(v.GONE);
            Toast.makeText(RegisterInterface.this, "Please complete the form", Toast.LENGTH_LONG).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressBar.setVisibility(v.GONE);
                                Toast.makeText(RegisterInterface.this, "Account Created", Toast.LENGTH_SHORT).show();

                                Intent toAccount = new Intent(getApplicationContext(), Account.class);
                                startActivity(toAccount);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.setVisibility(v.GONE);
                                Toast.makeText(RegisterInterface.this, "Invalid email address", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}