package com.BızdığıYakala.BızdığıYakala;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GettingPictureFromGallery extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonChoose,btnPlayGame;

    private static final int IMAG_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_picture_from_gallery);

        imageView = findViewById(R.id.image_view);
        buttonChoose = findViewById(R.id.btnChooose);
        btnPlayGame = findViewById(R.id.playGame);

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                        // permission not granted, request it

                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        // show pop for run time permissiin
                        requestPermissions(permission,PERMISSION_CODE);

                    }else{

                        // permission already granted

                        pickImageFromGallery();

                    }
                }
                else{
                    // system os is  less than marsmallow
                    pickImageFromGallery();
                }

            }
        });

        btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingPictureFromGallery.this,Difficulty.class);

                startActivity(intent);
            }
        });




    }



    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAG_PICK_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was agranted
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permmission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAG_PICK_CODE) {
            imageView.setImageURI(data.getData());

            Intent intent = new Intent(GettingPictureFromGallery.this,MainActivity.class);
            int imageN = Integer.parseInt(String.valueOf(data));
            intent.putExtra("data",imageN);
            startActivity(intent);

        }
    }



}