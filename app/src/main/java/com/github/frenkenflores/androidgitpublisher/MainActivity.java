package com.github.frenkenflores.androidgitpublisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        for (Map.Entry<String, String> e : links.entrySet()) {
            Button element = new Button(this);
            element.setText(e.getKey());
            element.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToEditPage = new Intent(MainActivity.this, EditPage.class);
                    goToEditPage.putExtra("link", e.getValue());
                    startActivity(goToEditPage);
                }
            });
            linearLayout.addView( element);
            System.out.println(e);
        }

    }
}