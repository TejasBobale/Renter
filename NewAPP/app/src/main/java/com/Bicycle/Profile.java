package com.Bicycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Profile extends AppCompatActivity {

    TextInputLayout Fullname, Email, Phone, Password;
    TextView FullNameLabel, UsernameLabel;

    String user_username, user_name, user_email, user_phone, user_password;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Fullname = findViewById(R.id.fullname_profile);
        Email = findViewById(R.id.email_profile);
        Phone = findViewById(R.id.phone_profile);
        Password = findViewById(R.id.password_profile);
        FullNameLabel = findViewById(R.id.name_profile);
        UsernameLabel = findViewById(R.id.username_profile);

        reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");

        showAllUserData();

    }

    private void showAllUserData() {

        Intent intent = getIntent();
        user_username = intent.getStringExtra("username");
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("email");
        user_phone = intent.getStringExtra("phone");
        user_password = intent.getStringExtra("password");

        FullNameLabel.setText(user_name);
        UsernameLabel.setText(user_username);
        Fullname.getEditText().setText(user_name);
        Email.getEditText().setText(user_email);
        Phone.getEditText().setText(user_phone);
        Password.getEditText().setText(user_password);

        reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");

    }

    public void update(View view) {
        if (isNameChngd() | isPassChngd() | isEmailChngd() | isPhoneChngd()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_LONG).show();
    }

    private boolean isPassChngd() {

        if (!user_password.equals(Password.getEditText().getText().toString())) {
            reference.child(user_username).child("password").setValue(Password.getEditText().getText().toString());
            user_password = Password.getEditText().getText().toString();
            return true;

        } else
            return false;

    }

    private boolean isEmailChngd() {

        if (!user_email.equals(Email.getEditText().getText().toString())) {
            reference.child(user_username).child("email").setValue(Email.getEditText().getText().toString());
            user_email = Email.getEditText().getText().toString();
            return true;

        } else
            return false;

    }

    private boolean isNameChngd() {

        if (!user_name.equals(Fullname.getEditText().getText().toString())) {
            reference.child(user_username).child("name").setValue(Fullname.getEditText().getText().toString());
            user_name = Fullname.getEditText().getText().toString();
            return true;

        } else
            return false;

    }

    private boolean isPhoneChngd() {

        if (!user_phone.equals(Phone.getEditText().getText().toString())) {
            reference.child(user_username).child("phone").setValue(Phone.getEditText().getText().toString());
            user_phone = Phone.getEditText().getText().toString();
            return true;

        } else
            return false;

    }
}