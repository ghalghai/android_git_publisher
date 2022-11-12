package com.github.frenkenflores.androidgitpublisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EditPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        Intent intent = getIntent();
        String value = intent.getStringExtra("link");
        System.out.println(value);
    }
}