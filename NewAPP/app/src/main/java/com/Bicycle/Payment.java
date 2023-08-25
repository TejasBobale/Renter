package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {

    TextView Pay;
    AppCompatButton Btn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Pay = findViewById(R.id.pay);
        Btn = findViewById(R.id.pay_btn);

        reference = FirebaseDatabase.getInstance().getReference().child("App/Admin/User/Tron/record");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String time = snapshot.getValue().toString();
                Pay.setText(time);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");
                reference.child("Tron").child("record").setValue("0");
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });


    }
}