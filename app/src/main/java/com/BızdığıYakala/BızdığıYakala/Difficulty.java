package com.BızdığıYakala.BızdığıYakala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Difficulty extends AppCompatActivity {

    //int gelenspeed;
    int speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        //gelenspeed = intent.getIntExtra("speed",500);


    }

    public void easy(View view){

        //gelenspeed  = 750;
        Intent intent = new Intent(Difficulty.this,MainActivity.class);
        speed = 750;
        intent.putExtra("speed",speed);

        startActivity(intent);
    }

    public void medium(View view){
        //gelenspeed = 500;
        Intent intent = new Intent(Difficulty.this,MainActivity.class);
        speed = 500;
        intent.putExtra("speed",speed);
        startActivity(intent);

    }

    public void hard(View view){
        //gelenspeed = 1250;
        Intent intent = new Intent(Difficulty.this,MainActivity.class);
        speed = 250;
        intent.putExtra("speed",speed);
        startActivity(intent);

    }
}