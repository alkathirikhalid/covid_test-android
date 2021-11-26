package com.iquii.covidtest.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.iquii.covidtest.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}