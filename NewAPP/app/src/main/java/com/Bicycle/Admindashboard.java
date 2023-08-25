package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admindashboard extends AppCompatActivity {

    ImageButton Booking, Profile, CurrentRide, History, Add_bike;
    TextView Name_Label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);


        Booking = findViewById(R.id.book_btn);
        Profile = findViewById(R.id.profile_btn);
        CurrentRide = findViewById(R.id.current_ride_btn);
        History = findViewById(R.id.history_btn);
        Add_bike = findViewById(R.id.add_bike);

        Add_bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Add_bicycle.class));
            }
        });

        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookData();
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profileData();

            }
        });

        CurrentRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Currentride.class));
            }
        });

        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),History.class));
            }
        });
    }

    private void profileData() {
        String userEnteredName_Label =  "Tron";

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("App/Admin/User");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredName_Label);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String nameFromDB = snapshot.child(userEnteredName_Label).child("name").getValue(String.class);
                    String usernameFromDB = snapshot.child(userEnteredName_Label).child("username").getValue(String.class);
                    String phoneFromDB = snapshot.child(userEnteredName_Label).child("phone").getValue(String.class);
                    String emailFromDB = snapshot.child(userEnteredName_Label).child("email").getValue(String.class);
                    String passwordFromDB = snapshot.child(userEnteredName_Label).child("password").getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(),Profile.class);

                    intent.putExtra("name",nameFromDB);
                    intent.putExtra("username",usernameFromDB);
                    intent.putExtra("email",emailFromDB);
                    intent.putExtra("phone",phoneFromDB);
                    intent.putExtra("password",passwordFromDB);

                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void bookData() {
        String name =  "Bicycle";

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("App/Admin");
        Query checkUser = reference.orderByChild("username").equalTo(name);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String nameBike1 = snapshot.child("Bicycle").child("Bicycle1").child("name1").getValue(String.class);
                    String nameBike2 = snapshot.child("Bicycle").child("Bicycle2").child("name2").getValue(String.class);
                    String nameBike3 = snapshot.child("Bicycle").child("Bicycle3").child("name3").getValue(String.class);
                    String nameBike4 = snapshot.child("Bicycle").child("Bicycle4").child("name4").getValue(String.class);
                    String nameBike5 = snapshot.child("Bicycle").child("Bicycle5").child("name5").getValue(String.class);
                    String namePrice1 = snapshot.child("Bicycle").child("Bicycle1").child("price1").getValue(String.class);
                    String namePrice2 = snapshot.child("Bicycle").child("Bicycle2").child("price2").getValue(String.class);
                    String namePrice3 = snapshot.child("Bicycle").child("Bicycle3").child("price3").getValue(String.class);
                    String namePrice4 = snapshot.child("Bicycle").child("Bicycle4").child("price4").getValue(String.class);
                    String namePrice5 = snapshot.child("Bicycle").child("Bicycle5").child("price5").getValue(String.class);


                    Intent intent = new Intent(getApplicationContext(),Book.class);

                    intent.putExtra("name1",nameBike1);
                    intent.putExtra("name2",nameBike2);
                    intent.putExtra("name3",nameBike3);
                    intent.putExtra("name4",nameBike4);
                    intent.putExtra("name5",nameBike5);
                    intent.putExtra("price1",namePrice1);
                    intent.putExtra("price2",namePrice2);
                    intent.putExtra("price3",namePrice3);
                    intent.putExtra("price4",namePrice4);
                    intent.putExtra("price5",namePrice5);

                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}