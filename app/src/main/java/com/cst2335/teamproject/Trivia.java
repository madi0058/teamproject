package com.cst2335.teamproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Trivia extends AppCompatActivity {
    RadioGroup radioGroup,deffeclty;
    RadioButton radioButton,dif,kind;
    EditText number;
    public static String num;
   public static String diff;
    public static String kin;




    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
         number= findViewById(R.id.editTextNumber);
        TextView textView3= findViewById(R.id.textView3);
       radioGroup= findViewById(R.id.radiogroup1);
       deffeclty= findViewById(R.id.radiogroup2);
        Button submit= findViewById(R.id.button);
      Intent gotoquiz= new Intent(this, Quiz.class);


        submit.setOnClickListener(v -> {
            int majd = deffeclty.getCheckedRadioButtonId();
            dif= findViewById(majd);

            int m= radioGroup.getCheckedRadioButtonId();
            kind=findViewById(m);
            if (majd == -1) {
                Massage.message(getApplicationContext(), "please select your kind");
            } else  {
                findRadioButton(majd);
                findRadioButton(m);
                num=number.getText().toString();
                gotoquiz.putExtra("number",num);
                diff= (String) dif.getText().toString();
                gotoquiz.putExtra("diff",diff);
                kin= (String) kind.getText().toString();
                gotoquiz.putExtra("kin",kin);
                textView3.setText(diff);
                startActivity(gotoquiz);

            }

        });

    }

    private void findRadioButton(int k) {

        switch (k){
            case R.id.radioButton21:

                break;
            case R.id.radioButton22:

                break;
            case R.id.radioButton23:

                break;
            case R.id.radioButton11:

                break;
            case R.id.radioButton12:

                break;
            case R.id.radioButton13:

            default:


        }

    }



}
