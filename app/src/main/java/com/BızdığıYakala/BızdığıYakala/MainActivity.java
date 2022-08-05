package com.BızdığıYakala.BızdığıYakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    int highScore = 0;
    int speed = 500;

    SharedPreferences sharedPreferences;

    ImageView[] imageArray;

    TextView scoreText;
    TextView timeText;

    Handler handler;
    Runnable runnable;

    int score;
    int comeSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);

        Intent intent = getIntent();
        comeSpeed = intent.getIntExtra("speed",0);

        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,
                imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();

        Intent image = getIntent();





        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart The Game?");
                alert.setMessage("Are You Sure? ");
                alert.setMessage("Your Score: " + score);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences preferences = getSharedPreferences("press",0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("score",score);
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                        intent.putExtra("highScore",highScore);
                        intent.putExtra("score",score);
                        intent.putExtra("speed",speed);

                        startActivity(intent);

                    }

                }).show();



            }
        }.start();
    }


    public void ekoBaby(View view){

        try {
            for (ImageView image : imageArray){
                image.setVisibility(View.INVISIBLE);
            }
            Random random = new Random();
            int i = random.nextInt(9);
            imageArray[i].setVisibility(View.INVISIBLE);
            score++;
            scoreText.setText("Score: " + score);
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,comeSpeed);
            }
        };

        handler.post(runnable);
    }


}