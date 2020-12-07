package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btnStart;
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageLayout);
        img.setBackgroundResource(R.drawable.main_animation);
        anim = (AnimationDrawable) img.getBackground();
        anim.start();
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalcActivity.class);
            startActivity(intent);
        });
    }


}