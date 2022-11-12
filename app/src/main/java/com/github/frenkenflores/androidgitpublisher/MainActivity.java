package com.github.frenkenflores.androidgitpublisher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Map<String, String> links = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Fill dictionary with data.
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("links.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String text = new String(buffer);
            for (String line : text.split("\n")) {
                links.put(line.split("=")[0], line.split("=")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> e : links.entrySet()) {
            System.out.println(e);
        }
    }
}