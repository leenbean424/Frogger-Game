package com.example.froggerhome;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;


public class JunitTestSprint4 {

    private GameScreen gameScreen;
    private Player newPlayer;

    @Before
    public void init() {
        gameScreen = Mockito.mock(GameScreen.class);
        newPlayer = new Player("Eileen", "5", "4");
    }

    @DisplayName("isWaterDecreaseLives")
    @Test
    public void isWaterDecreaseLives() throws Exception {
        newPlayer.setLives("5");
        when(gameScreen.waterCollision(newPlayer.getCurrentPositionY())).thenReturn(true);

        assertEquals("4", newPlayer.getLives());
    }

    @DisplayName("resetScore")
    @Test
    public void resetScore() throws Exception {
        ImageView car2 = new ImageView(null);
        when(gameScreen.isCarForwardLimit(car2, 1400.0f, -320.0f,
                -300.0f)).thenReturn(1400.0f);
        verify(gameScreen, atMost(1)).isCarBackwardLimit(car2,
                1400.0f, -320.0f, -300.0f);
    }

    @DisplayName("isRespawnAfterCarCollision")
    @Test
    public void isRespawn() throws Exception {
        newPlayer.setLives("5");
        ImageView car2 = new ImageView(null);
        when(gameScreen.collision(car2)).thenReturn(true);
        assertEquals(0, newPlayer.getCurrentPositionY());
        assertEquals(0, newPlayer.getCurrentPositionX());
    }

}
