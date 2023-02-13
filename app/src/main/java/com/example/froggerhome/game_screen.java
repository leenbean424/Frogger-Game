package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class game_screen extends AppCompatActivity {
    TextView playerName;
    TextView difficultyLevel;
    TextView livesCount;
    TextView score;

    ImageView sprite;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        playerName = (TextView) findViewById(R.id.player_name);
        playerName.setText(initial_config.getPlayer_name());

        difficultyLevel = (TextView) findViewById(R.id.difficulty_level);
        if (initial_config.getDifficulty().equalsIgnoreCase("Hard")) {
            difficultyLevel.setText("Hard");
        } else if (initial_config.getDifficulty().equalsIgnoreCase("Medium")) {
            difficultyLevel.setText("Medium");
        } else if (initial_config.getDifficulty().equalsIgnoreCase("Easy")){
            difficultyLevel.setText("Easy");
        }

        livesCount = (TextView) findViewById(R.id.lives_count);
        livesCount.setText(initial_config.getLives());

        score = (TextView) findViewById(R.id.score_value);
        score.setText("0");

    }
}
