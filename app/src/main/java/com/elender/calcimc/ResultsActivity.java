package com.elender.calcimc;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Locale;

/**
 * @author Elena Cirstea
 * Clase que interpreta los resultados de la actividad anterior
 * */
public class ResultsActivity extends AppCompatActivity {
    TextView results, bmiTV, bmiClasification, sedentaryTV, activeTV, TV_thanks;
    String name = "";

    /**
     * Método que se ejecuta cuando se inicia la aplicación
     * @param savedInstanceState
     */
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
            this.name = bundle.getString("username");
        }
        //se asocian los elementos de la vista a variables de la clase
        results = findViewById(R.id.textViewResults);
        bmiTV = findViewById(R.id.TV_bmiValue);
        bmiClasification = findViewById(R.id.TV_clasification);
        sedentaryTV = findViewById(R.id.TV_sedentary);
        activeTV = findViewById(R.id.TV_active);
        TV_thanks = findViewById(R.id.TV_thanks);
        // se definen las propiedades de la animacion
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 2);
        // se asocian las propiedades con la vista a animar
        ObjectAnimator animation =  ObjectAnimator.ofPropertyValuesHolder(results, pvhX, pvhY);
        //se establece la duración
        animation.setDuration(4000);
        //se inicia la animación
        animation.start();
        //se llama al método  que muestra los datos
        showResults(bmiValue, fcMaxValues);
    }

    /**
     * Según los parámetros que recibe, muestra en pantalla unos valores u otros
     * @param bmiValue
     * @param fcMaxValue
     */
    private void showResults(float bmiValue, double [] fcMaxValue){
        if(bmiValue < 18.5){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.lowWeight));
            bmiClasification.setTextColor(getColor(R.color.yellow));
        }else if (bmiValue >= 18.5 && bmiValue <= 24.9){
            bmiTV.setText(String.format(Locale.getDefault(), "%s  %.2f%n", getString(R.string.bmi), bmiValue));
            bmiClasification.setText(getString(R.string.normalWeight));
            bmiClasification.setTextColor(getColor(R.color.green));
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
        TV_thanks.setText(String.format("%s, %s", this.name.toUpperCase(), getString(R.string.gratitude)));
    }
}