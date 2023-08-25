package com.Bicycle;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextInputLayout inputFullname, inputEmail, inputPassword, inputPhone, inputUsername;
    AppCompatButton Signupbtn;
    TextView Signinbtn;
    ProgressBar progressBar;

    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputPhone = findViewById(R.id.reg_phone);
        inputUsername = findViewById(R.id.reg_username);
        inputEmail = findViewById(R.id.reg_email);
        inputPassword = findViewById(R.id.reg_password);
        Signupbtn = findViewById(R.id.registerbtn);
        Signinbtn = findViewById(R.id.signinbtn);
        inputFullname = findViewById(R.id.reg_fullname);
        progressBar = findViewById(R.id.progressbar);

        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("App/Admin/User");


        Signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }

    private Boolean validateName() {

        String val = inputFullname.getEditText().getText().toString();
        if (val.isEmpty()) {
            inputFullname.setError("Field cannot be empty");
            return false;
        } else {
            inputFullname.setError(null);
            inputFullname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {

        String val = inputUsername.getEditText().getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");

        if (val.isEmpty()) {
            inputUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 10) {
            inputUsername.setError("Username too long");
            return false;
        } else {
            inputUsername.setError(null);
            inputUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone() {

        String val = inputPhone.getEditText().getText().toString();
        if (val.isEmpty()) {
            inputPhone.setError("Field cannot be empty");
            return false;
        } else {
            inputPhone.setError(null);
            inputPhone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {

        String val = inputEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            inputEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            inputEmail.setError("Invalid Email");
            return false;
        } else {
            inputEmail.setError(null);
            inputEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {

        String val = inputPassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            inputPassword.setError("Field cannot be empty");
            return false;
        } else if (val.length() < 4){
            inputPassword.setError("Password should be of minimum 8 characters");
            return false;
        } else {
            inputPassword.setError(null);
            inputPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {

        if(!validateName() | !validateEmail() | !validatePhone() | !validateUsername() | !validatePassword()){
            return;
        }

        String fullname = inputFullname.getEditText().getText().toString();
        String username = inputUsername.getEditText().getText().toString();
        String email = inputEmail.getEditText().getText().toString();
        String phone = inputPhone.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();

        String record = "0";


        UserHelperClass helperClass = new UserHelperClass(fullname, username, email, password, phone, record);
        reference.child(username).setValue(helperClass);

        Toast.makeText(Register.this,"Registration successfull !",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Register.this, Login.class));


    }
}