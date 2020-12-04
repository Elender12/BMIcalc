package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {
    TextView results, bmiTV, bmiClasification, sedentaryTV, activeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle bundle = getIntent().getExtras();
        //recojo los datos desde la actividad anterior
        float bmiValue = 0;
        double [] fcMaxValues = new double[2];
        if (bundle != null) {
            bmiValue = bundle.getFloat("bmi");
            fcMaxValues = bundle.getDoubleArray("fcmax");
        }
        results = findViewById(R.id.textViewResults);
        bmiTV = findViewById(R.id.TV_bmiValue);
        bmiClasification = findViewById(R.id.TV_clasification);
        sedentaryTV = findViewById(R.id.TV_sedentary);
        activeTV = findViewById(R.id.TV_active);


        ObjectAnimator animation = ObjectAnimator.ofFloat(results, "scaleX", 2);
        animation.setDuration(4000);
        animation.start();
        showResults(bmiValue, fcMaxValues);
    }

    /**
     * @param bmiValue
     * @param fcMaxValue
     */
    private void showResults(float bmiValue, double [] fcMaxValue){
        if(bmiValue < 18.5){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.lowWeight));
            bmiClasification.setTextColor(Color.YELLOW);
        }else if (bmiValue >= 18.5 && bmiValue <= 24.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.normalWeight));
            bmiClasification.setTextColor(Color.GREEN);
        }else if(bmiValue >= 25 && bmiValue <= 26.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.overweightI));
            bmiClasification.setTextColor(getColor(R.color.warningI));
        }else if(bmiValue >= 27 && bmiValue <= 29.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.overWeightII));
            bmiClasification.setTextColor(getColor(R.color.warningII));
        }else if(bmiValue >= 30 && bmiValue <=34.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.obesityI));
            bmiClasification.setTextColor(getColor(R.color.severeWarning));
        }else if(bmiValue >= 35 && bmiValue <= 39.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.obesityII));
            bmiClasification.setTextColor(getColor(R.color.severeWarningI));
        }else if(bmiValue >= 40 && bmiValue <= 49.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.obesityIII));
            bmiClasification.setTextColor(Color.RED);
        }else if(bmiValue > 50){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.obesityIV));
            bmiClasification.setTextColor(getColor(R.color.extremeWarning));
        }
        sedentaryTV.setText(String.format("%s",fcMaxValue[0]));
        activeTV.setText(String.format("%s", fcMaxValue[1]));



    }
}