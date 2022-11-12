package com.github.frenkenflores.androidgitpublisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
public class EditPage extends AppCompatActivity {

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_edit_page);
        Intent intent = getIntent();
        String value = intent.getStringExtra("link");
        try {
            HttpClient client = new DefaultHttpClient();
            String getURL = "https://api.github.com/repos/ghalghai/ghalgahi_mott/contents/app/src/main/assets/dict.txt";
            HttpGet get = new HttpGet(getURL);
            get.setHeader("Accept", "application/vnd.github.raw");
            get.setHeader("Authorization", "Bearer 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");

            HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                //do something with the response
                String resp = EntityUtils.toString(resEntityGet);
                Log.i("GET ", resp);
                TextView textView = (TextView) findViewById(R.id.editTextView);
                textView.setText(resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//            curl   -H "Accept: application/vnd.github.raw"   -H "Authorization: Bearer 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"   https://api.github.com/repos/ghalghai/ghalgahi_mott/contents/app/src/main/assets/dict.txt


        System.out.println(value);
    }
}