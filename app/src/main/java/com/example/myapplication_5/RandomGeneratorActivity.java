package com.example.myapplication_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class RandomGeneratorActivity extends AppCompatActivity {

    EditText etCount, etMax, etMin;
    Button btnCalculate;
    LinearLayout llvProgBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generator);

        Button btnBackRnd = (Button) findViewById(R.id.btnBackRnd);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        llvProgBars = findViewById(R.id.llvProgBars);
        etCount = findViewById(R.id.etProgCount);
        etMin = findViewById(R.id.etProgMin);
        etMax = findViewById(R.id.etProgMax);

        btnBackRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(etCount.getText().toString());
                int max = Integer.parseInt(etMax.getText().toString());
                int min = Integer.parseInt(etMin.getText().toString());
                LayoutInflater inflater = getLayoutInflater();
                if (llvProgBars.getChildCount() != 0) {
                    llvProgBars.removeAllViews();
                }
                for (int i = 0; i < count; i++) {
                    View progbar = inflater.inflate(R.layout.item_rnd, null);
                    TextView txtMin = progbar.findViewById(R.id.txtProgMin);
                    TextView txtMax = progbar.findViewById(R.id.txtProgMax);
                    TextView txtResult = progbar.findViewById(R.id.txtProgResult);
                    ProgressBar progBar = progbar.findViewById(R.id.pbarProgBar);
                    Random rnd = new Random();
                    int newMin = rnd.nextInt(max - min + 1) + min;
                    int newMax = rnd.nextInt(max - newMin) + newMin+1;
                    int range = newMax - newMin;
                    int num = rnd.nextInt(range + 1) + newMin;
                    int percentage = ((num - newMin) * 100) / range;
                    progBar.setProgress(percentage);

                    txtResult.setText(String.format("%d = %%%d ", num, percentage));
                    txtMin.setText("" + newMin);
                    txtMax.setText("" + newMax);
                    llvProgBars.addView(progbar);
                }
            }
        });
    }
}
