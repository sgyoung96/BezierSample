package com.sgy.bezierv3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sgy.bezierv3.databinding.ActivityBezier2Binding;

public class Bezier2Activity extends AppCompatActivity {

    ActivityBezier2Binding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBezier2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}