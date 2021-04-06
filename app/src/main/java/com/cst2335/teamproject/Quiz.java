package com.cst2335.teamproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Quiz extends AppCompatActivity {
    ProgressBar loadbar;

   int bb=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadbar = findViewById(R.id.loadbar);
        loadbar.setVisibility(View.VISIBLE);

        TrivaQuery trivaQuery = new TrivaQuery();
        TrivaQuery.execute("https://opentdb.com/api_config.php");
    }

    private  class TrivaQuery extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(args[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream response = urlConnection.getInputStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(response, "UTF-8");
                int eventType = xpp.getEventType(); // the parser at Start Document
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (xpp.getEventType() == XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("temperature")) {
                            String curr = xpp.getAttributeValue(null, "value");
                            publishProgress(25);
                            String max = xpp.getAttributeValue(null, "max");
                            publishProgress(50);
                            String min = xpp.getAttributeValue(null, "min");
                            publishProgress(75);
                        }

                        return null;
                    }
                }


            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
