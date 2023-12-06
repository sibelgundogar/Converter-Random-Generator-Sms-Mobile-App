package com.example.myapplication_5;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import java.text.DecimalFormat;

public class ConverterActivity extends AppCompatActivity {
    EditText etDecimal, etByte, etCelcius;
    Spinner spDecimal, spByte;
    TextView txtDecRes, txtByteRes, txtCelRes;
    RadioButton rbtnFahr, rbtnKelvin;
    RadioGroup rgrpCelc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        Button btnBackConverter = findViewById(R.id.btnBackConverter);

        etDecimal = findViewById(R.id.etDecimal);
        spDecimal = findViewById(R.id.spDecimal);
        txtDecRes = findViewById(R.id.txtDecRes);

        etByte = findViewById(R.id.etByte);
        spByte = findViewById(R.id.spByte);
        txtByteRes = findViewById(R.id.txtByteRes);

        etCelcius = findViewById(R.id.etCelcius);
        rbtnFahr = findViewById(R.id.rbtnFahr);
        rbtnKelvin = findViewById(R.id.rbtnKelvin);
        txtCelRes = findViewById(R.id.txtCelRes);
        rgrpCelc = findViewById(R.id.rgrpCelc);

        btnBackConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.decimal_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDecimal.setAdapter(adapter);

        spDecimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                convertDecimalToSelected(selectedItem);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        etDecimal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    String selectedItem = spDecimal.getSelectedItem().toString();
                    convertDecimalToSelected(selectedItem);
                } else {
                    txtDecRes.setText("Sonuç :");
                }
            }
        });

        ArrayAdapter<CharSequence> adapterByte = ArrayAdapter.createFromResource(this,
                R.array.byte_array, android.R.layout.simple_spinner_item);
        adapterByte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spByte.setAdapter(adapterByte);

        spByte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertByteToSelected();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        etByte.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                convertByteToSelected();
            }
        });


        rgrpCelc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                convertCelciusToSelected();
            }
        });

        etCelcius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                convertCelciusToSelected();
            }
        });
    }


    private void convertDecimalToSelected(String selectedItem) {
        String input = etDecimal.getText().toString();
        if(input.length()==0) {
            txtDecRes.setText("Sonuç :");
            return;
        };

        int num = Integer.parseInt(input);
        if (selectedItem.equals("Binary")) {
            txtDecRes.setText(Integer.toBinaryString(num));
        }
        else if(selectedItem.equals("Octal")){
            txtDecRes.setText(Integer.toOctalString(num));
        }
        else if (selectedItem.equals("Hexadecimal")) {
            txtDecRes.setText(Integer.toHexString(num).toUpperCase());
        }
    }

    private void convertByteToSelected(){
        String input = etByte.getText().toString();
        if(input.length()==0) {
            txtByteRes.setText("Sonuç :");
            return;
        };

        int num = Integer.parseInt(input);
        float result = 0;
        switch (spByte.getSelectedItem().toString()) {
            case "Kilobyte":
                result = num * 1024f;
                break;
            case "Byte":
                result = num * 1048576f;
                break;
            case "Kibibyte":
                result = num * 976.56f;
                break;
            case "Bit":
                result = num * 8388608f;
                break;
        }
        DecimalFormat df = new DecimalFormat("0.##");
        txtByteRes.setText(df.format(result));
    }


    private void convertCelciusToSelected(){
        String input = etCelcius.getText().toString();
        if(input.length() == 0) {
            txtCelRes.setText("Sonuç :");
            return;
        }
        float result = Float.parseFloat(input.toString());
        if(rbtnFahr.isChecked()){
            result = result * 1.8f + 32;
        }
        else if(rbtnKelvin.isChecked()){
            result = result+273;
        }else return;
        DecimalFormat df = new DecimalFormat("0.##");
        txtCelRes.setText(df.format(result));
    }
}



