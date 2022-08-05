package com.BızdığıYakala.BızdığıYakala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainMenu extends AppCompatActivity {
    int GelenhighScore;
    int gelenScore;

    int lastScore;
    int best1;
    private Button buttonLight,buttonDark;
    //private ToggleButton toggleButton;

    private Switch aSwitch;
    static Button exitButton;
    TextView HighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        HighScore = findViewById(R.id.score);
        //exitButton = findViewById(R.id.button3);
        //aSwitch = findViewById(R.id.switchDark);
        getSupportActionBar().hide();

        buttonLight = findViewById(R.id.buttonLight);
        buttonDark = findViewById(R.id.buttonDark);



        buttonDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        buttonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//                else{
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//            }
//        });


        SharedPreferences preferences = getSharedPreferences("press",0);

//        toggleButton = findViewById(R.id.toggleButton);
//
//        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//                else{
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//            }
//        });

        lastScore = preferences.getInt("score",0);
        best1 = preferences.getInt("best1",0);

        if (lastScore > best1){
            best1 = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best1",best1);
            editor.apply();
        }

        HighScore.setText("High Score: " + best1);

//        exitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moveTaskToBack(true);
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);
//            }
//        });

        /*Intent Gelenintent = getIntent();
        GelenhighScore = Gelenintent.getIntExtra("highScore",0);
        gelenScore = Gelenintent.getIntExtra("score",0);


        if (gelenScore > GelenhighScore){
            GelenhighScore = gelenScore;
        }





        HighScore.setText("High Score is: " + GelenhighScore);*/


    }

    public void play(View view){

        //highScore =
        Intent intent = new Intent(MainMenu.this,GettingPictureFromGallery.class);
        startActivity(intent);
    }




}