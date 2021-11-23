package com.sgy.bezierv3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sgy.bezierv3.databinding.ActivityBezier3Binding;

public class Bezier3Activity extends AppCompatActivity {

    ActivityBezier3Binding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBezier3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBezier1 = new Intent(getApplicationContext(), MainActivity.class);
                goBezier1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(goBezier1);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
}