package com.example.coinflip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private TextView throw_text;
    private TextView win_text;
    private TextView lose_text;

    private Button head_btn;
    private Button tail_btn;
    private ImageView coin;

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
        head_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomChoice(true); //head
            }
        });
        tail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomChoice(false);  //tail
            }
        });
    }
    public void init() {
        throw_text = findViewById(R.id.throw_text);
        win_text = findViewById(R.id.win_text);
        lose_text = findViewById(R.id.lose_text);
        head_btn = findViewById(R.id.head_btn);
        tail_btn = findViewById(R.id.tail_btn);
        coin = findViewById(R.id.coin);
    }
    private int throwCount = 0;
    private  int winCount = 0;
    private int loseCount = 0;
    private boolean randomChoice(boolean playerChoice){
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(2);
        flipCoin(randomNumber == 1, playerChoice);
        return randomNumber == 1;
    }

    public void flipCoin(boolean isHead, boolean playerChoice){
        coin.animate().setDuration(1000);
        coin.animate().rotationYBy(180*5).withEndAction(new Runnable() {
            @Override
            public void run() {
                coin.setImageResource(isHead ? R.drawable.heads : R.drawable.tails);

                throwCount++;
                throw_text.setText("Throws: " + throwCount);

                if (playerChoice == isHead) {
                    winCount++;
                    win_text.setText("Wins: " + winCount);
                }
                else {
                    loseCount++;
                    lose_text.setText("loses: " + loseCount);
                }
            }
        });
    }

}