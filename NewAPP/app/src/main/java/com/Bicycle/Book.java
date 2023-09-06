package com.Bicycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Book extends AppCompatActivity {

    ImageButton Bike1, Bike2, Bike3, Bike4, Bike5;
    TextView Bike_name1, Bike_name2, Bike_name3, Bike_name4, Bike_name5, Bike_price1, Bike_price2, Bike_price3, Bike_price4, Bike_price5;

    Button Book1, Book2, Book3, Book4, Book5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Bike1 = findViewById(R.id.bicycle01);
        Bike2 = findViewById(R.id.bicycle02);
        Bike3 = findViewById(R.id.bicycle03);
        Bike4 = findViewById(R.id.bicycle04);
        Bike5 = findViewById(R.id.bicycle05);
        Bike_name1 = findViewById(R.id.bike_name1);
        Bike_name2 = findViewById(R.id.bike_name2);
        Bike_name3 = findViewById(R.id.bike_name3);
        Bike_name4 = findViewById(R.id.bike_name4);
        Bike_name5 = findViewById(R.id.bike_name5);
        Bike_price1 = findViewById(R.id.bike_price1);
        Bike_price2 = findViewById(R.id.bike_price2);
        Bike_price3 = findViewById(R.id.bike_price3);
        Bike_price4 = findViewById(R.id.bike_price4);
        Bike_price5 = findViewById(R.id.bike_price5);
        Book1 = findViewById(R.id.booking_1);
        Book2 = findViewById(R.id.booking_2);
        Book3 = findViewById(R.id.booking_3);
        Book4 = findViewById(R.id.booking_4);
        Book5 = findViewById(R.id.booking_5);

        showAllUserData();

        Bike1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bike("Bicycle1", "name1", "price1");
            }
        });
        Bike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bike("Bicycle2", "name2", "price2");
            }
        });
        Bike3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bike("Bicycle3", "name3", "price3");
            }
        });
        Bike4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bike("Bicycle4", "name4", "price4");
            }
        });
        Bike5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bike("Bicycle5", "name5", "price5");
            }
        });

        Book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book("Bicycle1", "name1", "price1");
            }
        });
        Book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book("Bicycle2", "name2", "price2");
            }
        });
        Book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book("Bicycle3", "name3", "price3");
            }
        });
        Book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book("Bicycle4", "name4", "price4");
            }
        });
        Book5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book("Bicycle5", "name5", "price5");
            }
        });
    }

    public  void Book(String Bicycle, String name, String price) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("App/Admin/Bicycle");
        Query checkUser = reference.orderByChild("username").equalTo(Bicycle);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    int use = snapshot.child(Bicycle).child("use").getValue(Integer.class);

                    if (use == 0) {

                        String nameFromDB = snapshot.child(Bicycle).child(name).getValue(String.class);
                        String priceFromDB = snapshot.child(Bicycle).child(price).getValue(String.class);
                        String usernameFromDB = snapshot.child(Bicycle).child("username").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Booking.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("price", priceFromDB);
                        intent.putExtra("username", usernameFromDB);

                        startActivity(intent);

                    } else {

                        startActivity(new Intent(getApplicationContext(), Bike_in_use.class));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Bike(String Bicycle, String name, String price){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("App/Admin/Bicycle");
        Query checkUser = reference.orderByChild("username").equalTo(Bicycle);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    String nameFromDB = snapshot.child(Bicycle).child(name).getValue(String.class);
                    String usernameFromDB = snapshot.child(Bicycle).child("username").getValue(String.class);
                    String detailFromDB = snapshot.child(Bicycle).child("details").getValue(String.class);
                    String priceFromDB = snapshot.child(Bicycle).child(price).getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(), Description.class);

                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("details", detailFromDB);
                    intent.putExtra("price", priceFromDB);

                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showAllUserData() {

        Intent intent = getIntent();
        String bike_name1 = intent.getStringExtra("name1");
        String bike_name2 = intent.getStringExtra("name2");
        String bike_name3 = intent.getStringExtra("name3");
        String bike_name4 = intent.getStringExtra("name4");
        String bike_name5 = intent.getStringExtra("name5");
        String bike_price1 = intent.getStringExtra("price1");
        String bike_price2 = intent.getStringExtra("price2");
        String bike_price3 = intent.getStringExtra("price3");
        String bike_price4 = intent.getStringExtra("price4");
        String bike_price5 = intent.getStringExtra("price5");

        Bike_name1.setText(bike_name1);
        Bike_name2.setText(bike_name2);
        Bike_name3.setText(bike_name3);
        Bike_name4.setText(bike_name4);
        Bike_name5.setText(bike_name5);

        Bike_price1.setText(bike_price1);
        Bike_price2.setText(bike_price2);
        Bike_price3.setText(bike_price3);
        Bike_price4.setText(bike_price4);
        Bike_price5.setText(bike_price5);


    }

}

