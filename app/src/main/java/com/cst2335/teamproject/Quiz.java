package com.cst2335.teamproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    public static String url;

    public static TextView textView5,ans1, ans2, ans3, ans4, next, prev,submit,right,number,wrong;
    public int i,y=0;
    public int z=i++;
    public int count=0;
   public static ArrayList<String> arr= new ArrayList<>();
    public static ArrayList<String> ans= new ArrayList<>();
    String array="majd";
    public static ProgressBar loadbar;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadbar = findViewById(R.id.progressBar);
        loadbar.setVisibility(View.VISIBLE);
        ans1 = findViewById(R.id.answer1);
        ans2 = findViewById(R.id.answer2);
        ans3 = findViewById(R.id.answer3);
        ans4 = findViewById(R.id.answer4);
        next= findViewById(R.id.next);
        prev=findViewById(R.id.prev);
        submit=findViewById(R.id.submit);
        textView5 = findViewById(R.id.question);
        Intent gotoscore= new Intent(Quiz.this, Score.class);


        String min = getIntent().getExtras().getString("number");

        String diff = getIntent().getExtras().getString("diff");
        String kin = getIntent().getExtras().getString("kin");
        //url=("https://opentdb.com/api.php?amount=10&category=10&difficulty=easy&type=multiple");
        url = String.format("https://opentdb.com/api.php?amount=%1s&difficulty=%2s&type=%3s", min, diff, kin);
        textView5.setText(url);

            TriviaQuery forecastQuery = new TriviaQuery();
            forecastQuery.execute(url);

        next.setOnClickListener(clk ->{
            TriviaQuery.setQuestions();

        });
        prev.setOnClickListener(clk ->{
            TriviaQuery.prev();
        });
        submit.setOnClickListener(clk ->{
            gotoscore.putExtra("score",y);
            startActivity(gotoscore);
        });
        ans1.setOnClickListener(clk ->{
            try {
                 if(TriviaQuery.rightanswer().toUpperCase().equals(ans1.getText().toString().toUpperCase())) {
                     count++;
                     y++;
                     textView5.setText("its good"+y);
                     if (count== TriviaQuery.fin){
                         next.setVisibility(View.GONE);
                         submit.setVisibility(View.VISIBLE);

                     }
                 }else {
                     textView5.setText("not good");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        ans2.setOnClickListener(clk->{
            try {
                if(TriviaQuery.rightanswer().toUpperCase().equals(ans1.getText().toString().toUpperCase())) {
                    count++;
                    textView5.setText("wrong answer"+y);
                    if (count== TriviaQuery.fin){
                        next.setVisibility(View.GONE);
                        submit.setVisibility(View.VISIBLE);

                    }
                }else {
                    textView5.setText("not good");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        ans3.setOnClickListener(clk->{
            try {
                if(TriviaQuery.rightanswer().toUpperCase().equals(ans1.getText().toString().toUpperCase())) {
                    count++;

                    textView5.setText("wrong answer"+y);
                    if (count== TriviaQuery.fin){
                        next.setVisibility(View.GONE);
                        submit.setVisibility(View.VISIBLE);

                    }
                }else {
                    textView5.setText("not good");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        ans4.setOnClickListener(clk->{
            try {
                if(TriviaQuery.rightanswer().toUpperCase().equals(ans1.getText().toString().toUpperCase())) {
                    count++;
                    textView5.setText("wrong answer"+y);
                    if (count== TriviaQuery.fin){
                        next.setVisibility(View.GONE);
                        submit.setVisibility(View.VISIBLE);

                    }
                }else {
                    textView5.setText("not good");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }

    protected void onPause(){
        super.onPause();

    }
        }

