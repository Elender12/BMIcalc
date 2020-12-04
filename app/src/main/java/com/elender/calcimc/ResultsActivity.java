package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    TextView results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        results = findViewById(R.id.textViewResults);
        ObjectAnimator animation = ObjectAnimator.ofFloat(results, "scaleX", 2);
        animation.setDuration(4000);
        animation.start();
    }
}