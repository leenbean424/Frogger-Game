package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
public class game_screen extends AppCompatActivity {
    private int score = 0;
    private int lives = 3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
    }

}
