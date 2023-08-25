package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    TextInputLayout Username,Password;
    AppCompatButton Loginbtn;
    TextView Signupbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.log_username);
        Password = findViewById(R.id.log_pass);
        Loginbtn = findViewById(R.id.loginbtn);
        Signupbtn = findViewById(R.id.signupbtn);


        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }

    private Boolean validateUsername() {

        String val = Username.getEditText().getText().toString();
        if (val.isEmpty()) {
            Username.setError("Field cannot be empty");
            return false;
        } else {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {

        String val = Password.getEditText().getText().toString();
        if (val.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){

        if(!validateUsername() | !validatePassword()){
            return;
        } else if (Username.getEditText().getText().toString().equals("admin") && Password.getEditText().getText().toString().equals("admin")){
            Toast.makeText(Login.this,"Login successfull !",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Admindashboard.class));
        } else {
            isUser();
        }

    }

    private void isUser() {

        String userEnteredUsername = Username.getEditText().getText().toString().trim();
        String userEnteredPassword = Password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        Username.setError(null);
                        Username.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneFromDB = snapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phone",phoneFromDB);
                        intent.putExtra("password",passwordFromDB);

                        Toast.makeText(Login.this,"Login successfull !",Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                    else{
                        Password.setError("Wrong Password");
                        Password.requestFocus();
                    }
                }
                else {
                    Username.setError("No such user exists");
                    Username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}