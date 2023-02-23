package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class GameScreen extends AppCompatActivity {
    private TextView playerName;
    private TextView difficultyLevel;
    private TextView livesCount;
    private TextView score;

    private ImageView sprite;
    private Player player;

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
    }
}
