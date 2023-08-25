package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogRecord;

public class Booking extends AppCompatActivity {

    TextView StopWatch, Name, Price;
    AppCompatButton Start_Ride;
    int seconds = 0;
    DatabaseReference reference;
    boolean isRunning;
    public static final String VALUE = "value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Start_Ride = findViewById(R.id.start_ride);
        Name = findViewById(R.id.bikeno);
        Price = findViewById(R.id.bikePrice);
        StopWatch = findViewById(R.id.timer);

        Intent intent = getIntent();

        String bike_name = intent.getStringExtra("name");
        String bike_price = intent.getStringExtra("price");
        String username = intent.getStringExtra("username");

        Name.setText(bike_name);
        Price.setText(bike_price);

        Start_Ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean myBooleanVariable = true;

                Intent intent = new Intent(getApplicationContext(), Currentride.class);

                intent.putExtra("my_boolean_key", myBooleanVariable);
                intent.putExtra("username", username);

                reference = FirebaseDatabase.getInstance().getReference("App/Admin/Bicycle");
                reference.child(username).child("use").setValue(1);
                startActivity(intent);
            }
        });
    }
}