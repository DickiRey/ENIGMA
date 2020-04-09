package com.example.enigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MasukMahasiswa extends AppCompatActivity {
    private Button buttoncode;
    private Button outmaha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk_mahasiswa);

        buttoncode = (Button)findViewById(R.id.buttoncode);
        buttoncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRCode();
            }
        });

        outmaha = (Button) findViewById(R.id.outmaha);
        outmaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKembaliHome();
            }
        });
    }
    public void openQRCode(){
        Intent intent = new Intent(this,QRCode.class);
        startActivity(intent);
    }
    public void openKembaliHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
