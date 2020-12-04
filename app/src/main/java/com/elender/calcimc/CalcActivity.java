package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class CalcActivity extends AppCompatActivity {
    EditText name, height, weight, age;
    RadioGroup radioGroup;
    Button btn;
    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        ConstraintLayout constraintLayout = findViewById(R.id.calcLayout);
       //variables para el cambio de color de fondo
        AnimationDrawable bckDrawable = (AnimationDrawable) constraintLayout.getBackground();
        bckDrawable.setEnterFadeDuration(2000);
        bckDrawable.setExitFadeDuration(4000);
        bckDrawable.start();
        //unir las variables con los elementos del layout
        name = findViewById(R.id.ET_name);
        height= findViewById(R.id.ET_height);
        weight = findViewById(R.id.ET_weight);
        age = findViewById(R.id.ET_age);
        radioGroup = findViewById(R.id.radioGroupGender);
        btn = findViewById(R.id.btnCalc);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultsActivity.class);
            //intent.put extra...
            startActivity(intent);
        });
    }

}
