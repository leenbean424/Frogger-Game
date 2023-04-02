package com.example.froggerhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GameScreen extends AppCompatActivity {
    private TextView playerName;
    private TextView difficultyLevel;
    private TextView livesCount;
    private TextView score;
    private ImageView sprite;
    private ImageView carImage1;
    private ImageView carImage2;
    private ImageView carImage3;
    private static Player player;
    private LinearLayout screen;


    //timer starts at 0
    //TextView timerTextView;
    private long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    private Handler timerHandler = new Handler(Looper.getMainLooper());
    private Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            //sets up timer
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;


            //moving cars
            runCar(carImage1, carImage1.getX(), 10);
            runCar(carImage2, carImage2.getX(), -20);
            runCar(carImage3, carImage3.getX(), 16);

            //checking for collision with cars
            if (collision(carImage1) || (collision(carImage2)) || (collision(carImage3))){
                System.out.println("OUCH");
                sprite.setY(1900);
                sprite.setX(650);
                //player.setCurrentPositionX(0);
                player.setCurrentPositionY(0);
                player.setMaxPositionY(0);
                sprite.setRotation(0);
                player.setScore(0);
                score.setText("0");
                player.setLives(Integer.toString(Integer.parseInt(player.getLives()) - 1));
                livesCount.setText(player.getLives());
                if (player.getLives().equals("0")) {
                    openEndGame();
                }
            }


            //delay that controls how often each callback is made
            timerHandler.postDelayed(this, 50);

            isCarForwardLimit(carImage1, -300, carImage1.getX(), 1400);
            isCarBackwardLimit(carImage2, 1400, carImage2.getX(), -300);
            isCarForwardLimit(carImage3, -300, carImage3.getX(), 1400);


        }
    };

    public boolean collision(ImageView carImg) {
        return (carImg.getX() + carImg.getWidth() > sprite.getX())
                && (sprite.getX() + 50 > carImg.getX())
                && (carImg.getY() + carImg.getHeight() > sprite.getY())
                && (sprite.getY() + 50 > carImg.getY());
    }

    public float isCarForwardLimit(ImageView car, float originalPos, float curr, float limit) {
        if (curr > limit) {
            car.setX(originalPos);
            return originalPos;
        }
        return curr;
    }

    public float isCarBackwardLimit(ImageView car, float originalPos, float curr, float limit) {
        if (curr < limit) {
            car.setX(originalPos);
            return originalPos;
        }
        return curr;
    }

    public float runCar(ImageView carImg, float originalPos, float additionalPos) {
        float newPos = originalPos + additionalPos;
        carImg.setX(newPos);
        return additionalPos;
    }


    @SuppressLint("MissingInflatedId")
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

        livesCount = (TextView) findViewById(R.id.lives_count);
        livesCount.setText(player.getLives());

        try {
            switch (InitialConfig.getDifficulty()) {
                case "Hard":
                    difficultyLevel.setText("Hard");
                    livesCount.setText(player.selectDifficulty("Hard"));
                    break;
                case "Medium":
                    difficultyLevel.setText("Medium");
                    livesCount.setText(player.selectDifficulty("Medium"));
                    break;
                case "Easy":
                    difficultyLevel.setText("Easy");
                    livesCount.setText(player.selectDifficulty("Easy"));
                    break;
            }
        } catch (Exception e) {
            difficultyLevel.setText("Easy");
            livesCount.setText(player.selectDifficulty("Easy"));
        }


        score = (TextView) findViewById(R.id.score_value);
        score.setText(String.valueOf(player.getScore()));

        sprite = (ImageView) findViewById(R.id.sprite);

        try {
            switch (player.getCharacter()) {
                case "1":
                    sprite.setImageResource(R.drawable.frog_char_1);
                    break;
                case "2":
                    sprite.setImageResource(R.drawable.frog_char_2);
                    break;
                case "3":
                    sprite.setImageResource(R.drawable.frog_char_3);
                    break;
            }
        } catch (Exception e) {
            sprite.setImageResource(R.drawable.frog_char_1);
        }

        //set up of cars images
        carImage1 = (ImageView) findViewById(R.id.car1);
        carImage2 = (ImageView) findViewById(R.id.car2);
        carImage3 = (ImageView) findViewById(R.id.car3);


        //calling timerhandler function
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);


        screen = (LinearLayout) findViewById(R.id.screen);


        screen.setOnTouchListener(new OnSwipeListener(GameScreen.this) {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return super.onTouch(v, event);
            }

            @Override
            public void onSwipeUp() { // goes up
                swipeAction(0);
            }

            @Override
            public void onSwipeDown() { // goes down
                swipeAction(2);
            }

            @Override
            public void onSwipeLeft() { // goes left
                swipeAction(3);
            }

            @Override
            public void onSwipeRight() { // goes right
                swipeAction(1);
            }

        });
    }

    public int swipeAction(int action) {
        switch (action) {
        case 0:
            return swipeUpAction();
        case 1:
            return swipeRightAction();
        case 2:
            return swipeDownAction();
        case 3:
            return swipeLeftAction();
        default:
            return 0;
        }
    }

    private int swipeRightAction() {
        if (sprite.getX() + 100 < 1334) {
            sprite.setRotation(90);
            sprite.setX(sprite.getX() + 100);
            player.setCurrentPositionX((int)sprite.getX());
        }
        return (Integer) player.getScore();
    }

    private int swipeLeftAction() {
        if (sprite.getX() - 100 > 3) {
            sprite.setRotation(270);
            sprite.setX(sprite.getX() - 100);
            player.setCurrentPositionX((int)sprite.getX());
        }
        return (Integer) player.getScore();
    }

    /**
     * method update player's score and position when player moving down
     * @return the player's score
     */
    private int swipeDownAction() {
        int addedScore = 0;
        int count = Integer.parseInt(player.getLives());
        if (sprite.getY() > 3.0) {
            sprite.setRotation(0);
            sprite.setY(sprite.getY() - 100);
            player.setCurrentPositionY(player.getCurrentPositionY() + 1);
        }
        if (player.getCurrentPositionY() > player.getMaxPositionY()) {
            player.setMaxPositionY(player.getCurrentPositionY());
            if (player.getCurrentPositionY() > 2 && player.getCurrentPositionY() < 9) {
                addedScore = 20;
            } else if (player.getCurrentPositionY() > 9 && player.getCurrentPositionY() < 16) {
                if (waterCollision(player.getCurrentPositionY())) {
                    count--;
                    if (count < 1) {
                        openEndGame();
                        return (Integer) player.getScore();
                    }
                    sprite.setY(1900);
                    sprite.setX(650);
                    player.setCurrentPositionY(0);
                    player.setMaxPositionY(0);
                    sprite.setRotation(0);
                    player.setScore(0);
                    score.setText("0");
                    player.setLives(String.valueOf(count));
                    livesCount.setText(player.getLives());
                    return (Integer) player.getScore();
                } else {
                    addedScore = 30;
                }
            } else {
                addedScore = 10;
            }
            player.setScore(player.getScore() + addedScore);
            score.setText(((Integer) player.getScore()).toString());
        }
        return (Integer) player.getScore();
    }

    /**
     *  method update player's score and position when player moving up
     * @return the player's score
     */
    private int swipeUpAction() {
        int addedScore = 0;
        int count = Integer.parseInt(player.getLives());
        if (sprite.getY() < 1902.0) {
            sprite.setRotation(180);
            sprite.setY(sprite.getY() + 100);
            player.setCurrentPositionY(player.getCurrentPositionY() - 1);
        }
        if (player.getCurrentPositionY() > player.getMaxPositionY()) {
            player.setMaxPositionY(player.getCurrentPositionY());
            if (player.getCurrentPositionY() > 2 && player.getCurrentPositionY() < 9) {
                addedScore = 20;
            } else if (player.getCurrentPositionY() > 9 && player.getCurrentPositionY() < 16) {
                if (waterCollision(player.getCurrentPositionY())) {
                    count++;
                    if (count < 1) {
                        openEndGame();
                        return (Integer) player.getScore();
                    }
                    sprite.setY(1900);
                    sprite.setX(650);
                    player.setCurrentPositionY(0);
                    player.setMaxPositionY(0);
                    sprite.setRotation(0);
                    player.setScore(0);
                    score.setText("0");
                    player.setLives(String.valueOf(count));
                    livesCount.setText(player.getLives());
                    return (Integer) player.getScore();
                } else {
                    addedScore = 30;
                }
            } else {
                addedScore = 10;
            }
            player.setScore(player.getScore() + addedScore);
            score.setText(((Integer) player.getScore()).toString());
        }
        return (Integer) player.getScore();
    }

    /**
     * waterCollision helper.
     * @param currentPosition the player's position
     * @return whether the player in the river or not
     */
    public boolean waterCollision(int currentPosition) {
        return currentPosition > 9 && currentPosition < 16;
    }

    /**
     *  Transitions to the gameover screen.
     */
    public void openEndGame() {
        Intent intent = new Intent(this, EndGame.class);
        startActivity(intent);
        finish();
    }

    public static int getFinalScore() {
        return player.getScore();
    }

}
