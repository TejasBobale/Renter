package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;


public class Currentride extends AppCompatActivity {

    TextView StopWatch;
    AppCompatButton End_Ride;
    int seconds = 0;
    DatabaseReference reference;
    boolean isRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentride);

        End_Ride = findViewById(R.id.end_ride);
        StopWatch = findViewById(R.id.timer);

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");

        Bundle bundle = getIntent().getExtras();
        isRunning = bundle.getBoolean("my_boolean_key");

        if (isRunning)
            startTimer();

        End_Ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("App/Admin/Bicycle");
                reference.child(username).child("use").setValue(0);
                isRunning = false;
                startActivity(new Intent(getApplicationContext(), Payment.class));
            }
        });

    }

    private void startTimer() {

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int s = seconds % 60;
                int m = seconds / 60;
                int h = m / 60;

                if (isRunning)
                    seconds++;
                String formatString = String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s);
                StopWatch.setText(formatString);
                handler.postDelayed(this, 1000);
                reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");
                reference.child("Tron").child("record").setValue(formatString);
            }
        };
        handler.post(runnable);
    }

}