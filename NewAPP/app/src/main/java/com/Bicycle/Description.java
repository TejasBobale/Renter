package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Description extends AppCompatActivity {

    TextView Name,Price,Details;

    Button Booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Name = findViewById(R.id.bike_name);
        Price = findViewById(R.id.bike_price);
        Details = findViewById(R.id.bike_details);
        Booking = findViewById(R.id.booking_0);

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_price = intent.getStringExtra("price");
        String user_details = intent.getStringExtra("details");

        Name.setText(user_name);
        Price.setText(user_price);
        Details.setText(user_details);

        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameFromDB = user_name;
                String priceFromDB = user_price;

                Intent intent = new Intent(getApplicationContext(), Booking.class);

                intent.putExtra("name", nameFromDB);
                intent.putExtra("price", priceFromDB);

                startActivity(intent);
            }
        });
    }
}