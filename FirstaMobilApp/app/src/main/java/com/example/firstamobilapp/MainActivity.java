package com.example.firstamobilapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private TextView rgbText;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.relativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    init();
    relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            rgbText.setTextColor(Color.WHITE);
            rgbText.setText("(0,0,0");
            relativeLayout.setBackgroundColor(Color.BLACK);
            return true;
        }
    });
    relativeLayout.setOnClickListener( new View.OnClickListener(){
        @Override
        public void onClick(View view){

            Random rand = new Random();
            int max=100,min=50;
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            if (r + g < 200){
                rgbText.setTextColor(Color.WHITE);
            } else {
                rgbText.setTextColor(Color.BLACK);
            }
            rgbText.setText("(" + r + "," + g + "," + b +")");
            relativeLayout.setBackgroundColor(Color.rgb(r,g,b));
        }
    });
    }

    public void init() {
        relativeLayout = findViewById(R.id.relativeLayout);
        rgbText = findViewById(R.id.rgbText);
    }

}