package com.example.froggerhome;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class JunitTestSprint3 {

    private GameScreen gameScreen;

    @Before
    public void init() {
        gameScreen = Mockito.mock(GameScreen.class);
    }

    @DisplayName("playerMoveUp")
    @Test
    public void playerMoveUp4Times() throws Exception {
        for (int i = 0; i < 3; i++) {
            gameScreen.swipeAction(0);
        }
        // check if move up at least 3 times
        verify(gameScreen, atMost(3)).swipeAction(0);
        reset();
    }

    @DisplayName("playerMoveDown")
    @Test
    public void playerMoveDown() throws Exception {
        gameScreen.swipeAction(2);
        verify(gameScreen, atLeast(1)).swipeAction(2);
        reset();
    }

    @DisplayName("playerMoveLeft")
    @Test
    public void playerMoveLeft() throws Exception {
        gameScreen.swipeAction(3);
        verify(gameScreen).swipeAction(3);
        reset();
    }

    @DisplayName("playerMoveRight")
    @Test
    public void playerMoveRight() throws Exception {
        gameScreen.swipeAction(3);
        verify(gameScreen).swipeAction(3);
        reset();
    }

    @DisplayName("randomWalk")
    @Test
    public void randomWalk() throws Exception {
        Random rn = new Random();
        int walk[] = new int[10];
        for (int i = 0; i < 10; i++) {
            walk[i] = rn.nextInt(4);
            gameScreen.swipeAction(walk[i]);
        }
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 0; i < walk.length; i++) {
            if (walk[i] == 0) {
                count0++;
            }
            else if (walk[i] == 1) {
                count1++;
            }
            else if (walk[i] == 2) {
                count2++;
            }
            else if (walk[i] == 3) {
                count3++;
            }
        }
        verify(gameScreen, atMost(count0)).swipeAction(0);
        verify(gameScreen, atMost(count1)).swipeAction(1);
        verify(gameScreen, atMost(count2)).swipeAction(2);
        verify(gameScreen, atMost(count3)).swipeAction(3);
    }

}
