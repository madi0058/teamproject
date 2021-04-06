package com.cst2335.teamproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Trivia extends AppCompatActivity {
    RadioGroup radioGroup,deffeclty;
    RadioButton radioButton;



    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        EditText number= findViewById(R.id.editTextNumber);
        TextView textView3= findViewById(R.id.textView3);
       radioGroup= findViewById(R.id.radiogroup1);
       deffeclty= findViewById(R.id.radiogroup2);
        Button submit= findViewById(R.id.button);
//        String num= number.getText().toString();
//        Intent intent= new Intent(this,Quiz.class);
//        intent.putExtra("number",num);
//        startActivity(intent);

        submit.setOnClickListener(v -> {
            int majd = deffeclty.getCheckedRadioButtonId();
            int m= radioGroup.getCheckedRadioButtonId();
            if (majd == -1) {
                Massage.message(getApplicationContext(), "please select your kind");
            } else  {
                findRadioButton(majd);
                findRadioButton(m);
                textView3.setText(String.valueOf(m));
            }

        });


    }



    private void findRadioButton(int k) {

        switch (k){
            case R.id.radioButton21:
                Massage.message(getApplicationContext(),"i click 1");
                break;
            case R.id.radioButton22:
                Massage.message(getApplicationContext(),"i click 2");
                break;
            case R.id.radioButton23:
                Massage.message(getApplicationContext(),"i did 3");
                break;
            case R.id.radioButton11:

                Massage.message(getApplicationContext(),"i did 1");
                break;
            case R.id.radioButton12:
                Massage.message(getApplicationContext(),"i did 33");
                Intent gottoquiz = new Intent(this,Quiz.class);
                startActivity(gottoquiz);
                break;
            case R.id.radioButton13:
                Massage.message(getApplicationContext(),"you chosed hard");
                break;
            default:
                Massage.message(getApplicationContext(),"fuck");
        }

    }



}
