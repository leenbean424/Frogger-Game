package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Button;

public class InitialConfig extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    private static String playerName;
    private static String difficulty;
    private static String lives;
    private static String character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        Spinner spinnerDifficulties = findViewById(R.id.difficulty_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulties.setAdapter(adapter);
        spinnerDifficulties.setOnItemSelectedListener(this);

        Button char1;
        Button char2;
        Button char3;
        char1 = (Button) findViewById(R.id.char_1);
        char1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character = "1";
                openGame();
            }
        });

        char2 = (Button) findViewById(R.id.char_2);
        char2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character = "2";
                openGame();
            }
        });

        char3 = (Button) findViewById(R.id.char_3);
        char3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character = "3";
                openGame();
            }
        });
    }

    public void openGame() {
        EditText name = (EditText) findViewById(R.id.editPersonName);
        playerName = name.getText().toString();
        if (playerName.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Valid Player Name",
                    Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, GameScreen.class);
            startActivity(intent);
        }
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static String getLives() {
        return lives;
    }

    public static String getCharacter() {
        return character;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        difficulty = adapterView.getItemAtPosition(i).toString();
        switch (difficulty) {
        case "Easy":
            lives = "5";
            break;
        case "Medium":
            lives = "3";
            break;
        case "Hard":
            lives = "1";
            break;
        default:
            break;
        }
        Toast.makeText(adapterView.getContext(), difficulty, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
