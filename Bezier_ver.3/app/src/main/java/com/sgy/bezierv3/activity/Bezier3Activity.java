package com.sgy.bezierv3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sgy.bezierv3.databinding.ActivityBezier3Binding;

public class Bezier3Activity extends AppCompatActivity {

    ActivityBezier3Binding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBezier3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}