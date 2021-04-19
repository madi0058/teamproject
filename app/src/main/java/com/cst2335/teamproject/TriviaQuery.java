package com.cst2335.teamproject;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TriviaQuery extends AsyncTask<String, Integer, String> {

    public static JSONArray wronganswer,jsonArray,majd;
    public static JSONObject questions;
    public static String answer, question;
    public static StringBuilder data;
    public static TextView textView;
    static int fin,i,z,q;

    @Override
    protected String doInBackground(String... args) {

        try {
            URL urlc = new URL(Quiz.url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlc.openConnection();
            InputStream response = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 16);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                data = stringBuilder.append(line).append("\n");
            }
            try {
                JSONObject jsonObject = new JSONObject(data.toString());
                jsonArray = jsonObject.getJSONArray("results");
                publishProgress(25);
                publishProgress(50);
                publishProgress(75);
                for (i = 0; i < jsonArray.length(); i++) {
                    questions = jsonArray.getJSONObject(i);
                    publishProgress(100);
//                    question = questions.getString("question");
//                    wronganswer = questions.getJSONArray("incorrect_answers");
                    answer = questions.getString("correct_answer");
//                    Log.v("his  i", i+question);
                    fin=jsonArray.length();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Successfully";
    }

    @SuppressLint("WrongThread")
    public void onPostExecute(String UV) {
        Quiz.loadbar.setVisibility(View.INVISIBLE);
        textView = Quiz.textView5;
        try {
            textView.setText((CharSequence) jsonArray.getJSONObject(z).getString("question"));
            Quiz.ans1.setText(jsonArray.getJSONObject(z).getString("correct_answer").toUpperCase());
            Quiz.ans2.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(0).toString());
            Quiz.ans3.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(1).toString());
            Quiz.ans4.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(2).toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
            }

    public static void setQuestions(){
    z++;
    try {
        textView.setText((CharSequence) jsonArray.getJSONObject(z).getString("question"));
        Quiz.ans1.setText(jsonArray.getJSONObject(z).getString("correct_answer"));
        Quiz.ans2.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(0).toString());
        Quiz.ans3.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(1).toString());
        Quiz.ans4.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(2).toString());

    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    public static void prev(){
        z--;
        try {
            textView.setText((CharSequence) jsonArray.getJSONObject(z).getString("question"));
            Quiz.ans1.setText(jsonArray.getJSONObject(z).getString("correct_answer"));
            Quiz.ans2.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(0).toString());
            Quiz.ans3.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(1).toString());
            Quiz.ans4.setText(jsonArray.getJSONObject(z).getJSONArray("incorrect_answers").get(2).toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

public static String rightanswer() throws JSONException {
        q=z;
    String ans=jsonArray.getJSONObject(q).getString("correct_answer");
    return ans;
}

    @Override
    public void onProgressUpdate(Integer... progress) {
        Log.d("progress bar", "progress bar:" + progress[0]);
        Quiz.loadbar.getProgressDrawable().setColorFilter(
                Color.GREEN, PorterDuff.Mode.SRC_IN);
        Quiz.loadbar.setVisibility(View.VISIBLE);
        Quiz.loadbar.setProgress(progress[0]);
    }
}