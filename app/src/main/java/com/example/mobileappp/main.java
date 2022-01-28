package com.example.mobileappp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHairImageButton();
        initmakeupImageButton();
    }

    private void initHairImageButton() {
        ImageButton imgbtn1 = (ImageButton) findViewById(R.id.imageButtonHair);
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHairInterface = new Intent(main.this, hairinterface.class);
                startActivity(toHairInterface);
            }
        });
    }

    private void initmakeupImageButton() {
        ImageButton imgbtn2 = (ImageButton) findViewById(R.id.imageButtonMakeup);
        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMakeupInterface = new Intent(main.this, makeupinterface.class);
                startActivity(toMakeupInterface);
            }
        });
    }
}