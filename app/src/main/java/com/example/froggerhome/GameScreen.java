package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class GameScreen extends AppCompatActivity {
    private TextView playerName;
    private TextView difficultyLevel;
    private TextView livesCount;
    private TextView score;

    private ImageView sprite;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        playerName = (TextView) findViewById(R.id.player_name);
        playerName.setText(InitialConfig.getPlayerName());

        difficultyLevel = (TextView) findViewById(R.id.difficulty_level);
        if (InitialConfig.getDifficulty().equals("Hard")) {
            difficultyLevel.setText("Hard");
        } else if (InitialConfig.getDifficulty().equals("Medium")) {
            difficultyLevel.setText("Medium");
        } else if (InitialConfig.getDifficulty().equals("Easy")) {
            difficultyLevel.setText("Easy");
        }

        livesCount = (TextView) findViewById(R.id.lives_count);
        livesCount.setText(InitialConfig.getLives());

        score = (TextView) findViewById(R.id.score_value);
        score.setText("0");

        sprite = (ImageView) findViewById(R.id.sprite);
        if (InitialConfig.getCharacter().equals("1")) {
            sprite.setImageResource(R.drawable.frog_char_1);
        } else if (InitialConfig.getCharacter().equals("2")) {
            sprite.setImageResource(R.drawable.frog_char_2);
        } else if (InitialConfig.getCharacter().equals("3")) {
            sprite.setImageResource(R.drawable.frog_char_3);
        }
    }
}
