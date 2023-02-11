package com.example.froggerhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class initial_config extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String player_name;
    private String difficulty;
    private Button char_1;
    private Button char_2;
    private Button char_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        Spinner spinnerDifficulties=findViewById(R.id.difficulty_dropdown); //instantiate our spinner

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulties.setAdapter(adapter);
        spinnerDifficulties.setOnItemSelectedListener(this);
        difficulty = spinnerDifficulties.getSelectedItem().toString();

        char_1 = (Button)findViewById(R.id.char_1);
        char_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame();
            }
        });

        char_2 = (Button)findViewById(R.id.char_2);
        char_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame();
            }
        });

        char_3 = (Button)findViewById(R.id.char_3);
        char_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame();
            }
        });
    }

    public void openGame() {
        Intent intent = new Intent(this, game_screen.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
