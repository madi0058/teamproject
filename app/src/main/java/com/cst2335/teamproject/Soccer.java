package com.cst2335.teamproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


public class Soccer extends AppCompatActivity {
    String title;
    String date;
    String image;
    ProgressBar loadPbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //loadPbar= findViewById(R.id.progressBar);
        loadPbar.setVisibility(View.VISIBLE);

    }
}
