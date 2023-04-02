package com.example.froggerhome;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atMost;

import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;


public class JunitTestSprint4 {

    private GameScreen gameScreen;
    private Player newPlayer;
    private Runnable newRunnable;
    private ImageView carImage1;

    @Before
    public void init() {
        gameScreen = Mockito.mock(GameScreen.class);
        newPlayer = new Player("Eileen", "5", "4");
        carImage1 = new ImageView(null);
        newRunnable = new Runnable() {
            @Override
            public void run() {
                if (gameScreen.collision(carImage1) ){

                    newPlayer.setCurrentPositionY(0);
                    newPlayer.setMaxPositionY(0);
                    newPlayer.setScore(0);
                    newPlayer.setLives(Integer.toString(Integer.parseInt(newPlayer.getLives()) - 1));
                    if (newPlayer.getLives().equals("0")) {
                        gameScreen.openEndGame();
                    }
                }
            }
        };
    }

    @DisplayName("isWaterDecreaseLives")
    @Test
    public void isWaterDecreaseLives() throws Exception {
        newPlayer.setLives("5");
        newPlayer.setCurrentPositionY(10);
        newRunnable.run();
        when(gameScreen.waterCollision(newPlayer.getCurrentPositionY())).thenReturn(true);
        gameScreen.swipeAction(0);
        assertEquals("4", newPlayer.getLives());
    }

    @DisplayName("isCarCollisionDecreaseLives")
    @Test
    public void isCarCollisionDecreaseLives() throws Exception {
        newPlayer.setLives("5");
        when(gameScreen.collision(carImage1)).thenReturn(true);
        newRunnable.run();
        assertEquals("4", newPlayer.getLives());
    }

    @DisplayName("isRespawnAfterCarCollision")
    @Test
    public void isRespawnAfterCarCollision() throws Exception {
        newPlayer.setLives("5");
        when(gameScreen.collision(carImage1)).thenReturn(true);
        newRunnable.run();

        assertEquals(0, newPlayer.getCurrentPositionY());
        assertEquals(0, newPlayer.getCurrentPositionX());
    }

    @DisplayName("isRespawnAfterWaterCollision")
    @Test
    public void isRespawnAfterWaterCollision() throws Exception {
        newPlayer.setLives("5");
        when(gameScreen.waterCollision(newPlayer.getCurrentPositionY())).thenReturn(true);

        assertEquals(0, newPlayer.getCurrentPositionY());
        assertEquals(0, newPlayer.getCurrentPositionX());
    }

    @DisplayName("resetScoreAfterCarCollision")
    @Test
    public void resetScoreAfterCarCollision() throws Exception {
       newPlayer.setScore(100);
        when(gameScreen.collision(carImage1)).thenReturn(true);
        newRunnable.run();
        assertEquals(0, newPlayer.getScore());
    }

    @DisplayName("resetScoreAfterWaterCollision")
    @Test
    public void resetScoreAfterWaterCollision() throws Exception {
        newPlayer.setScore(100);
        when(gameScreen.waterCollision(newPlayer.getCurrentPositionY())).thenReturn(true);
        newRunnable.run();
        assertEquals(0, newPlayer.getScore());
    }

    @DisplayName("gameOverAfterLivesEnd")
    @Test
    public void gameOverAfterLivesEnd() throws Exception {
        newPlayer.setLives("0");
        when(gameScreen.collision(carImage1)).thenReturn(true);
        newRunnable.run();
        gameScreen.openEndGame();
        verify(gameScreen, atMost(1)).openEndGame();
    }

}
