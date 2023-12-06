package com.example.myapplication_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
    Button btnSmsSend;
    Activity activity;
    EditText etSmsPhone, etSmsMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        checkPermissions();

        Button btnBackSms = (Button) findViewById(R.id.btnBackSms);
        btnSmsSend = findViewById(R.id.btnSmsSend);
        etSmsPhone = findViewById(R.id.etSmsPhone);
        etSmsMsg = findViewById(R.id.etSmsMsg);
        btnBackSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSmsSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(etSmsPhone.getText().toString(), null, etSmsMsg.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS başarıyla gönderildi", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS gönderilirken bir hatayla karşılaşıldı", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(SmsActivity.this, new String[]{Manifest.permission.SEND_SMS}, 98);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 98);
            }
        }
    }
}