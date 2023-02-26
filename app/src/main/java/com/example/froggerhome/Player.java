package com.example.froggerhome;

import android.widget.EditText;

public class Player {
    private String lives;
    private String character;
    private String playerName;

    public Player(String playerName, String lives, String character){
        setPlayerName(playerName);
        this.lives = lives;
        this.character = character;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getLives() {
        return lives;
    }

    public String getCharacter() {
        return character;
    }

    public void setPlayerName(String playerName) {
        if (isNameValid(playerName)) {
            this.playerName = playerName;
        } else {
            this.playerName = "Default Player";
        }
    }

    public boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        return !name.trim().equalsIgnoreCase("");
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
