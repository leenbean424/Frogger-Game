package com.example.froggerhome;

public class Player {
    private String lives;
    private String character;
    private String playerName;

    private int score = 0;

    private int maxPositionY = 0;
    private int currentPositionY = 0;

    public Player(String playerName, String lives, String character) {
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

    public String selectDifficulty(String diff) {
        switch (diff) {
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
        return lives;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void setCurrentPositionY(int y) {
        currentPositionY = y;
    }
    public int getCurrentPositionY() {
        return currentPositionY;
    }
    public void setMaxPositionY(int y) {
        maxPositionY = y;
    }
    public int getMaxPositionY() {
        return maxPositionY;
    }
}
