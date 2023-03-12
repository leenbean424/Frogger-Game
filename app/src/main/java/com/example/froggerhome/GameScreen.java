package com.example.froggerhome;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.BreakIterator;




public class GameScreen extends AppCompatActivity {
    private TextView playerName;
    private TextView difficultyLevel;
    private TextView livesCount;
    private TextView score;

    private ImageView sprite;
    private ImageView carImage1;
    private ImageView carImage2;
    private ImageView carImage3;
    private Player player;

    private LinearLayout screen;


    //timer starts at 0
    //TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler(Looper.getMainLooper());
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            //sets up timer
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;


            //moving cars
            carImage1.setX(carImage1.getX() + 10);
            carImage2.setX(carImage2.getX() - 20);
            carImage3.setX(carImage3.getX() + 16);

            //delay that controls how often each callback is made
            timerHandler.postDelayed(this, 50);

        }
    };


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();
        player = new Player(intent.getStringExtra("playerName"),
                intent.getStringExtra("playerLives"),
                intent.getStringExtra("playerChar"));

        playerName = (TextView) findViewById(R.id.player_name);
        playerName.setText(player.getPlayerName());

        difficultyLevel = (TextView) findViewById(R.id.difficulty_level);

        try {
            if (InitialConfig.getDifficulty().equals("Hard")) {
                difficultyLevel.setText("Hard");
            } else if (InitialConfig.getDifficulty().equals("Medium")) {
                difficultyLevel.setText("Medium");
            } else if (InitialConfig.getDifficulty().equals("Easy")) {
                difficultyLevel.setText("Easy");
            }
        } catch (Exception e) {
            difficultyLevel.setText("Easy");
        }

        livesCount = (TextView) findViewById(R.id.lives_count);
        livesCount.setText(player.getLives());

        score = (TextView) findViewById(R.id.score_value);
        score.setText(String.valueOf(player.getScore()));

        sprite = (ImageView) findViewById(R.id.sprite);

        try {
            if (player.getCharacter().equals("1")) {
                sprite.setImageResource(R.drawable.frog_char_1);
            } else if (player.getCharacter().equals("2")) {
                sprite.setImageResource(R.drawable.frog_char_2);
            } else if (player.getCharacter().equals("3")) {
                sprite.setImageResource(R.drawable.frog_char_3);
            }
        } catch (Exception e) {
            sprite.setImageResource(R.drawable.frog_char_1);
        }

        //set up of cars images
        carImage1 = (ImageView) findViewById(R.id.car1);
        carImage2 = (ImageView) findViewById(R.id.car2);
        carImage3 = (ImageView) findViewById(R.id.car3);



        //calling timerhandler function
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);



        screen = (LinearLayout) findViewById(R.id.screen);


        screen.setOnTouchListener(new OnSwipeListener(GameScreen.this) {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return super.onTouch(v, event);
            }

            @Override
            public void onSwipeUp() { // goes up
                swipeAction(0);
            }

            @Override
            public void onSwipeDown() { // goes down
                swipeAction(2);
            }

            @Override
            public void onSwipeLeft() { // goes left
                swipeAction(3);
            }

            @Override
            public void onSwipeRight() { // goes right
                swipeAction(1);
            }

        });
    }

    public void swipeAction(int action) {
        switch (action) {
            case 0:
                swipeUpAction();
                break;
            case 1:
                swipeRightAction();
                break;
            case 2:
                swipeDownAction();
                break;
            case 3:
                swipeLeftAction();
                break;
            default: break;
        }
    }

    private void swipeRightAction() {
        if (sprite.getX() + 100 < 1334) {
            sprite.setRotation(90);
            sprite.setX(sprite.getX() + 100);
        }
    }

    private void swipeLeftAction() {
        if (sprite.getX() - 100 > 3) {
            sprite.setRotation(270);
            sprite.setX(sprite.getX() - 100);
        }
    }

    private void swipeDownAction() {
        if (sprite.getY() > 3.0) {
            sprite.setRotation(0);
            sprite.setY(sprite.getY() - 100);
            player.setCurrentPositionY(player.getCurrentPositionY() + 1);
        }
        if (player.getCurrentPositionY() > player.getMaxPositionY()) {
            player.setMaxPositionY(player.getCurrentPositionY());
            if (player.getCurrentPositionY() > 2 && player.getCurrentPositionY() < 9) {
                player.setScore(player.getScore() + 20);
            } else if (player.getCurrentPositionY() > 9 && player.getCurrentPositionY() < 16) {
                player.setScore(player.getScore() + 30);
            } else {
                player.setScore(player.getScore() + 10);
            }
            score.setText(((Integer)player.getScore()).toString());
        }
    }

    private void swipeUpAction() {
        if (sprite.getY() < 1902.0) {
            sprite.setRotation(180);
            sprite.setY(sprite.getY() + 100);
            player.setCurrentPositionY(player.getCurrentPositionY() - 1);
        }
        if (player.getCurrentPositionY() > player.getMaxPositionY()) {
            player.setMaxPositionY(player.getCurrentPositionY());
            if (player.getCurrentPositionY() > 2 && player.getCurrentPositionY() < 9) {
                player.setScore(player.getScore() + 20);
            } else if (player.getCurrentPositionY() > 9 && player.getCurrentPositionY() < 16) {
                player.setScore(player.getScore() + 30);
            } else {
                player.setScore(player.getScore() + 10);
            }
            score.setText(((Integer)player.getScore()).toString());
        }
    }


}
