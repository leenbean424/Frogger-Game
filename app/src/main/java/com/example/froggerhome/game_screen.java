package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class game_screen extends AppCompatActivity {
    private int score = 0;
    private int lives = 3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        TextView difficulty_level = (TextView) findViewById(R.id.difficulty_level);
        if (true) {
            difficulty_level.setText("Hard");
        } else if (true) {
            difficulty_level.setText("Medium");
        } else {
            difficulty_level.setText("Easy");
        }
    }
}
