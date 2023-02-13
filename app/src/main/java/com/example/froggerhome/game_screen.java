package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.EditText;
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
//        playerName.setText("YOU");

        difficultyLevel = (TextView) findViewById(R.id.difficulty_level);
        if (initial_config.getDifficulty().equals("Hard")) {
            difficultyLevel.setText("Hard");
        } else if (initial_config.getDifficulty().equals("Medium")) {
            difficultyLevel.setText("Medium");
        } else if (initial_config.getDifficulty().equals("Easy")){
            difficultyLevel.setText("Easy");
        }

        livesCount = (TextView) findViewById(R.id.lives_count);
        livesCount.setText(initial_config.getLives());

        score = (TextView) findViewById(R.id.score_value);
        score.setText("0");

        sprite = (ImageView) findViewById(R.id.sprite);
        if (initial_config.getCharacter().equals("1")) {
            sprite.setImageResource(R.drawable.frog_char_1);
        } else if (initial_config.getCharacter().equals("2")) {
            sprite.setImageResource(R.drawable.frog_char_2);
        } else if (initial_config.getCharacter().equals("3")) {
            sprite.setImageResource(R.drawable.frog_char_3);
        }
    }
}
