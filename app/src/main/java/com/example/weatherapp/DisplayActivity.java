package com.example.weatherapp;

import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DisplayActivity extends AppCompatActivity {

    TextView city;
    TextView temp;
    TextView min;
    TextView max;
    TextView feels;

    TextToSpeech t1;
    String ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        WeatherData w = MainActivity.getWeatherInstance();
        city = findViewById(R.id.cityView);
        temp = findViewById(R.id.tempView);
        min =  findViewById(R.id.minView);
        feels = findViewById(R.id.feelsView);
        max = findViewById(R.id.maxView);

        city.setText("Weather for " + w.getCityName());
        temp.setText("Temperature : " + String.valueOf(Math.floor(KtoF(Double.parseDouble(w.getTemp())))) + "F");
        min.setText("Low : " + String.valueOf(Math.floor(KtoF(Double.parseDouble(w.getTempMin()))))+ "F");
        max.setText("Maximum : " + String.valueOf(Math.floor(KtoF(Double.parseDouble(w.getTempMax())))) + "F");
        feels.setText("Feels like : " + String.valueOf(Math.floor(KtoF(Double.parseDouble(w.getFeelsLike()))))+ "F");


        ed1=city.getText().toString()+" "+temp.getText().toString()+" "+min.getText().toString()+" "+feels.getText().toString();
        b1=(Button)findViewById(R.id.textSpeech);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }else{
                    System.out.println("Problem");
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1;
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        







    }

    public static double KtoF(Double k){
        return ((k - 273.15) * 9/5 + 32);
    }
}