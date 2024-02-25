package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    TextView btnCreateAccnt;
    EditText userName, userPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 //       txt = findViewById(R.id.txt);

        btnCreateAccnt = findViewById(R.id.btnCreateAccnt);
        btnLogin = findViewById(R.id.btnLogin);
        userName = findViewById(R.id.editTxtUserName);
        userPassword = findViewById(R.id.editTxtPassword);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(this);
        btnCreateAccnt.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
     //   txtWelcome = findViewById(R.id.txt);

    }

    public void onClick(View v){
        progressBar.setVisibility(v.VISIBLE);
        if(v.getId() == R.id.btnCreateAccnt){
            registerInterface();
        }else if(v.getId() == R.id.btnLogin){
            toLogin(v);
        }
    }

    public void toLogin(View v){

        String email = String.valueOf(userName.getText());
        String password = String.valueOf(userPassword.getText());

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            progressBar.setVisibility(v.GONE);
            Toast.makeText(MainActivity.this, "User name and Password is empty", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            progressBar.setVisibility(v.GONE);
            Toast.makeText(MainActivity.this, "Please input your user name", Toast.LENGTH_LONG).show();
        }else if(!TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            progressBar.setVisibility(v.GONE);
            Toast.makeText(MainActivity.this, "Please input your password", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this, "You are about to login", Toast.LENGTH_LONG).show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressBar.setVisibility(v.GONE);
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent toAccount = new Intent(getApplicationContext(), Account.class);
                                startActivity(toAccount);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.setVisibility(v.GONE);
                                Toast.makeText(MainActivity.this, "Username or Password not Found!.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
    public void registerInterface(){
        Intent toRegister = new Intent(this, RegisterInterface.class);
        startActivity(toRegister);
        finish();
    }

    public void accountInterface(){

    }

}