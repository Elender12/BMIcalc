package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity {
    private static final String TAG = "CalcActivity";
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
        height = findViewById(R.id.ET_height);
        weight = findViewById(R.id.ET_weight);
        age = findViewById(R.id.ET_age);
        radioGroup = findViewById(R.id.radioGroupGender);
        btn = findViewById(R.id.btnCalc);
        btn.setOnClickListener(v -> {
            float resultsBmi = calculateBMI();
            double [] resultsFCMax = calculateFCMax();
            if(radioGroup.getCheckedRadioButtonId() != -1 && height.getText().length() != 0 && weight.getText().length() != 0 && age.getText().length() != 0){
                Intent intent = new Intent(this, ResultsActivity.class);
                intent.putExtra("bmi", resultsBmi);
                intent.putExtra("fcmax", resultsFCMax);
                intent.putExtra("username", name.getText().toString());
                startActivity(intent);
            }else {
                Toast.makeText(this, "Rellene todos los campos, por favor", Toast.LENGTH_LONG).show();
            }

        });
    }
    private float calculateBMI() {
        float result = 0;
        try {
            float weightValue = Float.parseFloat(String.valueOf(weight.getText()));
            float heightValue = Float.parseFloat(String.valueOf(height.getText()));
            result = (float) (weightValue / Math.pow(heightValue, 2));
        } catch (NumberFormatException nfe) {
        }
        return result;
    }

    /**
     * El metodo calcula la frecuencia cardiaca maxima
     * @return double [] resultados
     * La primera posicion almacena el valor para las personas sedentarias
     * La segunda posicion almacena el valor para las personas entrenadas
     */
    private double[] calculateFCMax() {
        //primera posición valores para personas sedentarias, segunda posicion p
        double[] results = new double[2];
        int checkedId =  radioGroup.getCheckedRadioButtonId();
        int ageValue = 0;
        boolean isValidAge;
        try{
            ageValue = Integer.parseInt(String.valueOf(age.getText()));
            isValidAge = true;
        }catch(NumberFormatException nfe){
            isValidAge = false;
        }
        if(isValidAge){
            if (checkedId == R.id.radioFemale) {
                //Mujeres entrenadas: FCmax = 214 – (0,8 x edad)
                //Mujeres sedentarias: 225 - edad
                double sedentaryFCMax = 225 - ageValue;
                double trainedFCMax = 214 - (0.8 *  ageValue);
                Log.d(TAG, "onCheckedChanged: "+sedentaryFCMax+" "+trainedFCMax);
                results[0] = sedentaryFCMax;
                results[1] = trainedFCMax;
            }
            if (checkedId == R.id.radioMale) {
                //Hombres: FCmax = 209 – (0,7 x edad)
                double sedentaryFCMax = 220 - ageValue;
                double trainedFCMax = 209 - (0.7 *  ageValue);
                results[0] = sedentaryFCMax;
                results[1] = trainedFCMax;
            }
        }
        return results;
    }
}

