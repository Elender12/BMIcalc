package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author Elena Cirstea
 * Clase principal que inicia la aplicación
 */
public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btnStart;
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se asocian los elementos de la vista a variables de la clase
        img = findViewById(R.id.imageLayout);
        img.setBackgroundResource(R.drawable.main_animation);
        anim = (AnimationDrawable) img.getBackground();
        anim.start();
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            //se inicia una nueva actividad
            Intent intent = new Intent(this, CalcActivity.class);
            startActivity(intent);
        });
    }


}