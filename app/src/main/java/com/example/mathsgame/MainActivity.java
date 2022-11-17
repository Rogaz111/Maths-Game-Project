package com.example.mathsgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    Button buttonAdd;
    Button buttonSub;
    Button buttonMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMulti = findViewById(R.id.buttonMulti);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameSubtraction.class);
                startActivity(intent);
                finish();
            }
        });

        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameMultiplication.class);
                startActivity(intent);
                finish();
            }
        });
    }
}