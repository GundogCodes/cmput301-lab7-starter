package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);

        TextView cityText = findViewById(R.id.text_city_name);
        Button backBtn = findViewById(R.id.btn_back);

        String city = getIntent().getStringExtra(MainActivity.extraCityName);
        if (city == null) city = "Unknown City";
        cityText.setText(city);

        backBtn.setOnClickListener(v -> finish());
    }
}