package com.example.voelog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    public Button btnCompreAqui;
    public Button btnTelaEmbarque;
    public Button btnCheckIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnCompreAqui = findViewById(R.id.btnCompreAqui);
        btnCompreAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CompreAqui.class);
                startActivity(intent);
            }
        });
        btnTelaEmbarque = findViewById(R.id.btnCartaoEmbarque);
        btnTelaEmbarque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),TelaCartaoEmbarque.class);
                startActivity(intent);
            }
        });
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Check_in.class);
                startActivity(intent);
            }
        });
    }
}