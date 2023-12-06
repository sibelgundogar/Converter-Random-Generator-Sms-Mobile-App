package com.example.myapplication_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtStudentNumber = findViewById(R.id.txtStudentNumber);
        TextView txtStudentName = findViewById(R.id.txtStudentName);
        Button btnConverter  = (Button) findViewById(R.id.btnConverter);
        Button btnRandomGenerator  = (Button) findViewById(R.id.btnRandomGenerator);
        Button btnSms  = (Button) findViewById(R.id.btnSms);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(10000);
        txtStudentNumber.startAnimation(fadeIn);
        txtStudentName.startAnimation(fadeIn);


        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MainActivity.this, ConverterActivity.class);
                startActivity(c);
            }
        });

        btnRandomGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(MainActivity.this, RandomGeneratorActivity.class);
                startActivity(r);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(MainActivity.this, SmsActivity.class);
                startActivity(s);
            }
        });
    }
}

