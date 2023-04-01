package com.example.froggerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_end_game);
        super.onCreate(savedInstanceState);

        TextView score = (TextView) findViewById(R.id.score);
        score.setText("Score");
        TextView finalScore = (TextView) findViewById(R.id.endScore);
        finalScore.setText(Integer.toString(GameScreen.getFinalScore()));

        Button restartGame = (Button) findViewById(R.id.restartGameButton);
        Button exitGame = (Button) findViewById(R.id.exitGameButton);

        restartGame.setOnClickListener(v -> {
            restart();
        });

        exitGame.setOnClickListener(v -> finish());
    }
    public void restart() {
        Intent intent = new Intent(this, InitialConfig.class);
        startActivity(intent);
    }
}
