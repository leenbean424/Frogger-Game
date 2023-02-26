package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    private TextView playerName;
    private TextView difficultyLevel;
    private TextView livesCount;
    private TextView score;

    private ImageView sprite;
    private Player player;

    private LinearLayout screen;

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
        if (InitialConfig.getDifficulty().equals("Hard")) {
            difficultyLevel.setText("Hard");
        } else if (InitialConfig.getDifficulty().equals("Medium")) {
            difficultyLevel.setText("Medium");
        } else if (InitialConfig.getDifficulty().equals("Easy")) {
            difficultyLevel.setText("Easy");
        }

        livesCount = (TextView) findViewById(R.id.lives_count);
        livesCount.setText(player.getLives());

        score = (TextView) findViewById(R.id.score_value);
        score.setText("0");

        sprite = (ImageView) findViewById(R.id.sprite);
        if (player.getCharacter().equals("1")) {
            sprite.setImageResource(R.drawable.frog_char_1);
        } else if (player.getCharacter().equals("2")) {
            sprite.setImageResource(R.drawable.frog_char_2);
        } else if (player.getCharacter().equals("3")) {
            sprite.setImageResource(R.drawable.frog_char_3);
        }
        screen = (LinearLayout) findViewById(R.id.screen);
        screen.setOnTouchListener(new OnSwipeListener(GameScreen.this) {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return super.onTouch(v, event);
            }

            @Override
            public void onSwipeDown() { // goes down
                if (sprite.getY() > 3.0) {
                    sprite.setRotation(0);
                    sprite.setY(sprite.getY() - 100);
                }
            }

            @Override
            public void onSwipeUp() { // goes up
                if (sprite.getY() < 1902.0) {
                    sprite.setRotation(180);
                    sprite.setY(sprite.getY() + 100);
                }
            }

            @Override
            public void onSwipeLeft() { // goes left
                if (sprite.getX() - 100 > 3) {
                    sprite.setRotation(270);
                    sprite.setX(sprite.getX() - 100);
                }
            }

            @Override
            public void onSwipeRight() { // goes right
                if (sprite.getX() + 100 < 1334) {
                    sprite.setRotation(90);
                    sprite.setX(sprite.getX() + 100);
                }
            }
        });
    }
}
