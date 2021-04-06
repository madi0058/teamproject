package com.cst2335.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton car= findViewById(R.id.car);
        ImageButton trivia = findViewById(R.id.trivia);
        ImageButton soccer= findViewById(R.id.soccer);
        ImageButton songster= findViewById(R.id.songster);
//        car.setOnClickListener(clk ->{
//            Intent gotocar = new Intent(MainActivity.this, Car.class);
//            startActivity(gotocar);
//        });
        trivia.setOnClickListener(clk ->{
            Intent gototrivia= new Intent(MainActivity.this, Trivia.class);
            startActivity(gototrivia);
        });
//        soccer.setOnClickListener(clk ->{
//            Intent gotosoccer= new Intent(MainActivity.this, Soccer.class);
//            startActivity(gotosoccer);
//        });
//        songster.setOnClickListener(clk ->{
//            Intent gotosongster= new Intent(MainActivity.this, sogster.class);
//            startActivity(gotosongster);
//        });


    }
    protected void onPause(){
        super.onPause();

    }
}