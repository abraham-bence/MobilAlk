package com.example.akasztofa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Image
    private TextView imageDisplay;
    //Text
    private TextView textDisplay;
    //Buttons
    private Button negativeBtn;
    private Button postiveBtn;
    private Button submitBtn;
    //Abc
    private final String[] characterList = {"a", "á", "b", "c", "cs", "d", "dz", "dzs", "e", "é", "f", "g", "gy", "h", "i", "í", "j", "k", "l", "ly", "m", "n", "ny", "o", "ó", "ö", "ő", "p", "r", "s", "sz", "t", "ty", "u", "ú", "ü", "ű", "v", "z", "zs"};
    private final String[] wordList = {
            "tyúkhúsleves", "alma", "macska", "asztal", "labda", "tavasz", "virág", "autó", "könyv", "térkép", "eső"
    };

    private String currentWord;

    private int timer = 0;
    private boolean running = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        OnGameStart();


        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeLetter(-1);
            }
        });

        new Thread() {
            @Override
            public void run(){
                while (running){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ChangeLetter(1);
                }
            }
        };

        postiveBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.d("msg","asd");
                    running = true;
                    new Thread() {
                        @Override
                        public void run(){
                            while (running){
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                ChangeLetter(1);
                            }
                        }
                    }.start();
                    return true;
                } if (event.getAction() == MotionEvent.ACTION_UP){
                    running = false;
                    return true;
                }

                return false;
            }
        });
    }
    private int currentLetterId = 0;
    private void ChangeLetter(int number){
        currentLetterId += number;
        Log.d("currentLetter", String.valueOf(currentLetterId));
        if (currentLetterId < 0){
            currentLetterId = characterList.length-1;
        }
        if (currentLetterId > characterList.length-1){
            currentLetterId = 0;
        }
        Log.d("currentLetter2", String.valueOf(currentLetterId));

        submitBtn.setText(characterList[currentLetterId]);
    }

    private void OnGameStart(){
        currentWord = RandomWord();
        String placeholder = "";
        for (int i = 0; i < currentWord.length(); i++) {
            placeholder += "_";
        }
        textDisplay.setText(placeholder);
    }
    private String RandomWord(){
        Random rnd = new Random();
        return wordList[rnd.nextInt(wordList.length)];
    }
    private void init() {
        negativeBtn = findViewById(R.id.negativeBtn);
        postiveBtn = findViewById(R.id.positiveBtn);
        submitBtn = findViewById(R.id.submitBtn);

        imageDisplay = findViewById(R.id.imageDisplay);

        textDisplay = findViewById(R.id.textDisplay);
    }
}
